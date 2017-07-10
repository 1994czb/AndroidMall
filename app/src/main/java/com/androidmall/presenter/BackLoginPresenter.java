package com.androidmall.presenter;

import com.androidmall.model.utils.HttpUtils;
import com.androidmall.view.iview.IBackLoginView;

/**
 * 作者：刘秉卿
 * 时间：2017/6/22
 * 类作用：
 */

public class BackLoginPresenter extends BasePresenter<IBackLoginView>{
    private String uri="http://169.254.13.193/mobile/index.php?act=logout";

    public <T>void getBackLoginData(String key, String client,String username){

        HttpUtils.getBackLogin(uri,key, client, username, new HttpUtils.CallbackData<T>() {
            @Override
            public void callBak(T t) {
                getMvpView().callBackStr(t);
            }
        });
    }
}
