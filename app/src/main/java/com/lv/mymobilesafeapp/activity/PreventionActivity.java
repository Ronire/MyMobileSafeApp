package com.lv.mymobilesafeapp.activity;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.fragment.Setting1Fragment;
import com.lv.mymobilesafeapp.fragment.Setting2Fragment;
import com.lv.mymobilesafeapp.fragment.Setting3Fragment;
import com.lv.mymobilesafeapp.fragment.Setting4Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首先判断是否是第一次进入应用 如果是设置密码
 * 把密码保存在shared里面
 */

public class PreventionActivity extends FragmentActivity implements View.OnClickListener {
    private Button sim, safe;
    private EditText e_number;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TelephonyManager tm;
    private LinearLayout fragment;
    private List<Fragment> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("safe", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        fragment = (LinearLayout) findViewById(R.id.fragment);

        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        list.add(new Setting1Fragment());
        list.add(new Setting2Fragment());
        list.add(new Setting3Fragment());
        list.add(new Setting4Fragment());
        ft.add(R.id.fragment, list.get(0));
        ft.add(R.id.fragment, list.get(1));
        ft.add(R.id.fragment, list.get(2));
        ft.add(R.id.fragment, list.get(3));
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                break;
        }

    }

    public void next1(View v) {
        //跳到下一个Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(list.get(0));
        ft.show(list.get(1));
        ft.commit();

    }
    public void next2(View v) {
        //跳到下一个Fragment
        Switch aSwitch=((Setting2Fragment)list.get(1)).getaSwitch();

        if(aSwitch.isChecked()){
            //绑定SIM卡
         editor.putString("sim",tm.getSimSerialNumber());
            editor.commit();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(list.get(1));
            ft.show(list.get(2));
            ft.commit();
        }
       else {
            Toast.makeText(this,"请先绑定SIM卡",Toast.LENGTH_SHORT).show();
        }

    }
    public void next3(View v) {
        //跳到下一个Fragment
        EditText e_sade=((Setting3Fragment)list.get(2)).getE_safe();
        String e_safenumber=e_sade.getText().toString().trim();
        if(e_safenumber.length()==11){
            //保存安全号码
            editor.putString("safenumber",e_safenumber);
            editor.commit();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(list.get(2));
            ft.show(list.get(3));
            ft.commit();
        }
        else {
            Toast.makeText(this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
        }

    }
    public void next4(View v) {
        //跳到下一个Fragment
        Switch aSwitch=((Setting4Fragment)list.get(3)).getaSwitch();
        if(aSwitch.isChecked()){
            editor.putBoolean("flag",true);
            editor.commit();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            this.finish();
           // ft.hide(list.get(3));
            // ft.show(list.get(3));
            ft.commit();
        }
        else {
            Toast.makeText(this,"请先开启防盗保护",Toast.LENGTH_SHORT).show();
        }

    }

    public void preview2(View v) {
        //跳到下一个Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(list.get(1));
        ft.show(list.get(0));
        ft.commit();
    }
    public void preview3(View v) {
        //跳到下一个Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(list.get(2));
        ft.show(list.get(1));
        ft.commit();
    }
    public void preview4(View v) {
        //跳到下一个Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(list.get(3));
        ft.show(list.get(2));
        ft.commit();
    }

}
