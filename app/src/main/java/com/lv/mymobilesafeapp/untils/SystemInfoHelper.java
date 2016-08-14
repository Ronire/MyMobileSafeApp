package com.lv.mymobilesafeapp.untils;

import android.app.ActivityManager;
import android.content.Context;

import com.jaredrummler.android.processes.ProcessManager;

/**
 * Created by 吕亚平 on 2016/7/25.
 */
public class SystemInfoHelper {
    /**
     * 获取当前进程的个数
     * */
    public static int getRunningProgressSize(Context context){

    return ProcessManager.getRunningAppProcesses().size();
    }

    /***
     * 获取可用内存大小
     * */
    public static long getAvailableRam(Context context){
        ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }

    /**
     * 获取内存总大小
     * */
    public static long getTotalRam(Context context){
        ActivityManager am=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }


}
