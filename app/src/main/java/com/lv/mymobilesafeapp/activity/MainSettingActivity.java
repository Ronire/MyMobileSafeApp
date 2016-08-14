package com.lv.mymobilesafeapp.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lv.mymobilesafeapp.R;

public class MainSettingActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout rl_total,rl_jiesuan;
    private TextView number,date;
    private EditText flowedit;
    private Button flow_yes,flow_no;
    private View dialogview;
    private  AlertDialog numberdialog,datedialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);
        init();
    }
    private void init(){
        dialogview=View.inflate(this,R.layout.flowdialog,null);
        rl_total=(RelativeLayout)findViewById(R.id.rl_maintotal);
        rl_jiesuan=(RelativeLayout)findViewById(R.id.rl_jiesuan);
        number=(TextView)findViewById(R.id.mainflow_number) ;
        date=(TextView)findViewById(R.id.mainflow_date) ;
        flowedit=(EditText)dialogview.findViewById(R.id.flow_edit);
        flow_no=(Button)dialogview.findViewById(R.id.flowdialog_no);
        flow_yes=(Button)dialogview.findViewById(R.id.flowdialog_yes);
        sharedPreferences=getSharedPreferences("safe",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        flow_no.setOnClickListener(this);
        flow_yes.setOnClickListener(this);

        rl_jiesuan.setOnClickListener(this);
        rl_total.setOnClickListener(this);
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
            case R.id.rl_maintotal:
               numberdialog=new AlertDialog.Builder(this).setView(dialogview).show();
                break;
            case  R.id.rl_jiesuan:
                new AlertDialog.Builder(this).setItems(R.array.mydate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       editor.putInt("date",which);
                        editor.commit();

                        date.setText("每月"+(which+1)+"日");
                    }
                }).setTitle("结算日期").show();

                break;
            case R.id.flowdialog_yes:
              String n=  flowedit.getText().toString();
                editor.putString("totalnumber",n);
                editor.commit();
                number.setText(n+"MB");

                flowedit.setText("");
                numberdialog.dismiss();
                break;
            case R.id.flowdialog_no:
                flowedit.setText("");
                numberdialog.dismiss();
                break;
        }
    }
}
