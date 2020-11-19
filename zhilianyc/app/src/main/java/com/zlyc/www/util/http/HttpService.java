package com.zlyc.www.util.http;

import com.zlyc.www.bean.BaseResponse;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.address.AddressBean;
import com.zlyc.www.bean.coupon.MyCouponBean;
import com.zlyc.www.bean.coupon.ShopCouponBean;
import com.zlyc.www.bean.login.InfoBean;
import com.zlyc.www.bean.login.LoginBean;
import com.zlyc.www.bean.login.MineBean;
import com.zlyc.www.bean.login.VerifyImageBean;
import com.zlyc.www.bean.shop.GoodsDetailsBean;
import com.zlyc.www.bean.shop.HotGoodsBean;
import com.zlyc.www.bean.team.RankingBean;

import java.util.List;

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


    //--------------------user start
    //个人中心
    @POST("user/usercenter")
    Observable<BaseResponse<InfoBean>> info(
            @Body RequestBody body
    );

    //设置昵称
    @POST("user/reset_nickname")
    Observable<BaseResponse<EmptyModel>> resetNickname(
            @Body RequestBody body
    );

    //启动 H5 人脸核身
    @POST("user/auth_real_name")
    Observable<BaseResponse<EmptyModel>> authRealName(
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
    //--------------------user end



    //--------------------account start
    //设置交易密码
    @POST("account/pay_password")
    Observable<BaseResponse<EmptyModel>> payPwd(
            @Body RequestBody body
    );
    //--------------------account end


    //--------------------address start
    //我的收获地址列表
    @POST("address/list")
    Observable<BaseResponse<List<AddressBean>>> addressList(
            @Body RequestBody body
    );
    //添加我的收货地址
    @POST("address/add")
    Observable<BaseResponse<EmptyModel>> addressAdd(
            @Body RequestBody body
    );
    //编辑我的收货地址
    @POST("address/edit")
    Observable<BaseResponse<EmptyModel>> addressEdit(
            @Body RequestBody body
    );
    //删除我的收货地址
    @POST("address/delete")
    Observable<BaseResponse<EmptyModel>> addressDelete(
            @Body RequestBody body
    );


    //--------------------address end

    //--------------------shop start
    //获取热门商品
    @POST("shop/hot_goods")
    Observable<BaseResponse<List<HotGoodsBean>>> getHotGoods(
            @Body RequestBody body
    );

    //查询商品详情
    @POST("shop/goods_detail")
    Observable<BaseResponse<GoodsDetailsBean>> getGoodsDetails(
            @Body RequestBody body
    );
    //--------------------shop end


    //--------------------coupon start
    //我的仓储列表
    @POST("coupon/mine")
    Observable<BaseResponse<MyCouponBean>> getMyCoupon(
            @Body RequestBody body
    );

    //仓储商城
    @POST("coupon/shop")
    Observable<BaseResponse<List<ShopCouponBean>>> getShopCoupon(
            @Body RequestBody body
    );

    //够买仓储
    @POST("coupon/buy")
    Observable<BaseResponse<EmptyModel>> buyShopCoupon(
            @Body RequestBody body
    );

    //--------------------coupon end

    //--------------------team start
    //获取邀请排名
    @POST("team/invite_ranking")
    Observable<BaseResponse<List<RankingBean>>> inviteRanking(
            @Body RequestBody body
    );


    //--------------------team end




}
