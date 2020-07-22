package com.weather.lite.util;

import com.weather.lite.MyApplication;

public class UIUtil {

    public static int dp2px(int dp) {
        float density = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    public static int px2dp(int px) {
        float density = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static int sp2px(float spValue) {
        final float fontScale = MyApplication.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static void runOnUIThread(Runnable runnable) {
        if (isInMainThread()) {
            runnable.run();
        } else {
            MyApplication.getHandler().post(runnable);
        }
    }

    private static boolean isInMainThread() {
        int tid = android.os.Process.myTid();
        return tid == MyApplication.getMainThreadId();
    }

}