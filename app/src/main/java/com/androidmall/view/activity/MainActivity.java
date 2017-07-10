package com.androidmall.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.androidmall.R;
import com.androidmall.view.fragment.FragmentCart;
import com.androidmall.view.fragment.FragmentClass;
import com.androidmall.view.fragment.FragmentHome;
import com.androidmall.view.fragment.FragmentUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ViewPager vp;
    private RadioGroup rg;
    private RadioButton rbhome,rbclass,rbcart,rbuser;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        fragmentList = Arrays.asList(new FragmentHome(),new FragmentClass(),new FragmentCart(),new FragmentUser());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        rg = (RadioGroup) findViewById(R.id.rg);
        rbhome = (RadioButton) findViewById(R.id.rbhome);
        rbclass = (RadioButton) findViewById(R.id.rbclass);
        rbcart = (RadioButton) findViewById(R.id.rbcart);
        rbuser = (RadioButton) findViewById(R.id.rbuser);
        rg.setOnCheckedChangeListener(this);
        vp.setHorizontalScrollBarEnabled(false);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int current = 0;
        switch (checkedId) {
            case R.id.rbhome: {
                current = 0;
                break;
            }
            case R.id.rbclass: {
                current = 1;
                break;
            }
            case R.id.rbcart: {
                current = 2;
                break;
            }
            case R.id.rbuser: {
                current = 3;
                break;
            }
        }
        if (vp.getCurrentItem() != current) {
            vp.setCurrentItem(current, false);
        }
    }
}
