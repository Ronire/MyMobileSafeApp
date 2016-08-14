package com.lv.mymobilesafeapp.bean;

/**
 * Created by 吕亚平 on 2016/7/19.
 */
public class LinkMan {
    private String id;
    private String name;
    private String number;
    private String sortLetters;

    public LinkMan() {
    }

    public LinkMan(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
