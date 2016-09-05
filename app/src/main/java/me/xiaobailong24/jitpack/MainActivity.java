/*
 * Copyright (c) 2016.  @xiaobailong24
 */

package me.xiaobailong24.jitpack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xiaobailong24.library.View.CircleProgress;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.progress)
    CircleProgress mCircleProgress;
    @BindView(R.id.start_btn)
    View mStartBtn;
    @BindView(R.id.stop_btn)
    View mStopBtn;
    @BindView(R.id.reset_btn)
    View mResetBtn;
    @BindView(R.id.out_seek)
    SeekBar mSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: ");

        ButterKnife.bind(this);
        mCircleProgress.startAnim();    //开始动画
        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mResetBtn.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, "onProgressChanged: ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: ");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: ");
                float factor = seekBar.getProgress() / 100f;
                mCircleProgress.setRadius(factor);
            }
        });

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: " + view.toString());
        if (view == mStartBtn) {
            mCircleProgress.startAnim();
        } else if (view == mStopBtn) {
            mCircleProgress.stopAnim();
        } else if (view == mResetBtn) {
            mCircleProgress.reset();
        }
    }
}




