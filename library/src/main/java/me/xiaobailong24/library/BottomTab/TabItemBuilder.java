/*
 * Copyright (c) 2016.  @xiaobailong24
 */

package me.xiaobailong24.library.BottomTab;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

/**
 * Created by LiuYinlong on 2016/9/19.
 * 开放的导航按钮构建入口
 */
public class TabItemBuilder {
    private static final String TAG = TabItemBuilder.class.getName();
    private TabItem mTabItem;

    private Context mContext;

    public TabItemBuilder(@NotNull Context context) {
        mContext = context;
    }

    public TabItemBuild create() {
        mTabItem = new TabItem(mContext);
        return mTabItem.builder(this);
    }

    protected TabItem getTabItem() {
        return mTabItem;
    }
}
