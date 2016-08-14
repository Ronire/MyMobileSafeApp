package com.lv.mymobilesafeapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 吕亚平 on 2016/7/25.
 */
public class CircleAnimationBar extends View {
    private int height;
    private int width;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPathPaint;
    private Paint mTextPaint;
    private Paint mCirclePaint,mbigCirclepaint;
    private int maxProgress = 100;
    private int currentProgress = 0;
    private String text;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NEED_INVALIDATE:
                    //更新时间
                    count += 5;
                    if (count > 80) {
                        count = 0;
                    }
                    invalidate();
                    sendEmptyMessageDelayed(NEED_INVALIDATE, 50);
                    break;
            }

        }
    };


    public CircleAnimationBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        //初始化绘制路径的画笔
        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setColor(Color.argb(0xff, 72, 177, 82));
        mPathPaint.setStyle(Paint.Style.FILL);//设置为填充，默认为填充，这里我们还是定义下
        //设置两张图片相交时的模式
        mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);

        mCirclePaint.setColor(Color.argb(0xff, 52, 147, 63));


        //设置外面大圆的画笔
        mbigCirclepaint = new Paint();
        mbigCirclepaint.setAntiAlias(true);
        mbigCirclepaint.setColor(Color.argb(0xff,52, 147, 63));
        mbigCirclepaint.setStrokeWidth(15);
       // mbigCirclepaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        mbigCirclepaint.setStyle(Paint.Style.STROKE);


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.argb(0xff, 255, 252, 255));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(70);
        handler.sendEmptyMessageDelayed(NEED_INVALIDATE, 50);


    }

    public CircleAnimationBar(Context context) {
        super(context);
    }

    private int count = 0;
    private static final int NEED_INVALIDATE = 0X6666;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);//设置宽和高
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas.drawCircle(width / 2, height / 2, 300, mCirclePaint);
        //通过Path绘制贝塞尔曲线
        mPath.reset();
        mPath.moveTo(width/2+280, (height / 2 + 300) - (currentProgress * 600f / maxProgress));
        mPath.lineTo(width, height / 2 + 350);
        mPath.lineTo(count, height / 2 + 350);
        mPath.lineTo(count, (height / 2 + 300) - (currentProgress * 600f / maxProgress));


        //设置曲线
        for (int i = 0; i < 10; i++) {
            mPath.rQuadTo(20, 5, 40, 0);
            mPath.rQuadTo(20, -5, 40, 0);
        }
        mPath.close();
        //将贝塞尔曲线绘制到Bitmap的Canvas上
        mCanvas.drawPath(mPath, mPathPaint);
        //将Bitmap绘制到View的Canvas上
        mCanvas.drawText(text, width / 2, height / 2, mTextPaint);
        mCanvas.drawCircle(width / 2, height / 2, 300, mbigCirclepaint);

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }


    public int getProgress() {
        return currentProgress;
    }

    public void setProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    private  double width(float height){
        float h=200-height;
        double w=Math.sqrt(40000-height*height);
        return w;
    }
}
