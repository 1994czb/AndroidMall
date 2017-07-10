package com.androidmall.presenter;


import com.androidmall.model.utils.HttpUtils;
import com.androidmall.view.iview.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private String uri="http://169.254.13.193/mobile/";

    public <T>void getLoginData(String username, String password, String client,Class<T> t){

        HttpUtils.getLogin(uri, username, password, client, new HttpUtils.CallbackData<T>() {
            @Override
            public void callBak(T t) {
                getMvpView().callBackStr(t);
            }
        });
    }
}
