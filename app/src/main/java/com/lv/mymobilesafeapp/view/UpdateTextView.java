package com.lv.mymobilesafeapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by 吕亚平 on 2016/7/26.
 */
public class UpdateTextView extends TextView{
    private Paint mTextPaint;
    private static final int NEED_INVALIDATE = 0X6666;
    private int count=0;
    private int time;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NEED_INVALIDATE:

                    if(UpdateTextView.this.isShown()){
                        time++;

                    }
                    //更新时间
                   /* count += 5;
                    if (count > 80) {
                        count = 0;
                    }*/
                    invalidate();
                    sendEmptyMessageDelayed(NEED_INVALIDATE, 1000);
                    break;
            }

        }
    };


    public UpdateTextView(Context context) {
        super(context);
        init();
    }

    public UpdateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        /*mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.argb(0xff, 255, 252, 255));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(70);*/
        handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 1000);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        setText(time+"");
        Log.i("msg",count+"++++++++++++++++++++++++++++++");

        super.onDraw(canvas);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;

    }
}
