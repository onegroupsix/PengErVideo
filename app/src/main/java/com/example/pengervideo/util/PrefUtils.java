package com.example.pengervideo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 专门访问和设置SharePreference的工具类, 保存和配置一些设置信息
 *
 * @author Kevin
 */
public class PrefUtils {

    private static final String SP_NAME = "util";

   /* public static void putBoolean(Context ctx, String key, boolean value) {
        SharedPreferences pref = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        pref.edit().putBoolean(key, value).commit();
    }*/
   public static void putBoolean(Context ctx, String key, boolean value) {
       SharedPreferences pref = ctx.getSharedPreferences(SP_NAME,
               Context.MODE_PRIVATE);

       pref.edit().putBoolean(key, value).commit();}

   /* public static boolean getBoolean(Context ctx, String key, Boolean defaultValue) {
        SharedPreferences pref = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }*/
   public static boolean getBoolean(Context ctx, String key,
                                    boolean defaultValue) {
       SharedPreferences pref = ctx.getSharedPreferences(SP_NAME,
               Context.MODE_PRIVATE);

       return pref.getBoolean(key, defaultValue);
   }

    public static void putString(Context ctx, String key, String str){
        SharedPreferences pref = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        pref.edit().putString(key,str).commit();
    }

    public static String getString(Context ctx, String key, String defultValue){
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key,defultValue);
    }

    public static void putInt(Context ctx, String key, int defultValue){
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key,defultValue).commit();
    }
    public static int getInt(Context ctx, String key, int defultValue){
        SharedPreferences sp = ctx.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key,defultValue);
    }
}
