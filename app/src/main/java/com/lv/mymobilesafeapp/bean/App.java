package com.lv.mymobilesafeapp.bean;

import android.graphics.drawable.Drawable;

import java.util.Comparator;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class App implements Comparable<App>{
    private String appName;
    private Drawable appIcon;
    private String appSize;
    private String appPackageName;
    private boolean appType;
    private int uid;
    private long flow_number;

    public App(String appName, Drawable appIcon, String appSize, String appPackageName,boolean appType) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.appSize = appSize;
        this.appPackageName = appPackageName;
        this.appType=appType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public boolean getAppType() {
        return appType;
    }

    public void setAppType(boolean appType) {
        this.appType = appType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getFlow_number() {
        return flow_number;
    }

    public void setFlow_number(long flow_number) {
        this.flow_number = flow_number;
    }

    @Override
    public int compareTo(App another) {


        return new Long(another.getFlow_number()).compareTo(new Long(this.getFlow_number()));
    }
}
