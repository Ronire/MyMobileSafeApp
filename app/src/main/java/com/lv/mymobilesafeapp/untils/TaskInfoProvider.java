package com.lv.mymobilesafeapp.untils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug;

import com.jaredrummler.android.processes.ProcessManager;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.lv.mymobilesafeapp.bean.TaskInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/25.
 */
public class TaskInfoProvider {

    public static List<TaskInfo> getTaskInfo(Context context){
        List<TaskInfo> taskInfoList=new ArrayList<>();
        ActivityManager am=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        /**
         * 得到正在运行的进程
         * */
      // List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList= am.getRunningAppProcesses();
        List<AndroidAppProcess> runningAppProcessInfoList= ProcessManager.getRunningAppProcesses();

        PackageManager pm=context.getPackageManager();
        for(AndroidAppProcess processInfo:runningAppProcessInfoList){
            TaskInfo taskInfo=new TaskInfo();
            Debug.MemoryInfo[] memoryInfo = am
                    .getProcessMemoryInfo(new int[] {processInfo.pid});
            int memory = memoryInfo[0].getTotalPrivateDirty();
            //进程uid
            int uid=processInfo.uid;
            taskInfo.setUid(uid);
            // 内存占用大小
            long memorySize = memory * 1024L;
            taskInfo.setMemsize(memorySize);
            // 包名、进程名
            String packageName = processInfo.name;
            taskInfo.setPackageName(packageName);
            try {
                PackageInfo packInfo = pm.getPackageInfo(packageName, 0);
                // 图标
                Drawable icon = packInfo.applicationInfo.loadIcon(pm);
                taskInfo.setIcon(icon);
                // 软件名称
                String name = packInfo.applicationInfo.loadLabel(pm).toString();
                taskInfo.setName(name);
                int falgs = packInfo.applicationInfo.flags;

                if ((falgs & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    // 用户进程
                    taskInfo.setUserTask(true);
                } else {
                    // 系统进程
                    taskInfo.setUserTask(false);
                }
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            taskInfoList.add(taskInfo);
        }

        return taskInfoList;
    }

}
