/*
 * Copyright (c) 2016.  @xiaobailong24
 */

package me.xiaobailong24.library.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.orhanobut.logger.Logger;

import me.xiaobailong24.library.BottomTab.BottomTabStrip;
import me.xiaobailong24.library.BottomTab.ChangeColorsView;
import me.xiaobailong24.library.BottomTab.Controller;
import me.xiaobailong24.library.BottomTab.OnTabItemSelectListener;
import me.xiaobailong24.library.BottomTab.TabItem;
import me.xiaobailong24.library.BottomTab.TabLayoutMode;
import me.xiaobailong24.library.BottomTab.TabStripBuild;
import me.xiaobailong24.library.BottomTab.TabStripListener;
import me.xiaobailong24.library.BottomTab.Utils;

/**
 * Created by LiuYinlong on 2016/9/19.
 */
public class BottomTab extends FrameLayout implements TabStripListener {
    private static final String TAG = BottomTab.class.getName();

    private Context mContext;

    private BottomTabStrip mBottomTabStrip;

    private ChangeColorsView mChangeColorsView;


    public BottomTab(Context context) {
        super(context);
        init(context);
    }

    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Logger.d(TAG, "init: ");
        mContext = context;
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
        setMeasuredDimension(getMeasuredWidth(), (int) Utils.dp2px(mContext, 56));
    }

    /**
     * 构建导航栏
     *
     * @return {@link TabStripBuild}
     */
    public TabStripBuild builder() {
        mBottomTabStrip = new BottomTabStrip(mContext);
        BottomTab.this.addView(mBottomTabStrip);

        LayoutParams lp = new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mBottomTabStrip.setLayoutParams(lp);

        return mBottomTabStrip.builder(BottomTab.this);
    }


    @Override
    public Controller onFinishBuild() {
        if ((mBottomTabStrip.mMode & TabLayoutMode.CHANGE_BACKGROUND_COLOR) > 0) {
            mChangeColorsView = new ChangeColorsView(mContext);

            mChangeColorsView.setBackgroundColor(
                    mBottomTabStrip.mTabItems.get(mBottomTabStrip.mIndex).getSelectedColor());

            BottomTab.this.addView(mChangeColorsView, 0);

            LayoutParams lp = new LayoutParams
                    (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mChangeColorsView.setLayoutParams(lp);
        }
        return mController;
    }

    @Override
    public void onSelect() {
        if(mChangeColorsView != null)
        {
            mChangeColorsView.addOvalColor(
                    mBottomTabStrip.mTabItems.get(mBottomTabStrip.mIndex).getSelectedColor(),touch_x,touch_y);
        }
    }

    @Override
    public void onSelect(float x, float y) {
        if(mChangeColorsView != null)
        {
            mChangeColorsView.addOvalColor(
                    mBottomTabStrip.mTabItems.get(mBottomTabStrip.mIndex).getSelectedColor(),x,y);
        }
    }

    @Override
    public void onNotMeasure(int color) {
        if(mChangeColorsView != null) {
            mChangeColorsView.setBackgroundColor(color);
        }
    }

    private float touch_x;
    private float touch_y;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        touch_x = ev.getX();
        touch_y = ev.getY();
        return super.onInterceptTouchEvent(ev);
    }

    Controller mController = new Controller() {
        @Override
        public void addTabItemClickListener(OnTabItemSelectListener listener) {
            mBottomTabStrip.mOnTabItemClickListener = listener;
        }

        @Override
        public void setMessageNumber(int index, int number) {
            mBottomTabStrip.mTabItems.get(index).setMessageNumber(number);
        }

        @Override
        public void setMessageNumber(Object tag, int number) {
            TabItem tabItem = selectTag(tag);
            if (tabItem != null) {
                tabItem.setMessageNumber(number);
            }
        }

        @Override
        public void setDisplayOval(int index, boolean display) {
            mBottomTabStrip.mTabItems.get(index).setDisplayOval(display);
        }

        @Override
        public void setDisplayOval(Object tag, boolean display) {
            TabItem tabItem = selectTag(tag);
            if (tabItem != null) {
                tabItem.setDisplayOval(display);
            }
        }

        @Override
        public void setSelect(int index) {
            mBottomTabStrip.setSetectManually(index);
        }

        @Override
        public void setSelect(Object tag) {
            for (int i = 0; i < mBottomTabStrip.mTabItems.size(); i++) {
                if (mBottomTabStrip.mTabItems.get(i).getTag().equals(tag)) {
                    setSelect(i);
                }
            }
        }

        @Override
        public int getSelected() {
            return mBottomTabStrip.mIndex;
        }

        @Override
        public Object getSelectedTag() {
            return mBottomTabStrip.mTabItems.get(mBottomTabStrip.mIndex).getTag();
        }

        @Override
        public void setBackgroundColor(@ColorInt int color) {
            BottomTab.this.setBackgroundColor(color);
        }

        @Override
        public void setBackground(Drawable drawable) {
            BottomTab.this.setBackgroundDrawable(drawable);
        }

        @Override
        public void setBackgroundResource(@DrawableRes int resid) {
            BottomTab.this.setBackgroundResource(resid);
        }

        private TabItem selectTag(Object tag) {
            for (TabItem tabItem : mBottomTabStrip.mTabItems) {
                if (tabItem.getTag().equals(tag)) {
                    return tabItem;
                }
            }
            return null;
        }
    };
}
