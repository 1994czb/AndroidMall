package com.androidmall.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidmall.R;
import com.google.zxing.activity.CaptureActivity;
import com.utils.CommonUtil;

/**
 * 作者：刘秉卿
 * 时间：2017/6/9
 * 类作用：
 */

public class FragmentClass extends Fragment {

    private ImageView qrcode;
    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;
    private ListView lv_first_tier_category;
    private ExpandableListView edlv_secondary_classification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        scanQrCode();
    }

    private void scanQrCode() {
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonUtil.isCameraCanUse()){
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else{
                    Toast.makeText(getActivity(),"请打开此应用的摄像头权限！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        qrcode = (ImageView) getView().findViewById(R.id.imageview_fragment_home_qrcode);
        lv_first_tier_category = (ListView) getView().findViewById(R.id.lv_first_tier_category);
        edlv_secondary_classification = (ExpandableListView) getView().findViewById(R.id.edlv_secondary_classification);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            //将扫描出的信息显示出来
//            qrCodeText.setText(scanResult);
            Toast.makeText(getActivity(),scanResult, Toast.LENGTH_SHORT).show();
        }
    }
}