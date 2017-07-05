package com.example.unitonedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * name ：李飞宇
 * Date: 2017/7/5
 * desc: 自定义View矩形
 */

public class OrthogonActivity extends View {
    public OrthogonActivity(Context context) {
        this(context, null);
    }

    public OrthogonActivity(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrthogonActivity(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, 400, 400, paint);
    }




}
