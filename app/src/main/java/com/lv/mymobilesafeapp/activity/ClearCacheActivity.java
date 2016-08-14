package com.lv.mymobilesafeapp.activity;

import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.adapter.CacheAdapter;
import com.lv.mymobilesafeapp.bean.Cache;
import com.lv.mymobilesafeapp.untils.AppStats;
import com.lv.mymobilesafeapp.untils.StatsHelper;
import com.lv.mymobilesafeapp.view.CircleProgressBar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClearCacheActivity extends AppCompatActivity {
    private ListView cachelist;
    private List<Cache> caches = new ArrayList<>();
    private List<Cache> list = new ArrayList<>();
    private PackageManager pm;
    private CircleProgressBar circleProgressBar;
    private long totalsize;
    private TextView cache_number, cache_msg;
    private RelativeLayout relativeLayout;
    private Button clear;
    private CacheAdapter cacheAdapter;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            try {
                if (msg.what == 0x001) {

                    circleProgressBar.setFlag(false);
                    sendEmptyMessage(0x002);

                }

                if (msg.what == 0x02) {
                    Thread.sleep(1000);
                    circleProgressBar.setVisibility(View.INVISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    cache_number.setVisibility(View.VISIBLE);
                    cache_msg.setVisibility(View.VISIBLE);
                    cache_number.setText(totalsize / 1024 + " " + "kb");
                    if (totalsize > 1024 * 1024 * 10) {
                        cache_msg.setText("建议清理");

                    } else {
                        cache_msg.setText("比较干净");
                    }

                    clear.setVisibility(View.VISIBLE);

                }
                if (msg.what == 0x003) {
                    cacheAdapter.notifyDataSetChanged();
                    circleProgressBar.setText(totalsize / 1024 + "");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_cache);
        init();
        initdata();
        cacheAdapter = new CacheAdapter(ClearCacheActivity.this, caches, R.layout.cache_item);
        cachelist.setAdapter(cacheAdapter);
    }

    private void init() {
        cachelist = (ListView) findViewById(R.id.cachelist);
        pm = getPackageManager();
        circleProgressBar = (CircleProgressBar) findViewById(R.id.circlebar);
        cache_number = (TextView) findViewById(R.id.cache_number);
        cache_msg = (TextView) findViewById(R.id.message);
        relativeLayout = (RelativeLayout) findViewById(R.id.hidecenter);
        clear = (Button) findViewById(R.id.clear);
    }

    private void initdata() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    List<PackageInfo> packageInfos = pm.getInstalledPackages(0);
                    for (PackageInfo info : packageInfos) {
                        Cache cache = new Cache();
                        String packnmae = info.packageName;
                        String name = info.applicationInfo.loadLabel(pm).toString();
                        Drawable icon = info.applicationInfo.loadIcon(pm);
                        AppStats appStats = StatsHelper.queryPacakgeSize(packnmae, pm);
                        Thread.sleep(100);
                        long size = appStats.getCachesize();

                        // circleProgressBar.setText(totalsize+" ");
                        if (size > 0) {
                            totalsize += size;
                            cache.setIcon(icon);
                            cache.setName(name);
                            cache.setPackname(packnmae);
                            cache.setSize(size);
                            caches.add(cache);
                            handler.sendEmptyMessage(0x003);
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
                handler.sendEmptyMessage(0x001);
            }
        }.start();

    }

    public void clear(View v) {
        Log.i("clear","clear++++++++++++++++++++++++++++");
        Method[] methodes = PackageManager.class.getMethods();
        for (Method method : methodes) {
            if ("freeStorageAndNotify".equals(method.getName())) {
                try {
                    method.invoke(pm, Integer.MAX_VALUE, new IPackageDataObserver.Stub() {
                        @Override
                        public void onRemoveCompleted(String packageName, boolean succeeded)
                                throws RemoteException {
                           Log.i("msg","succeeded=" + succeeded);
                        }
                    });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}
