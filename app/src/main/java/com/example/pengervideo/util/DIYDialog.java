package com.example.pengervideo.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class DIYDialog {
    MaterialDialog dialog;
    Context context;
    boolean bb=false;

    public boolean isBb() {
        return bb;
    }

    public void setBb(boolean bb) {
        this.bb = bb;
    }

    public DIYDialog(Context context) {
        this.context = context;
    }

    public void showProgressDialog(){
        bb=true;
        dialog=new MaterialDialog.Builder(context)
                .title("")
                .content("请稍后...")
                .progress(true, 0)
                .show();
    }

    public void endDialog(){
        bb=false;
        dialog.dismiss();
    }
}
