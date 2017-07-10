package com.androidmall.model.utils;

import android.util.Log;
import android.widget.Toast;

import com.androidmall.BuildConfig;
import com.androidmall.model.home.HomeBean;
import com.androidmall.model.login.LoginBean;
import com.androidmall.view.activity.BackLoginActivity;
import com.androidmall.view.iview.IHomeBiz;
import com.androidmall.view.iview.ILoginBiz;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {

    public static void getLogin(String uri, String username, String password, String client, final CallbackData callbackData){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri)
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ILoginBiz iLoginBiz = retrofit.create(ILoginBiz.class);
        Observable<LoginBean> observable = iLoginBiz.getLoginData("login", username, password, client);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        callbackData.callBak(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static void getBackLogin(String uri, String key, String username, String client, final CallbackData callbackData){

        AjaxParams ajaxParams = new AjaxParams();
        ajaxParams.put("key", key);
        ajaxParams.put("client", client);
        ajaxParams.put("username", username);
        FinalHttp mfinalHttp = new FinalHttp();
        mfinalHttp.post(uri, ajaxParams, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                callbackData.callBak(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    public static void getReg(String uri, String username, String password,String password_confirm, String email, String client, final CallbackData callbackData){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri)
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ILoginBiz iLoginBiz = retrofit.create(ILoginBiz.class);
        Observable<LoginBean> observable = iLoginBiz.getRegData("login","register", username, password, password_confirm, email, client);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        Log.e("=====", "onNext: "+value.getCode());
                        callbackData.callBak(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static <T>void getDataFromInternet(String uri, final CallbackData callbackData, final Class<T> clazz){

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10,TimeUnit.SECONDS);

        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        IHomeBiz iBeanBiz = retrofit.create(IHomeBiz.class);

        iBeanBiz.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("=====", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.e("=====", "onNext: ");
                        callbackData.callBak(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("=====", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("=====", "onComplete: ");
                    }
                });

    }


    public interface CallbackData<T>{
        void callBak(T t);
    }

}
