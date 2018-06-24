package com.example.pengervideo.fragment;




import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pengervideo.R;
import com.example.pengervideo.adapter.DetailPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {
    @BindView(R.id.sliding_tabs_fh)
    TabLayout slidingTabsFh;
    @BindView(R.id.img_search_fh)
    ImageView imgSearchFh;
    @BindView(R.id.viewpager_fh)
    ViewPager viewpagerFh;
    Unbinder unbinder;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        //        为viewpager设置适配器
        viewpagerFh.setAdapter(new DetailPageAdapter(getActivity(), fragmentManager));

        slidingTabsFh.setupWithViewPager(viewpagerFh);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
