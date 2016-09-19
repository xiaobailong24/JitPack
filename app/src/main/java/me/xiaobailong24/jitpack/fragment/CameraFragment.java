/*
 * Copyright (c) 2016.  @xiaobailong24
 */

package me.xiaobailong24.jitpack.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.xiaobailong24.jitpack.R;

/**
 * Created by LiuYinlong on 2016/9/19.
 */
public class CameraFragment extends BaseFragment{
    private static final String TAG = CameraFragment.class.getName();

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera,container,false);
    }

    @Override
    public void init() {

    }
}
