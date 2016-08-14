package com.lv.mymobilesafeapp.recever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.lv.mymobilesafeapp.MainActivity;
import com.lv.mymobilesafeapp.service.SmsService;

/**
 * Created by 吕亚平 on 2016/7/21.
 */
public class MySMSRecever extends BroadcastReceiver {
    //监听系统开机的广播
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("++++++++++++++广播接收器接收到了广播+++++++++++++++++++++++++");
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            System.out.println("++++++++++++++开机广播+++++++++++++++++++++++++");
            Intent startintent = new Intent();
            startintent.setClass(context, SmsService.class);
            startintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(startintent);
            System.out.println("+++++++++++++++++startintent++++++++++++++++++++++");


            /*Intent i=new Intent();
            i.setAction(Intent.ACTION_SENDTO);
            i.putExtra("sms_body", "The SMS test");
            i.setData(Uri.parse("sms:15727658318"));
            context.startActivity(i);*/
        }
    }
}
