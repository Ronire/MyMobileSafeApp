package com.lv.mymobilesafeapp.untils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 吕亚平 on 2016/7/20.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    private String sql="create table tbl_blacklist("
            +"_id integer primary key autoincrement,"
            +"name text,telephone text)";
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
