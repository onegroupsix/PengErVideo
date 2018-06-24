package com.example.pengervideo.activity;



import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.pengervideo.R;
import com.example.pengervideo.fragment.CartoonFragment;
import com.example.pengervideo.fragment.HomeFragment;
import com.example.pengervideo.fragment.SearchFragment;
import com.example.pengervideo.fragment.XiaoVideoFragment;
import com.example.pengervideo.http.Api;
import com.example.pengervideo.manager.MyOkhttpManager;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.tsy.sdk.myokhttp.MyOkHttp;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Credentials;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    HomeFragment homeFragment;
    CartoonFragment cartoonFragment;
    SearchFragment searchFragment;
    XiaoVideoFragment xiaoVideoFragment;
    Authenticator authenticator=new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return super.getPasswordAuthentication();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentManager fManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) fManager.findFragmentByTag("homeFragment");
            cartoonFragment = (CartoonFragment) fManager.findFragmentByTag("cartoonFragment");
            searchFragment = (SearchFragment) fManager.findFragmentByTag("searchFragment");
            xiaoVideoFragment = (XiaoVideoFragment) fManager.findFragmentByTag("xiaoVideoFragment");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation;
        if(ori == mConfiguration.ORIENTATION_PORTRAIT){
            init();
        }

    }
    private void init(){
        shitJWT();
        String basic=Credentials.basic("AUINI4ALS", "{\"alg\":\"HS256\",\"typ\":\"JWT\"}{\"man\":\"samsung\",\"brand\":\"samsung\",\"systemName\":\"Android\",\"systemVersion\":\"5.1.1\",\"unique\":\"6026040105942872\",\"iat\":1529820536,\"exp\":1529856536}");
        Log.e("",basic);
        initBNB();
        setDefaultFragment();
        new MyOkhttpManager(this) {
            @Override
            public void Success(String response, MyOkHttp myOkHttp) {

            }

            @Override
            public void Another(String response, MyOkHttp myOkHttp) {

            }
        }.get(Api.NewVideo);
    }
    /**
     * 底部导航栏初始化
     * */
    private void initBNB(){
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar //值得一提，模式跟背景的设置都要在添加tab前面，不然不会有效果。
                .setActiveColor(R.color.zhuti)//选中颜色 图标和文字
                .setInActiveColor("#8e8e8e")//默认未选择颜色
                .setBarBackgroundColor(R.color.gray);//默认背景色
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"首页"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"卡通"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"搜索"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher,"小视频"))
                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();//所有的设置需在调用该方法前完成
        bottomNavigationBar //设置lab点击事件
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        switch (position){
                            case 0:
                                hideFragment(transaction);
                                transaction.show(homeFragment);
                                transaction.commit();
                                break;
                            case 1:
                                if (cartoonFragment==null){
                                    cartoonFragment =new CartoonFragment();
                                    transaction.add(R.id.fragment, cartoonFragment,"cartoonFragment");
                                }
                                hideFragment(transaction);
                                transaction.show(cartoonFragment);
                                transaction.commit();
                                break;
                            case 2:
                                if (searchFragment==null){
                                    searchFragment =new SearchFragment();
                                    transaction.add(R.id.fragment, searchFragment,"searchFragment");
                                }
                                hideFragment(transaction);
                                transaction.show(searchFragment);
                                transaction.commit();
                                break;
                            case 3:
                                if (xiaoVideoFragment==null){
                                    xiaoVideoFragment =new XiaoVideoFragment();
                                    transaction.add(R.id.fragment, xiaoVideoFragment,"xiaoVideoFragment");
                                }
                                hideFragment(transaction);
                                transaction.show(xiaoVideoFragment);
                                transaction.commit();
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                });
    }
    /**
     * Fragment处理
     * */
    //初始化
    private void setDefaultFragment() {
        homeFragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment, homeFragment,"homeFragment");
        transaction.commit();
    }
    /**
     * 去除（隐藏）所有的Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (searchFragment != null) {
            transaction.hide(searchFragment);
        }
        if (cartoonFragment != null) {
            transaction.hide(cartoonFragment);
        }
        if (xiaoVideoFragment != null) {
            transaction.hide(xiaoVideoFragment);
        }
    }

    private void shitJWT(){
        Map<String,Object> map= new HashMap<>();
        map.put("man","samsung");
        map.put("brand","samsung");
        map.put("systemName","Android");
        map.put("systemVersion","5.1.1");
        map.put("unique","6026040105942872");
        map.put("iat","1529820536");
        map.put("exp","1529856536");
        try {
            creatToken(map);
            Log.e("aaaaaa------",creatToken(map));
        } catch (JOSEException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.创建一个32-byte的密匙
     */

    private static final byte[] secret = "avbobo".getBytes();


    //生成一个token
    public static String creatToken(Map<String,Object> payloadMap) throws JOSEException {

        //3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(String.valueOf(new JSONObject(payloadMap)));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //建立一个密匙

        JWSSigner jwsSigner = new MACSigner(secret);

        //签名
        jwsObject.sign(jwsSigner);

        //生成token
        return jwsObject.serialize();
    }
}
