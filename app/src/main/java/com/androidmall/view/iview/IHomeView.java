package com.androidmall.view.iview;

public interface IHomeView<T> extends IMvpView {
    void callBackStr(T t);
    void callBackErr(String errMsg, String errCode);
}
