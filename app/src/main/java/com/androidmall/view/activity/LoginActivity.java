package com.androidmall.view.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidmall.R;
import com.androidmall.model.login.LoginBean;
import com.androidmall.presenter.LoginPresenter;
import com.androidmall.view.iview.ILoginView;

/**
 * 作者：刘秉卿
 * 时间：2017/6/12
 * 类作用：
 */

public class LoginActivity extends Activity implements ILoginView<LoginBean> {

    private TextView registeredtextview,tv_findpassword;
    private TextView textView_login_back;
    private EditText editView_login_username;
    private EditText editView_login_password;
    private Button button_login;
    private LoginPresenter loginPresenter;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        initView();
        initData();
        setListener();
    }

    private void initData() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        SharedPreferences mySp = getSharedPreferences("MySp", MODE_PRIVATE);
        edit = mySp.edit();
    }
    private void setListener() {
        registeredtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
            }
        });
        textView_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });
        //找回密码的监听
        tv_findpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        textView_login_back = (TextView) findViewById(R.id.textView_login_back);
        editView_login_username = (EditText) findViewById(R.id.editView_login_username);
        editView_login_password = (EditText) findViewById(R.id.editView_login_password);
        button_login = (Button) findViewById(R.id.button_login);
        registeredtextview = (TextView) findViewById(R.id.registeredTextview);
        tv_findpassword = (TextView) findViewById(R.id.tv_findpassword);


    }
    private void setLogin() {
        String username = editView_login_username.getText().toString();
        String password = editView_login_password.getText().toString();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        loginPresenter.getLoginData(username,password,"android",LoginBean.class);
    }
    @Override
    public void callBackStr(LoginBean loginBean) {
        Log.e("my", "callBackStr: "+loginBean.getCode());
        if (loginBean.getCode() == 200){
            edit.putString("userName",loginBean.getDatas().getUsername().toString());
            edit.putString("key",loginBean.getDatas().getKey().toString());
            edit.commit();
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"账户或密码错误，请从新输入",Toast.LENGTH_SHORT).show();
        }
    }
}
