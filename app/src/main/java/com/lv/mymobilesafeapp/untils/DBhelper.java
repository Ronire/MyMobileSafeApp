package com.lv.mymobilesafeapp.untils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lv.mymobilesafeapp.bean.BlackMan;
import com.lv.mymobilesafeapp.dao.BlackManDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class DBhelper {
    public static int check(String number, SQLiteDatabase db) {
        int result = 0;
        List<BlackMan> list=new ArrayList<>();

        Cursor cursor = BlackManDao.findall(db);

        while (cursor.moveToNext()) {

            BlackMan item = new BlackMan(cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("telephone")));
            list.add(item);
        }
        for (BlackMan item : list) {
            if (item.getNumber().equals(number)) {
                result = 1;
                break;
            }
        }

        return result;
    }
}
