package com.lv.mymobilesafeapp.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by 吕亚平 on 2016/7/25.
 */
public class TaskInfo {
    private Drawable icon;
    private String name="";
    private boolean check;
    private int uid;
    private long flow_number;
    /**
     * byte 为单位
     */
    private long memsize;
    private boolean userTask;
    private String packageName;


    public TaskInfo() {
    }

    public TaskInfo(Drawable icon, String name, long memsize, boolean userTask, String packageName) {
        this.icon = icon;
        this.name = name;
        this.memsize = memsize;
        this.userTask = userTask;
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMemsize() {
        return memsize;
    }

    public void setMemsize(long memsize) {
        this.memsize = memsize;
    }

    public boolean isUserTask() {
        return userTask;
    }

    public void setUserTask(boolean userTask) {
        this.userTask = userTask;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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
}
