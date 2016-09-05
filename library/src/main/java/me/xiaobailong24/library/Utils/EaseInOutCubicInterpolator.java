/*
 * Copyright (c) 2016.  @xiaobailong24
 */

package me.xiaobailong24.library.Utils;

import android.animation.TimeInterpolator;

/**
 * Created by LiuYinlong on 2016/9/5.
 */
public class EaseInOutCubicInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        if ((input *= 2) < 1.0f) {
            return 0.5f * input * input * input;
        }
        input -= 2;
        return 0.5f * input * input * input + 1;
    }

}
