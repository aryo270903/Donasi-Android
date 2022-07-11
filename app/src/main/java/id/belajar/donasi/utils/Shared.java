package id.belajar.donasi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import id.belajar.donasi.MyApplication;

public class Shared {
    public static String getValue(String key){
        MyApplication app = MyApplication.getInstance();
        SharedPreferences pref = app.getSharedPreferences(app.getPackageName(), Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public static void setValue(String key, String value){
        MyApplication app = MyApplication.getInstance();
        SharedPreferences pref = app.getSharedPreferences(app.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean getBooleanValue(String key){
        MyApplication app = MyApplication.getInstance();
        SharedPreferences pref = app.getSharedPreferences(app.getPackageName(), Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public static void setBooleanValue(String key, boolean value){
        MyApplication app = MyApplication.getInstance();
        SharedPreferences pref = app.getSharedPreferences(app.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}

