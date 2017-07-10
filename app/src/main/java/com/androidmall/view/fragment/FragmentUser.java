package com.androidmall.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidmall.R;
import com.androidmall.model.login.LoginBean;
import com.androidmall.view.activity.BackLoginActivity;
import com.androidmall.view.activity.LoginActivity;

/**
 * 作者：刘秉卿
 * 时间：2017/6/9
 * 类作用：
 */

public class FragmentUser extends Fragment implements View.OnClickListener {

    private TextView usernameTextView, collectionGoodsTextView,
            collectionStoreTextView, myFootprintTextView,
            allOrder, Unpaid, to_be_delivered, to_be_received,
            be_evaluated, refunds, shipping_address, system_settings;
    private String key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        SharedPreferences mySp = getActivity().getSharedPreferences("MySp", getActivity().MODE_PRIVATE);
        String userName = mySp.getString("userName", "");
        key = mySp.getString("key", "");
        usernameTextView.setText(userName);
    }

    private void initView() {
        //点击登录
        usernameTextView = (TextView) getView().findViewById(R.id.usernameTextView);
        usernameTextView.setOnClickListener(this);
        //商品收藏
        collectionGoodsTextView = (TextView) getView().findViewById(R.id.collectionGoodsTextView);
        collectionGoodsTextView.setOnClickListener(this);
        //店铺收藏
        collectionStoreTextView = (TextView) getView().findViewById(R.id.collectionStoreTextView);
        collectionStoreTextView.setOnClickListener(this);
        //我的足迹
        myFootprintTextView = (TextView) getView().findViewById(R.id.myFootprintTextView);
        myFootprintTextView.setOnClickListener(this);
        //全部订单
        allOrder = (TextView) getView().findViewById(R.id.allOrder);
        allOrder.setOnClickListener(this);
        //未付款
        Unpaid = (TextView) getView().findViewById(R.id.Unpaid);
        Unpaid.setOnClickListener(this);
        //待发货
        to_be_delivered = (TextView) getView().findViewById(R.id.to_be_delivered);
        to_be_delivered.setOnClickListener(this);
        //待收货
        to_be_received = (TextView) getView().findViewById(R.id.to_be_received);
        to_be_received.setOnClickListener(this);
        //待评价
        be_evaluated = (TextView) getView().findViewById(R.id.be_evaluated);
        be_evaluated.setOnClickListener(this);
        //退款/货
        refunds = (TextView) getView().findViewById(R.id.refunds);
        refunds.setOnClickListener(this);


        //收货地址
        shipping_address = (TextView) getView().findViewById(R.id.shipping_address);
        shipping_address.setOnClickListener(this);

        //系统设置
        system_settings = (TextView) getView().findViewById(R.id.system_settings);
        system_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (usernameTextView.getText().equals("点击登录")) {
            switch (v.getId()) {
                case R.id.usernameTextView:
                    getIntent();
                    break;
                case R.id.collectionGoodsTextView:
                    getIntent();
                    break;
                case R.id.collectionStoreTextView:
                    getIntent();
                    break;
                case R.id.myFootprintTextView:
                    getIntent();
                    break;
                case R.id.allOrder:
                    getIntent();
                    break;
                case R.id.Unpaid:
                    getIntent();
                    break;
                case R.id.to_be_delivered:
                    getIntent();
                    break;
                case R.id.to_be_received:
                    getIntent();
                    break;
                case R.id.be_evaluated:
                    getIntent();
                    break;
                case R.id.refunds:
                    getIntent();
                    break;
                case R.id.shipping_address:
                    getIntent();
                    break;
                case R.id.system_settings:
                    getIntent();
                    break;
            }
        } else {
            switch (v.getId()) {
                case R.id.usernameTextView:
                    Intent intentBack = new Intent(getActivity(), BackLoginActivity.class);

                    startActivity(intentBack);
                    break;
                case R.id.collectionGoodsTextView:

                    break;
                case R.id.collectionStoreTextView:

                    break;
                case R.id.myFootprintTextView:

                    break;
                case R.id.allOrder:

                    break;
                case R.id.Unpaid:

                    break;
                case R.id.to_be_delivered:

                    break;
                case R.id.to_be_received:

                    break;
                case R.id.be_evaluated:

                    break;
                case R.id.refunds:

                    break;
                case R.id.shipping_address:

                    break;
                case R.id.system_settings:

                    break;
            }
        }
    }

    private void getIntent() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
