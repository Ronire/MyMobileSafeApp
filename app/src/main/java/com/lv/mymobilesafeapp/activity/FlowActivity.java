package com.lv.mymobilesafeapp.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.FlowerAdapter;
import com.lv.mymobilesafeapp.bean.App;
import com.lv.mymobilesafeapp.bean.TaskInfo;
import com.lv.mymobilesafeapp.untils.TaskInfoProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlowActivity extends AppCompatActivity {
    private List<App> apps = new ArrayList<>();
    private ListView tasklistview;
    private PackageManager pm;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x001) {
                Collections.sort(apps);
                tasklistview.setAdapter(new FlowerAdapter(FlowActivity.this, apps, R.layout.flow_item));
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        init();

    }

    private void init() {
        tasklistview = (ListView) findViewById(R.id.flow_list);
        pm = getPackageManager();
        initdata();

    }

    private void initdata() {

        try {
            for (PackageInfo info : getInfos()) {
                String packname = info.packageName;
                String name = info.applicationInfo.loadLabel(pm).toString();
                Drawable appicon = info.applicationInfo.loadIcon(pm);
                int uid = info.applicationInfo.uid;
                long number = TrafficStats.getUidRxBytes(uid) + TrafficStats.getUidTxBytes(uid);


                String size = "0" + "kb";
                boolean type = false;
                int flag = info.applicationInfo.flags;

                if ((flag & info.applicationInfo.FLAG_SYSTEM) != 0) {
                    //系统程序
                    type = true;
                }
                App app = new App(name, appicon, size, packname, type);
                app.setUid(uid);
                app.setFlow_number(number);
                apps.add(app);


            }

            handler.sendEmptyMessage(0x001);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<PackageInfo> getInfos() {
        return pm.getInstalledPackages(0);
    }
}
