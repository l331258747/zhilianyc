package com.zlyc.www.util.http;

import com.zlyc.www.bean.BaseResponse;
import com.zlyc.www.bean.EmptyModel;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LGQ
 * Time: 2018/7/17
 * Function: 请求
 */

public interface HttpService {

//    @GET("alipay/appPay")
//    Observable<AliPay> appPay();
//
//    //登录系列   start
//    //登录
//    @FormUrlEncoded
//    @POST("user/login")
//    Observable<BaseResponse<LoginModel>> login(
//            @Field("mobile") String mobile,
//            @Field("password") String password,
//            @Field("loginType") int loginType
//    );

    //登录系列   start
    //登录
    @POST("user/login")
    Observable<BaseResponse<EmptyModel>> login(
            @Body RequestBody body
    );


}
