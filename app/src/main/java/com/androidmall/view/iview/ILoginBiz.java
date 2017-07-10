package com.androidmall.view.iview;

import com.androidmall.model.login.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILoginBiz {
    @FormUrlEncoded
    @POST("index.php")
    Observable<LoginBean> getLoginData(@Field("act") String sort,
                                       @Field("username") String username,
                                       @Field("password") String password,
                                       @Field("client") String client);

    @FormUrlEncoded
    @POST("index.php")
    Observable<LoginBean> getRegData(@Field("act") String sort,
                                     @Field("op") String op,
                                     @Field("username") String username,
                                     @Field("password") String password,
                                     @Field("password_confirm") String password_confirm,
                                     @Field("email") String email,
                                     @Field("client") String client);

}
