package com.lv.mymobilesafeapp.recever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

import com.lv.mymobilesafeapp.untils.DBhelper;

/**
 * Created by 吕亚平 on 2016/7/21.
 */
public class InterCeptorRecever extends BroadcastReceiver {


    //在这里读取短信的内容
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        for (Object obj : objs) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String sender = smsMessage.getOriginatingAddress();
            String tel = sender.substring(3, sender.length());

            System.out.println(tel + "000000000000000000000000000000000000000000000000");
            if (DBhelper.check(tel,null) == 1) {
                // 在黑名单中 拦截
                System.out.println("+++++++++++++拦截短信++++++++++++++");
                abortBroadcast();
            }
        }
    }
}
