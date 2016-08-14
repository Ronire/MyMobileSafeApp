package com.lv.mymobilesafeapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by 吕亚平 on 2016/7/28.
 */
public class CircleProgressBar extends View {


    private int width, height;
    private Paint circlePaint, textPaint, saomiaoPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private SweepGradient sweepGradient;
    private int[] color = {Color.argb(0x005, 219, 234, 222), Color.argb(0x44f, 219, 234, 222)};
    private int angle = 0;
    private Bitmap copyBitmap;
    private boolean flag = true;
    private String text = "0";
    private String type = "kb";


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (flag) {
                switch (msg.what) {
                    case 0x001:

                        angle += 60;
                        if (angle >= 360) {

                            angle = 0;

                        }

                        invalidate();
                        sendEmptyMessageDelayed(0x001, 150);
                        break;
                }
            }
        }
    };

    public CircleProgressBar(Context context) {

        super(context);
        invalidate();

    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.rgb(255, 255, 255));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(80);


        saomiaoPaint = new Paint();
        saomiaoPaint.setStyle(Paint.Style.FILL);
        saomiaoPaint.setColor(Color.rgb(255, 255, 255));
        saomiaoPaint.setTextAlign(Paint.Align.CENTER);
        saomiaoPaint.setTextSize(40);
        handler.sendEmptyMessageDelayed(0x001, 150);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("msg", "onDraw");
        super.onDraw(canvas);
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(15);
        float[] positions = {0.1f, 1f};
        Matrix matrix = new Matrix();
        matrix.setRotate(angle, width / 2, height / 2);
        sweepGradient = new SweepGradient(width / 2, height / 2, color, positions);
        sweepGradient.setLocalMatrix(matrix);
        circlePaint.setShader(sweepGradient);
        RectF rectF = new RectF(width / 2 - 260, height / 2 - 260, width / 2 + 260, height / 2 + 260);

        mCanvas.drawText(text + " " + type, width / 2, height / 2, textPaint);

        mCanvas.drawText("正在扫描", width / 2, height / 2 + 70, saomiaoPaint);

        mCanvas.drawArc(rectF, 0, 360, true, circlePaint);

        canvas.drawBitmap(mBitmap, 0, 0, null);


    }


    public String getType() {
        invalidate();
        return type;
    }

    public void setType(String type) {

        this.type = type;
        invalidate();
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
        invalidate();
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {

        this.flag = flag;
        invalidate();
    }
}
