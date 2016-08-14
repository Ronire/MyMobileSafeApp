package com.lv.mymobilesafeapp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class BlackManDao {
    public static void save(SQLiteDatabase db, String name, String number) {
        String sql = "insert into tbl_blacklist(" + "name,telephone) values(?,?)";
        db.execSQL(sql, new String[] { name, number });
    }

    public static  void delete(SQLiteDatabase db, String tel) {

        String sql = "delete from tbl_blacklist" + " where telephone =" + tel;
        db.execSQL(sql);
    }

    public static void updata(SQLiteDatabase db, String name, String tel) {

    }

    public static Cursor findall(SQLiteDatabase db) {
        String sql = "select *from tbl_blacklist";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;

    }

    public static Cursor findbytel(SQLiteDatabase db,String tel) {
        String sql = "select *from tbl_blacklist"+"where telephone=" +tel;

        Cursor cursor = db.rawQuery(sql, null);
        return cursor;

    }
}
