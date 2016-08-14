package com.lv.mymobilesafeapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by 吕亚平 on 2016/7/27.
 */
public class AntivirusDao {
    private static String path = "data/data/com.lv.mymobilesafeapp/databases/antivirus.db";
    public static String isVirus(String md5) {

        String result = null;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select desc from datable where md5 = ?",
                new String[] { md5 });
        while (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        cursor.close();
        db.close();
        Log.i("msg","查找数据库");
        return result;
    }
}
