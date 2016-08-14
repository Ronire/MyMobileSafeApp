package com.lv.mymobilesafeapp.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.mymobilesafeapp.MainActivity;
import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.dao.AntivirusDao;
import com.lv.mymobilesafeapp.untils.Md5Utils;
import com.lv.mymobilesafeapp.untils.VirusHelper;
import com.lv.mymobilesafeapp.view.RadarView;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class KillVirusActivity extends AppCompatActivity {
    private LinearLayout virus;
    private List<Scanfinfo> scanfinfoList=new ArrayList<>();
    private RadarView radarView;
private TextView showtext;
    private Button finish;
    PackageManager pm;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x001){
                Scanfinfo scanfinfo=(Scanfinfo)msg.obj;
                TextView textView=new TextView(KillVirusActivity.this);
                if(scanfinfo.isVirus){
                    textView.setTextColor(Color.rgb(238,78,78));
                }
                textView.setText(scanfinfo.appName);
                virus.addView(textView,0);
            }
            if(msg.what==0x002){
                //结束
                Log.i("msg",scanfinfoList.size()+"+++++++++++++=");
                radarView.setFlag(false);
                showtext.setVisibility(View.INVISIBLE);
                finish.setVisibility(View.VISIBLE);
                if(scanfinfoList.size()>0){
                    Toast.makeText(KillVirusActivity.this,"发现病毒",Toast.LENGTH_SHORT).show();
                    //在这里写杀毒
                    new  Thread(){
                        @Override
                        public void run() {
                            super.run();
                            for (Scanfinfo scanfinfo : scanfinfoList) {
                                Uri packuri = Uri.parse("package:" + scanfinfo.packname);
                                Intent intent = new Intent(Intent.ACTION_DELETE);
                                intent.setData(packuri);
                                startActivity(intent);
                            }
                        }
                    }.start();

                }else {
                    Toast.makeText(KillVirusActivity.this,"未发现病毒",Toast.LENGTH_SHORT).show();
                }

            }
            if(msg.what==0x003){


                showtext.setText("正在扫描："+(String )msg.obj.toString());
                showtext.setVisibility(View.VISIBLE);

            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_virus);
        init();

    }

    private void init() {
        VirusHelper.loaddatabase(this);
        radarView=(RadarView)findViewById(R.id.radarview);
showtext=(TextView)findViewById(R.id.showtext);
        finish=(Button)findViewById(R.id.finish);
        virus = (LinearLayout) findViewById(R.id.virus);
        start();
    }

    private void start() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //查杀找病毒

                pm = getPackageManager();
                List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
                for (PackageInfo info : packageInfos) {
                    Message s=new Message();
                    s.what=0x003;
                    s.obj=info.applicationInfo.loadLabel(pm).toString();
                    handler.sendMessage(s);


                    try {
                        sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Scanfinfo scanfinfo=new Scanfinfo();
                    String packname = info.packageName;
                    Log.i("msg",packname);
                    String appname=info.applicationInfo.loadLabel(pm).toString();
                    String md5 = Md5Utils.md5Password(info.signatures[0].toCharsString());
                    if(info.packageName.equals("com.lv.bdmap")){
                        //病毒测试
                        scanfinfo.isVirus=true;
                        scanfinfo.desc=AntivirusDao.isVirus(md5);
                        scanfinfoList.add(scanfinfo);
                    }
                    else if(AntivirusDao.isVirus(md5)==null){
                        scanfinfo.isVirus=false;
                        scanfinfo.desc=null;
                    }
                    else  {
                        scanfinfo.isVirus=true;
                        scanfinfo.desc=AntivirusDao.isVirus(md5);
                        scanfinfoList.add(scanfinfo);
                    }
                    scanfinfo.appName=appname;
                    scanfinfo.packname=packname;
                    Message msg=Message.obtain();
                    msg.what=0x001;
                    msg.obj=scanfinfo;
                    handler.sendMessage(msg);
                }
                handler.sendEmptyMessage(0x002);
            }
        }.start();
    }

    class Scanfinfo{
        boolean isVirus;
        String desc;
        String appName;
        String packname;
    }

    public void mfinish(View v){
       // startActivity(new Intent(this, MainActivity.class));
       this.finish();
    }
}
