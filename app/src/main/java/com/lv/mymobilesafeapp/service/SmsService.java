package com.lv.mymobilesafeapp.service;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.untils.DBhelper;
import com.lv.mymobilesafeapp.untils.LocationHelpter;
import com.lv.mymobilesafeapp.untils.MyOpenHelper;

/**
 * Created by 吕亚平 on 2016/7/21.
 */
public class SmsService extends Service {
    private SharedPreferences sharedPreferences;
    private TelephonyManager tm;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private String realnumber, telephone, safenumber;
    private SmsRecever recever;
    private MediaPlayer player;
    private DevicePolicyManager devicePolicyManager;
    private LocationHelpter locationHelpter;
    private LocationManager locationManager;
    private boolean flag=false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();

        //判断Sim卡是否改变,如果改变发送号码给安全号码 18770814068 13170914707
        System.out.println("+++++++++++++++++开启服务111111111111111111111111111+++++++++++++++++++++");
        System.out.println("++++++++++++++++++"+safenumber+"111111111111111111111111111+++++++++++++++++++++");
        if (flag&&!realnumber.equals(sharedPreferences.getString("sim", null))&&sharedPreferences.getString("sim", null)!=null) {
            SmsManager.getDefault().sendTextMessage(safenumber, null, "手机号码发生改变:" + telephone, null, null);
            System.out.println("+++++++++++++++++开启服务2222222222222222222222222+++++++++++++++++++++");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {

        //动态注册广播接收器
        recever = new SmsRecever();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(recever, filter);
        super.onCreate();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("safe", MODE_PRIVATE);
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        helper = new MyOpenHelper(this, "safe.db", null, 1);
        db = helper.getWritableDatabase();
        realnumber = tm.getSimSerialNumber();
        telephone = tm.getLine1Number();
        safenumber = sharedPreferences.getString("safenumber", null);
        flag=sharedPreferences.getBoolean("flag",false);
        locationHelpter = new LocationHelpter(this);
        locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);



        player = MediaPlayer.create(this, R.raw.start);
        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        System.out.println("+++++++++++++++++++++_______" + safenumber + "_________-PPPPPPPPPPPPPPPP");
    }

    private class SmsRecever extends BroadcastReceiver {
        /**
         * 拦截短信 判断是否是安全号码发来的
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("++++++++++++接收到短信++++++++++++++++++++");
            System.out.println("+++++++++++++++" + safenumber + "++++++++++++++++++++++");

            Object[] objs = (Object[]) intent.getExtras().get("pdus");
            for (Object obj : objs) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
                String sender = smsMessage.getOriginatingAddress();
                String tel = sender.substring(3, sender.length());
                String body = smsMessage.getMessageBody().trim();

                if (DBhelper.check(tel, db) == 1) {
                    // 在黑名单中 拦截
                    abortBroadcast();
                } else if (tel.equals(safenumber)&&flag) {
                    //如果是安全号码发来的 判断号码内容
                    System.out.println("++++++++++++++++++++++++++++++++" + body);
                    System.out.println("+++++++++++++++++++++++++=="+"#*alarm*#".equals(body));
                    if ("#*location*#".equals(body)) {
                        //获取位置信息 发送给安全号码
                        Location location = locationHelpter.getLocation();
                        SmsManager.getDefault().sendTextMessage(safenumber, null,
                                1111 + " " + 111, null, null);

                    } else if ("#*alarm*#".equals(body)) {
                        //播放报警音乐
                        System.out.println("+++++++++++++播放音乐+++++++++++++++++=");
                        try {
                            System.out.println("+++++++++++++开始播放音乐+++++++++++++++++=");
                            // player.prepare();
                            player.start();
                            System.out.println("+++++++++++++开始播放音乐2222222222222222+++++++++++++++++=");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if ("#*wipedata*#".equals(body)) {
                        //清除数据
                        devicePolicyManager.wipeData(0);

                    } else if ("#*lockscreen*#".equals(body)) {
                        //远程锁屏
                        devicePolicyManager.lockNow();

                    }

                }
            }

        }

    }


}
