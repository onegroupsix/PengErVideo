package com.example.pengervideo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pengervideo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "param";
    @BindView(R.id.bt_day)
    Button btDay;
    @BindView(R.id.bt_month)
    Button btMonth;
    @BindView(R.id.bt_year)
    Button btYear;
    @BindView(R.id.ll_biaoqian)
    LinearLayout llBiaoqian;
    @BindView(R.id.rv_rmqdorzxyp)
    RecyclerView rvRmqdorzxyp;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters

    private int mParam;//用来表示当前需要展示的是哪一页

    public DetailInfoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailInfoFragment newInstance(int param) {
        DetailInfoFragment fragment = new DetailInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getInt(ARG_PARAM);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_info, container, false);
        unbinder = ButterKnife.bind(this, view);

        //根据mParam来判断当前展示的是哪一页，根据页数的不同展示不同的信息
        switch (mParam) {
            case 1:
                llBiaoqian.setVisibility(View.VISIBLE);
                break;
            case 0:
                llBiaoqian.setVisibility(View.GONE);
                break;
            default:
                break;

        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_day, R.id.bt_month, R.id.bt_year})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_day:
                break;
            case R.id.bt_month:
                break;
            case R.id.bt_year:
                break;
        }
    }
}
