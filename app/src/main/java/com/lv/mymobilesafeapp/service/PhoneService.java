package com.lv.mymobilesafeapp.service;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


import com.android.internal.telephony.ITelephony;
import com.lv.mymobilesafeapp.untils.DBhelper;
import com.lv.mymobilesafeapp.untils.MyOpenHelper;

import java.lang.reflect.Method;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class PhoneService extends Service {
    private TelephonyManager tm;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("++++++++++++____________creat_____________+++++++++++++++");
        helper = new MyOpenHelper(this, "safe.db", null, 1);
        db = helper.getWritableDatabase();
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
        Log.i("msg","++++++++++++____________start_____________+++++++++++++++");
        System.out.println("++++++++++++____________start_____________+++++++++++++++");
        return super.onStartCommand(intent, flags, startId);
    }

    private class MyListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            System.out.println("++++++++++++____________listener_____________+++++++++++++++");
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    System.out.println("=================++++++++++++++" + incomingNumber);
                    if (DBhelper.check(incomingNumber,db) == 1) {
                        System.out.println("+++++++++++++++拦截+++++++++++++++");
                        endcall();
                    }
                    for (int i = 0; i < 100; i++) {

                        Toast.makeText(getApplicationContext(), "拦截", Toast.LENGTH_SHORT).show();
                    }

                    break;

                default:
                    break;
            }

            super.onCallStateChanged(state, incomingNumber);
        }

    }




    public void endcall() {
        try {
            Class clazz = PhoneService.class.getClassLoader().loadClass("android.os.ServiceManager");
            Method method = clazz.getDeclaredMethod("getService", String.class);
            IBinder ibBinder = (IBinder) method.invoke(null, TELEPHONY_SERVICE);
            ITelephony.Stub.asInterface(ibBinder).endCall();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
