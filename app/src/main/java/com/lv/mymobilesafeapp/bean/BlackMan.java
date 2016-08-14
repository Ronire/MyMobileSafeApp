package com.lv.mymobilesafeapp.bean;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class BlackMan {
    private String name;
    private String number;

    public BlackMan() {
    }

    public BlackMan(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BlackMan{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
