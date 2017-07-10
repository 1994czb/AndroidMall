package com.androidmall.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.androidmall.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 作者：刘秉卿
 * 时间：2017/6/12
 * 类作用：
 */

public class RegisteredActivity extends Activity {

    private EditText usernameRegistered, passwordRegistered, passwordRegisteredAgain, emailRegistered;
    private Button registeredButton;
    private TextView textviewReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
    }

    private void initData() {
        String usernameReg = usernameRegistered.getText().toString();
        String passwordReg = passwordRegistered.getText().toString();
        String passwordRegAgain = passwordRegisteredAgain.getText().toString();
        String emailReg = emailRegistered.getText().toString();

        if (usernameReg.isEmpty()) {
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if (passwordReg.isEmpty()) {
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if (passwordRegAgain.isEmpty()) {
            Toast.makeText(this,"请再次输入密码",Toast.LENGTH_SHORT).show();
            return;
        }

        if (emailReg.isEmpty()) {
            Toast.makeText(this,"请输入邮箱地址",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordReg.equals(passwordRegAgain)) {
            Toast.makeText(this,"密码不一样",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordReg.equals(passwordRegAgain)) {
            Toast.makeText(this,"密码不一样",Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmailAddress(emailReg)) {
            Toast.makeText(this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
            return;
        }

        registeredButton.setEnabled(false);
        registeredButton.setText("正在注册...");
        AjaxParams ajaxParams = new AjaxParams();
        ajaxParams.put("username", usernameReg);
        ajaxParams.put("password", passwordReg);
        ajaxParams.put("password_confirm", passwordRegAgain);
        ajaxParams.put("email", emailReg);
        ajaxParams.put("client", "android");
        FinalHttp mfinalHttp = new FinalHttp();
        mfinalHttp.post("http://169.254.13.193/mobile/index.php?act=login&op=register", ajaxParams, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                if (isNcJson(o.toString())) {
                    Toast.makeText(RegisteredActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    try {
                        registeredButton.setText("注 册");
                        registeredButton.setEnabled(true);
                        Log.e("myMessage"," 注册失败 "+o.toString());
                        JSONObject jsonObject = new JSONObject(o.toString());
                        String datas = jsonObject.getString("datas");
                        jsonObject = new JSONObject(datas);
                        Toast.makeText(RegisteredActivity.this,jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Toast.makeText(RegisteredActivity.this,"注册失败,请重试",Toast.LENGTH_SHORT).show();
                registeredButton.setText("注 册");
                registeredButton.setEnabled(true);
            }
        });

    }

    private void initView() {
        usernameRegistered = (EditText) findViewById(R.id.usernameRegistered);
        passwordRegistered = (EditText) findViewById(R.id.passwordRegistered);
        passwordRegisteredAgain = (EditText) findViewById(R.id.passwordRegisteredAgain);
        emailRegistered = (EditText) findViewById(R.id.emailRegistered);
        registeredButton = (Button) findViewById(R.id.registeredButton);
        textviewReg = (TextView) findViewById(R.id.textviewReg);

        registeredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        textviewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public static boolean isEmailAddress(String url) {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(url);
        return matcher.matches();
    }
    public static boolean isNcJson(String content) {

        return content.length() > 8 && content.contains("{") && content.contains("}") && content.contains("\"code\":200") && !content.contains("\"error\"");

    }
}
