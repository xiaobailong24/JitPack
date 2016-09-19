/*
 * Copyright (c) 2016.  @xiaobailong24
 */

package me.xiaobailong24.library.BottomTab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by LiuYinlong on 2016/9/19.
 * 点击切换颜色的View
 */
public class ChangeColorsView extends View {
    private static final String TAG = ChangeColorsView.class.getName();

    public ChangeColorsView(Context context) {
        super(context);
        init(context);
    }

    public ChangeColorsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChangeColorsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context mContext;

    private float R;

    List<Oval> mOvals;

    private Paint mPaint;

    private void init(Context context) {
        com.orhanobut.logger.Logger.d(TAG, "init: ");
        this.mContext = context;
        this.mOvals = new ArrayList<>();
        this.mPaint = new Paint();
        mPaint.setAntiAlias(true);

        setFitsSystemWindows(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Iterator<Oval> iterator = mOvals.iterator();
        while (iterator.hasNext()) {
            Oval oval = iterator.next();
            mPaint.setColor(oval.color);
            if (oval.r < R) {
                oval.r += R / 30f;
                RectF rectF = new RectF(oval.x - oval.r, oval.y - oval.r,
                        oval.x + oval.r, oval.y + oval.r);
                canvas.drawOval(rectF, mPaint);
            } else {
                ChangeColorsView.this.setBackgroundColor(oval.color);
                canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
                //单线程下防止ConcurrentModificationException异常
                iterator.remove();
            }
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);

        R = (float) Math.sqrt(getMeasuredHeight() * getMeasuredHeight()
                + getMeasuredWidth() * getMeasuredWidth());

    }

    /**
     * 添加一个圆形颜色
     *
     * @param color 颜色
     * @param x     X坐标
     * @param y     Y坐标
     */
    public void addOvalColor(int color, float x, float y) {
        Oval oval = new Oval();
        oval.color = color;
        oval.r = Utils.dp2px(mContext, 2);
        oval.x = x;
        oval.y = y;

        mOvals.add(oval);
        invalidate();
    }

    class Oval {
        public int color;
        public float r;
        public float x;
        public float y;
    }
}
