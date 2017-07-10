package com.androidmall.presenter;

import com.androidmall.model.utils.HttpUtils;
import com.androidmall.view.iview.IRegView;

public class RegPresenter extends BasePresenter<IRegView> {

    private String uri="http://169.254.13.193/mobile/";

    public <T>void getRegData(String username, String password,String password_confirm,String email, String client,Class<T> t){

        HttpUtils.getReg(uri, username, password, password_confirm, email, client, new HttpUtils.CallbackData<T>() {
            @Override
            public void callBak(T t) {
                if (getMvpView() != null){
                    getMvpView().callBacStr(t);
                }
            }
        });
    }
}
