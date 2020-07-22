package com.weather.lite.util;

import com.weather.lite.MyApplication;

import es.dmoral.toasty.Toasty;

public class ToastUtil {

    public static void showInfo(String messgae) {
        Toasty.info(MyApplication.getContext(), messgae).show();
    }

    public static void showInfoLong(String messgae) {
        Toasty.info(MyApplication.getContext(), messgae, Toasty.LENGTH_LONG).show();
    }

    public static void showError(String messgae) {
        Toasty.error(MyApplication.getContext(), messgae).show();
    }

    public static void showErrorLong(String messgae) {
        Toasty.error(MyApplication.getContext(), messgae, Toasty.LENGTH_LONG).show();
    }

    public static void showSuccess(String messgae) {
        Toasty.success(MyApplication.getContext(), messgae).show();
    }

    public static void showSuccessLong(String messgae) {
        Toasty.success(MyApplication.getContext(), messgae, Toasty.LENGTH_LONG).show();
    }

}