package com.example.pengervideo.manager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.pengervideo.bean.CodeBoy;
import com.example.pengervideo.util.DIYDialog;
import com.example.pengervideo.util.PrefUtils;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public abstract class MyOkhttpManager {
    Context context;
    DIYDialog diyDialog;
    Map<String,String> map=new HashMap<>();
    String soft_id="105";
    boolean isdialog=true;
    public MyOkhttpManager(Context context) {
        this.context = context;
        diyDialog=new DIYDialog(context);
    }
    public MyOkhttpManager(Context context, boolean isdialog) {
        this.context = context;
        diyDialog=new DIYDialog(context);
        this.isdialog=isdialog;
    }
    //post请求
    public MyOkhttpManager post(final Map<String,String> params, final String url){
        //转菊
        if (isdialog)
        diyDialog.showProgressDialog();
        //加入默认参数
            map.put("token", PrefUtils.getString(context,"token",""));
            map.put("phone", "1");
            map.put("soft_id", soft_id);
            map.put("username",PrefUtils.getString(context, "loginname", ""));
        //根据请求个例加入其他参数
        if (params!=null)
        map.putAll(params);
        final MyOkHttp myOkHttp=new MyOkHttp();
        myOkHttp
                .post()
                .url(url)
                .params(map)
                .tag(context)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, final String response) {
                        Log.e(url,response);
                        if (isdialog)
                        diyDialog.endDialog();
                        Success(response,myOkHttp);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.e("请求失败",error_msg);
                        showToast(context,error_msg);
                        if (isdialog)
                        diyDialog.endDialog();
                        Another(error_msg,myOkHttp);
                    }
                });
        return null;
    }
    //get请求
    public void get(final String url){
        //转菊
        if (isdialog)
            diyDialog.showProgressDialog();
//设置开启cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
        final MyOkHttp myOkHttp=new MyOkHttp(okHttpClient);
        myOkHttp
                .get()
                .url(url)
                .tag(context)
                .addHeader("authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtYW4iOiJzYW1zdW5nIiwiYnJhbmQiOiJzYW1zdW5nIiwic3lzdGVtTmFtZSI6IkFuZHJvaWQiLCJzeXN0ZW1WZXJzaW9uIjoiNS4xLjEiLCJ1bmlxdWUiOiI2MDI2MDQwMTA1OTQyODcyIiwiaWF0IjoxNTI5ODIwNTM2LCJleHAiOjE1Mjk4NTY1MzZ9.-TxMVCffXlcF9Fexe44VogJvJT1q9bmxdiWZjdWqbu4")
                .addHeader("content-type","application/json")
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, final String response) {
                        if (isdialog)
                            diyDialog.endDialog();
                        Log.e(url,response);
                        Success(response,myOkHttp);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.e("请求失败",error_msg);
                        if (isdialog)
                            diyDialog.endDialog();
                        showToast(context,error_msg);
                        Another(error_msg,myOkHttp);
                    }
                });
    }
    //Code不为特殊情况时用于与UI交互的方法(在调用时实例化)
    public abstract void Success(String response,MyOkHttp myOkHttp);
    public abstract void Another(String response,MyOkHttp myOkHttp);
    /**
     * Toast使用：避免重复弹出提示
     * */
    private static Toast sixflyToast = null;
    public static void showToast(Context context, String text) {
        if (sixflyToast == null) {
            sixflyToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            sixflyToast.setText(text);
            sixflyToast.setDuration(Toast.LENGTH_SHORT);
        }

        sixflyToast.show();
    }
}
