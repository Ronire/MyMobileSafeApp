package com.lv.mymobilesafeapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;

public class FlowSettingActivity extends AppCompatActivity implements View.OnClickListener{
    private Switch mSwitch;
    private RelativeLayout flowsetting;
    private TextView number,date;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_setting);
        init();
    }
    private void init(){
        mSwitch=(Switch)findViewById(R.id.flowsetting_sw);
        flowsetting=(RelativeLayout)findViewById(R.id.flow_manager);
        number=(TextView)findViewById(R.id.flowsetting_number);
        date=(TextView)findViewById(R.id.flowsetting_date);
        sharedPreferences=getSharedPreferences("safe",MODE_PRIVATE);
        flowsetting.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        number.setText(sharedPreferences.getString("totalnumber",null)+"MB");
        date.setText("每月"+(sharedPreferences.getInt("date",-1)+1)+"日");
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flow_manager:
                startActivity(new Intent(this,MainSettingActivity.class));

                break;
        }

    }
}
