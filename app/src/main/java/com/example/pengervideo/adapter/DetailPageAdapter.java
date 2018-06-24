package com.example.pengervideo.adapter;


import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pengervideo.fragment.DetailInfoFragment;

public class DetailPageAdapter extends FragmentPagerAdapter {

    private static int PAGE_COUNT;//表示要展示的页面数量
    private Context mContext;

    public DetailPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        PAGE_COUNT = 2;

    }

    @Override
    public Fragment getItem(int position) {
        return DetailInfoFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {//设置标题
        switch (position) {
            case 0:
                return "最新影片";
            case 1:
                return "热门强档";

            default:
                break;

        }
        return null;
    }
}
