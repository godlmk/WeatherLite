package com.weather.lite.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.weather.lite.MyApplication;

public class SharedPrefsUtil {

    private static SharedPrefsUtil INSTANCE;

    private static SharedPreferences prefs;

    private SharedPrefsUtil(String name) {
        prefs = MyApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void createInstance(String name) {
        if (INSTANCE == null) {
            INSTANCE = new SharedPrefsUtil(name);
        }
    }

    public static String getString(String key, @Nullable String defValue) {
        return prefs.getString(key, defValue);
    }

    public static void putString(String key, @Nullable String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return prefs.getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static int getInt(String key, int defValue) {
        return prefs.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

}