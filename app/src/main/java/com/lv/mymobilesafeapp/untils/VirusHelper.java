package com.lv.mymobilesafeapp.untils;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 吕亚平 on 2016/7/27.
 */
public class VirusHelper {
    public static void  loaddatabase(Context context){
        String path="data/data/com.lv.mymobilesafeapp/databases/antivirus.db";
        File file=new File(path);
        try{
            FileOutputStream out=new FileOutputStream(file);
            InputStream in=context.getAssets().open("antivirus.db");
            byte[] b=new byte[10240];
            int flag;
            while ((in.read(b))>0){
                out.write(b);
            }
            out.flush();
            in.close();
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
