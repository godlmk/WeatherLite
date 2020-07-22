package com.weather.lite;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.kongzue.dialog.util.DialogSettings;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.weather.lite.util.LogUtil;
import com.weather.lite.util.SharedPrefsUtil;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

        SharedPrefsUtil.createInstance("Config");
        LogUtil.init(true);
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);
            return new ClassicsHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) ->
                new ClassicsFooter(context).setDrawableSize(20));
    }

    static {
        DialogSettings.isUseBlur = DialogSettings.checkRenderscriptSupport(context);
        DialogSettings.modalDialog = true;
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
        DialogSettings.theme = DialogSettings.THEME.LIGHT;
        DialogSettings.tipTheme = DialogSettings.THEME.DARK;
        DialogSettings.cancelable = false;
        DialogSettings.cancelableTipDialog = false;
        DialogSettings.autoShowInputKeyboard = true;
    }


}