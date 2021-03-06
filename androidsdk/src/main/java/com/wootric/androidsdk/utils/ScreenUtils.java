package com.wootric.androidsdk.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by maciejwitowski on 4/15/15.
 */
public class ScreenUtils {

    private static int screenHeight = 0;
    private static int screenWidth = 0;

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static float pxToDp(float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float dpToPx(int dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    public static void setViewsVisibility(View[] views, boolean visible) {
        final int visibility = (visible ? View.VISIBLE : View.GONE);
        for(int i = 0; i < views.length; i++) {
            views[i].setVisibility(visibility);
        }
    }

    public static void fadeInView(View view) {
        view.animate()
            .alpha(1)
            .setDuration(1000)
            .setInterpolator(new DecelerateInterpolator())
            .start();
    }
}
