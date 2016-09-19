/*
 * Copyright (c) 2016.  @xiaobailong24
 */

package me.xiaobailong24.jitpack;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xiaobailong24.jitpack.fragment.BaseFragment;
import me.xiaobailong24.jitpack.fragment.CameraFragment;
import me.xiaobailong24.jitpack.fragment.CompassFragment;
import me.xiaobailong24.jitpack.fragment.HelpFragment;
import me.xiaobailong24.jitpack.fragment.SearchFragment;
import me.xiaobailong24.library.BottomTab.Controller;
import me.xiaobailong24.library.BottomTab.OnTabItemSelectListener;
import me.xiaobailong24.library.BottomTab.TabLayoutMode;
import me.xiaobailong24.library.View.BottomTab;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    int[] testColors = {0xFF00796B, 0xFF5B4947, 0xFF607D8B, 0xFFF57C00, 0xFFF57C00};

    @BindView(R.id.tab)
    BottomTab mBottomTab;
    Controller controller;
    List<BaseFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.e(TAG, "onCreate: ");

        ButterKnife.bind(this);

        initFragment();
        initBottomTab();

    }

    private void initBottomTab() {
        mBottomTab = (BottomTab) findViewById(R.id.tab);
        controller = mBottomTab.builder()
                .addTabItem(android.R.drawable.ic_menu_camera, "Camera", testColors[0])
                .addTabItem(android.R.drawable.ic_menu_compass, "Compass", testColors[1])
                .addTabItem(android.R.drawable.ic_menu_search, "Search", testColors[2])
                .addTabItem(android.R.drawable.ic_menu_help, "Help", testColors[3])
                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .build();
        controller.addTabItemClickListener(listener);

    }

    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(new CameraFragment());
        mFragments.add(new CompassFragment());
        mFragments.add(new SearchFragment());
        mFragments.add(new HelpFragment());

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, mFragments.get(0));
        transaction.commit();

    }


    OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {
            Logger.d(TAG, "onSelected: " + index + "    TAG:    " + tag.toString());

            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, mFragments.get(index));
            transaction.commit();
        }

        @Override
        public void onRepeatClick(int index, Object tag) {
            Logger.d(TAG, "onRepeatClick: " + index + "    TAG:    " + tag.toString());

        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy: ");
    }

}
