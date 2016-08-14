package com.lv.mymobilesafeapp.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by 吕亚平 on 2016/7/28.
 */
public class Cache {
    private String name;
    private String packname;
    private long size;
    private Drawable icon;

    public Cache() {
    }

    public Cache(String name, String packname, Long size, Drawable icon) {
        this.name = name;
        this.packname = packname;
        this.size = size;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
