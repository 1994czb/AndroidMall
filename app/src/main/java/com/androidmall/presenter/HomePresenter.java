package com.androidmall.presenter;

import com.androidmall.model.utils.HttpUtils;
import com.androidmall.view.iview.IHomeView;

public class HomePresenter extends BasePresenter<IHomeView> {
    String uri="http://169.254.13.193/";
    public <T>void getDataFromServer(final Class<T> t){
        HttpUtils.getDataFromInternet(uri, new HttpUtils.CallbackData<T>() {
            @Override
            public void callBak(T t) {
                getMvpView().callBackStr(t);
            }
        },t);
    }
}
