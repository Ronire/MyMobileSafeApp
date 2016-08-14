package com.lv.mymobilesafeapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 吕亚平 on 2016/7/27.
 */
public class RadarView extends View {
    private int width,height;
    private Paint bigPaint,betterPaint,smallPaint;
    private Paint recPaint,fillCirclePaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private   int[] colors={Color.argb(0x000,219,234,222),Color.argb(0x44f,219,234,222)};
    private int startangle,endangle;
    private static final int NEED_INVALIDATE = 0X6666;
    private  boolean flag=true;



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(flag){
                switch (msg.what) {
                    case NEED_INVALIDATE:

                        startangle+=60;
                        if(startangle>=360){

                            startangle=5;
                            Log.i("msg",startangle+"  ");
                        }

                        invalidate();
                        sendEmptyMessageDelayed(NEED_INVALIDATE, 150);
                        break;
                }
            }
        }
    };


    public RadarView(Context context) {
        super(context);
        init();

    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }


    private void init(){
        startangle=245;
        //endangle=245+90;
        bigPaint=new Paint();
        bigPaint.setStyle(Paint.Style.FILL);
        bigPaint.setColor(Color.argb(0x025,222,227,222));
        bigPaint.setAntiAlias(true);


        betterPaint=new Paint();
        betterPaint.setStyle(Paint.Style.FILL);
        betterPaint.setColor(Color.argb(0x037,219,234,222));
        betterPaint.setAntiAlias(true);

        smallPaint=new Paint();
        smallPaint.setStyle(Paint.Style.FILL);
        smallPaint.setColor(Color.argb(0xfff,255,255,255));
        smallPaint.setAntiAlias(true);
        handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 100);




    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高
        Log.i("msg","onMeasure++++++++++++++");
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        recPaint=new Paint();
        recPaint.setStyle(Paint.Style.FILL);
        recPaint.setAntiAlias(true);
        float[] positions={startangle/360f,(startangle+30)/360f};
        SweepGradient sweepGradient=new SweepGradient(width/2,height/2,colors,positions);
        recPaint.setShader(sweepGradient);

        //画圆
        mCanvas.drawCircle(width/2,height/2,300,bigPaint);
        mCanvas.drawCircle(width/2,height/2,160,betterPaint);

     RectF rectF=new RectF(width/2-300,height/2-300,width/2+300,height/2+300);
        mCanvas.drawArc(rectF,startangle,60,true,recPaint);
        mCanvas.drawCircle(width/2,height/2,50,smallPaint);
        canvas.drawBitmap(mBitmap,0,0,null);

        Log.i("msg","ondraw+++++++++++++++++++++++++");

    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
