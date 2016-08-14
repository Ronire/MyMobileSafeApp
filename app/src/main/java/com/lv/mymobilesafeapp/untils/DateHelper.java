package com.lv.mymobilesafeapp.untils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 吕亚平 on 2016/7/27.
 */
public class DateHelper {
    public static int getDistanceDay(int diatanceday){
        int result=0;
        Date date=new Date();
        Calendar now=Calendar.getInstance();
        int month=now.get(Calendar.MONTH);
        int day=now.get(Calendar.DAY_OF_MONTH);
        Log.i("msg",month+"+++"+day+"+++++"+diatanceday+"+++");
        if(month==1){
            result=28-day+diatanceday;
        }
        else  if(month==0||month==2||month==4||month==6||month==7||month==9||month==11){
            result =31-day+diatanceday;
        }
        else {
            result=30-day+diatanceday;
        }
        return result;
    }
}
