package com.androidmall.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.androidmall.R;
import com.androidmall.model.utils.HttpUtils;
import com.androidmall.presenter.BackLoginPresenter;
import com.androidmall.view.iview.IBackLoginView;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：刘秉卿
 * 时间：2017/6/22
 * 类作用：
 */

public class BackLoginActivity extends Activity implements IBackLoginView {

    private AlertDialog dialog;
    private BackLoginPresenter backLoginPresenter;
    private String userName;
    private String key;
    private SharedPreferences mySp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_back);
        initData();
        TextView textView_login_back = (TextView) findViewById(R.id.textview_back_login);
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        backLoginPresenter = new BackLoginPresenter();
        backLoginPresenter.attachView(this);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog = new AlertDialog.Builder(BackLoginActivity.this).create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setContentView(R.layout.dialog_query);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                    TextView titleTextView = (TextView) window.findViewById(R.id.titleTextView);
//                    titleTextView.setText(title);
                    TextView contentTextView = (TextView) window.findViewById(R.id.contentTextView);
//                    contentTextView.setText(content);
                    TextView confirmTextView = (TextView) window.findViewById(R.id.confirmTextView);
//                    confirmTextView.setOnClickListener(clickListener);
                    TextView cancelTextView = (TextView) window.findViewById(R.id.cancelTextView);
                    cancelTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    confirmTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*AjaxParams ajaxParams = new AjaxParams();
                            ajaxParams.put("key", key);
                            ajaxParams.put("client", "android");
                            ajaxParams.put("username", userName);
                            FinalHttp mfinalHttp = new FinalHttp();
                            mfinalHttp.post("http://169.254.13.193/mobile/index.php?act=logout", ajaxParams, new AjaxCallBack<Object>() {
                                @Override
                                public void onSuccess(Object o) {
                                    super.onSuccess(o);
                                    if (o.toString().contains("1")) {
                                    Toast.makeText(BackLoginActivity.this,"退出成功",Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(BackLoginActivity.this,"退出失败",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t, int errorNo, String strMsg) {
                                    super.onFailure(t, errorNo, strMsg);
                                    Toast.makeText(BackLoginActivity.this,"退出失败",Toast.LENGTH_SHORT).show();
                                }
                            });*/

                            backLoginPresenter.getBackLoginData(key,"android",userName);

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        mySp = getSharedPreferences("MySp", MODE_PRIVATE);
        userName = mySp.getString("userName", "");
        key = mySp.getString("key", "");
    }


    @Override
    public void callBackStr(Object o) {
        if (o.toString().contains("1")) {
            Toast.makeText(BackLoginActivity.this,"退出成功",Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(BackLoginActivity.this,"退出失败",Toast.LENGTH_SHORT).show();
        }
    }
}
