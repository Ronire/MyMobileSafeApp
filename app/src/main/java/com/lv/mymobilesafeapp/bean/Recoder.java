package com.lv.mymobilesafeapp.bean;

import java.util.Date;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class Recoder {
    private String name;
    private String type;
    private String time;
    private String number;

    public Recoder() {
    }

    public Recoder(String type, String name, String time, String number) {
        this.type = type;
        this.name = name;
        this.time = time;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String   getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
