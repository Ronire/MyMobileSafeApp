package com.lv.mymobilesafeapp.untils;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.RemoteException;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class AppStats extends IPackageStatsObserver.Stub{
    /**
     * 获取的大小是字节B
     * */
   private Long cachesize;
    private Long datasize;
    private Long codesize;

    @Override
    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
        Log.i("msg", "++++++++++++++++onGetStatsCompleted+++++++++++++++++++");
        System.out.println();
        cachesize = pStats.cacheSize  ;

        datasize = pStats.codeSize  ;

        codesize =    pStats.codeSize  ;
        Log.i("msg", cachesize+"++++++++++++++="+datasize+"+++++++++++++++++=="+codesize);


    }

    public Long getCachesize() {
        return cachesize;
    }

    public void setCachesize(Long cachesize) {
        this.cachesize = cachesize;
    }

    public Long getDatasize() {
        return datasize;
    }

    public void setDatasize(Long datasize) {
        this.datasize = datasize;
    }

    public Long getCodesize() {
        return codesize;
    }

    public void setCodesize(Long codesize) {
        this.codesize = codesize;
    }
}
