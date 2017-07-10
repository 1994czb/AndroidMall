package com.androidmall.view.iview;

import com.androidmall.model.home.HomeBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IHomeBiz {
    @GET("mobile/index.php?act=goods_class")
    Observable<HomeBean> getData();
}
