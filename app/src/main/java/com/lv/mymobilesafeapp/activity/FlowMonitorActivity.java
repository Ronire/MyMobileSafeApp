package com.lv.mymobilesafeapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.TrafficStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.untils.DateHelper;
import com.lv.mymobilesafeapp.view.CircleAnimationBar;

public class FlowMonitorActivity extends AppCompatActivity implements View.OnClickListener{
    private CircleAnimationBar bar;
    private RelativeLayout yidong,wifi;
    private ImageView flowSetting;
    private TextView use ,distanceday;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_monitor);
        init( );

    }

    @Override
    protected void onResume() {
        Long total=Long.parseLong(sharedPreferences.getString("totalnumber",null));
        Long useflow=sharedPreferences.getLong("useflow",0);
        int day=sharedPreferences.getInt("date",0);
        Float f=new Float(((useflow.floatValue()/total.floatValue())*100));
     int  process=f.intValue();
        Log.i("msg",process+"++++++++++++");
        bar.setText(total+"M");
        use.setText(useflow+"M");
        bar.setProgress(process);
        distanceday.setText(DateHelper.getDistanceDay(day+1)+"");
        super.onResume();
    }

    private void init(){
        yidong=(RelativeLayout)findViewById(R.id.yidong);
        wifi=(RelativeLayout)findViewById(R.id.wifi);
        flowSetting=(ImageView)findViewById(R.id.flow_setting);
        use=(TextView)findViewById(R.id.using);
        distanceday=(TextView)findViewById(R.id.distanceday);
        sharedPreferences=getSharedPreferences("safe",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putLong("useflow",(TrafficStats.getTotalRxBytes()+TrafficStats.getTotalTxBytes())/1024/1024);
        editor.commit();
        flowSetting.setOnClickListener(this);
        yidong.setOnClickListener(this);
        wifi.setOnClickListener(this);
        bar=(CircleAnimationBar)findViewById(R.id.my_bar);


       // use.setText((TrafficStats.getMobileRxBytes()+TrafficStats.getMobileTxBytes())/1024/1024+"M");


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yidong:
                startActivity(new Intent(this,FlowActivity.class));
                break;
            case R.id.wifi:
                startActivity(new Intent(this,FlowActivity.class));
                break;
            case  R.id.flow_setting:
                startActivity(new Intent(this,FlowSettingActivity.class));
                break;
        }
    }
}
