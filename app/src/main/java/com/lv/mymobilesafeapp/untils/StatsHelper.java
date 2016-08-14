package com.lv.mymobilesafeapp.untils;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class StatsHelper {
    public static  AppStats  queryPacakgeSize(String pkgName,PackageManager pm) throws Exception{
        AppStats appStats=new AppStats();
        if ( pkgName != null){
            //使用放射机制得到PackageManager类的隐藏函数getPackageSizeInfo
            try {
                Log.i("msg","++++++++++++++执行反射++++++++++++++++");
                Method getPackageSizeInfo = pm.getClass().getMethod("getPackageSizeInfo", String.class,IPackageStatsObserver.class);
                //调用该函数，并且给其分配参数 ，待调用流程完成后会回调PkgSizeObserver类的函数
                getPackageSizeInfo.invoke(pm, pkgName,appStats);
                Log.i("msg","++++++++++++++反射结束++++++++++++++++");

            }
            catch(Exception ex){

                ex.printStackTrace() ;
                throw ex ;  // 抛出异常

            }
        }
        return appStats;
    }
}
