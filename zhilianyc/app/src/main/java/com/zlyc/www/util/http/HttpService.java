package com.zlyc.www.util.http;

import com.zlyc.www.bean.BaseResponse;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.login.InfoBean;
import com.zlyc.www.bean.login.LoginBean;
import com.zlyc.www.bean.login.MineBean;
import com.zlyc.www.bean.login.VerifyImageBean;

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
    //实名状态
    @POST("user/real_name_status")
    Observable<BaseResponse<String>> realNameStatus(
            @Body RequestBody body
    );

    //登录系列   start
    //登录
    @POST("user/login")
    Observable<BaseResponse<LoginBean>> login(
            @Body RequestBody body
    );
    //注册
    @POST("user/register")
    Observable<BaseResponse<EmptyModel>> register(
            @Body RequestBody body
    );
    //忘记密码
    @POST("user/forget_password")
    Observable<BaseResponse<EmptyModel>> forgetPwd(
            @Body RequestBody body
    );

    //修改密码
    @POST("user/reset_password")
    Observable<BaseResponse<EmptyModel>> resetPwd(
            @Body RequestBody body
    );

    //校验滑图确认信息
    @POST("user/verify_image_confirm")
    Observable<BaseResponse<EmptyModel>> verifyImageConfirm(
            @Body RequestBody body
    );

    //生成图片滑块
    @POST("user/verify_image")
    Observable<BaseResponse<VerifyImageBean>> verifyImage(
            @Body RequestBody body
    );

    //登录系列   end


    //个人信息系列   start
    //设置交易密码
    @POST("account/pay_password")
    Observable<BaseResponse<EmptyModel>> payPwd(
            @Body RequestBody body
    );
    //个人信息系列   end
}
