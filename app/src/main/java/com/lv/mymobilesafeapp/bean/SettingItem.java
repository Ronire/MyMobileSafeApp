package com.lv.mymobilesafeapp.bean;

/**
 * Created by 吕亚平 on 2016/7/22.
 */
public class SettingItem  {
    private String title;
    private int srcId;

    public SettingItem(String title, int srcId) {
        this.title = title;
        this.srcId = srcId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }
}
