package com.lv.mymobilesafeapp.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.AppAdapter;
import com.lv.mymobilesafeapp.bean.App;
import com.lv.mymobilesafeapp.untils.AppStats;
import com.lv.mymobilesafeapp.untils.StatsHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class AppManagerActivity extends AppCompatActivity {
    private ListView appListView;
    private PackageManager pm;
    private List<App> appList = new ArrayList<>();


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x001){
                appListView.setAdapter(new AppAdapter(AppManagerActivity.this,appList,R.layout.app_item));
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        init();
        initdata();

    }

    private void init() {
        appListView = (ListView) findViewById(R.id.app_list);
        pm = getPackageManager();
    }

    private void initdata() {

        try{
            for (PackageInfo info : getInfos()) {
                String packname=info.packageName;
                String name=info.applicationInfo.loadLabel(pm).toString();
                Drawable appicon=info.applicationInfo.loadIcon(pm);


                String size="0"+"kb";
                boolean type=false;
                int flag=info.applicationInfo.flags;

                if((flag&info.applicationInfo.FLAG_SYSTEM)!=0){
                    //系统程序
                    type=true;
                }
                App app=new App(name,appicon,size,packname,type);
                appList.add(app);


            }
          /*  new Thread(){
                @Override
                public void run() {
                    try{
                        for(App app:appList){
                            AppStats appStats=StatsHelper.queryPacakgeSize(app.getAppPackageName().trim(),pm);
                              sleep(300);
                            //join();
                            app.setAppSize((appStats.getCachesize()+appStats.getCodesize())/1024+"kb");

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    super.run();
                    handler.sendEmptyMessage(0x001);
                }
            }.start();*/
            handler.sendEmptyMessage(0x001);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private List<PackageInfo> getInfos() {
        return pm.getInstalledPackages(0);
    }

}
