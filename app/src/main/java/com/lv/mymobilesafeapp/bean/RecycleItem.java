package com.lv.mymobilesafeapp.bean;

/**
 * Created by 吕亚平 on 2016/7/19.
 */
public class RecycleItem {
    private int image;
    private String nmae;

    public RecycleItem(int image, String nmae) {
        this.image = image;
        this.nmae = nmae;
    }

    public int getImage() {
        return image;
    }

    public String getNmae() {
        return nmae;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setNmae(String nmae) {
        this.nmae = nmae;
    }
}
