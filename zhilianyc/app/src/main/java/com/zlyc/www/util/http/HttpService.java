package com.zlyc.www.util.http;

import com.zlyc.www.bean.BaseResponse;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.login.InfoBean;
import com.zlyc.www.bean.login.LoginBean;
import com.zlyc.www.bean.login.MineBean;

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

    //个人中心
    @POST("user/usercenter")
    Observable<BaseResponse<InfoBean>> info(
            @Body RequestBody body
    );

    //我的主页信息
    @POST("user/mine")
    Observable<BaseResponse<MineBean>> mine(
            @Body RequestBody body
    );

    //登录系列   start
    //登录
    @POST("user/login")
    Observable<BaseResponse<LoginBean>> login(
            @Body RequestBody body
    );

    //修改密码
    @POST("user/reset_password")
    Observable<BaseResponse<EmptyModel>> resetPwd(
            @Body RequestBody body
    );


    //登录系列   end

}
