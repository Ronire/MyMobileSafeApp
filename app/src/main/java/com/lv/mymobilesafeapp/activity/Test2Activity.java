package com.lv.mymobilesafeapp.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lv.mymobilesafeapp.R;
import com.lv.mymobilesafeapp.view.MyCircleAnimationBar;

public class Test2Activity extends AppCompatActivity {
    private MyCircleAnimationBar bar;
    private int progress;
    //通过这个可以控制速度
    private static final int PROGRESS= 0X0003;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0x006){
                progress=progress+5;
                if (progress <= 100) {
                    bar.setProgress(progress);
                    sendEmptyMessageDelayed(PROGRESS, 100);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        bar=(MyCircleAnimationBar) findViewById(R.id.testbar);

    }

    public void test2(View view){

        new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i=0;i<100;i++){
                    try{
                        Thread.sleep(1000);
                        handler.sendEmptyMessage(0x006);
                       // handler.sendEmptyMessageDelayed(0x006,100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        }.start();


    }
}
