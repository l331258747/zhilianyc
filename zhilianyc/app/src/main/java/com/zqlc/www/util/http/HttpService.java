package com.zqlc.www.util.http;

import com.zqlc.www.bean.BasePageModel;
import com.zqlc.www.bean.BaseResponse;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.account.MyBillListBean;
import com.zqlc.www.bean.account.UserAccountBean;
import com.zqlc.www.bean.ad.BannerBean;
import com.zqlc.www.bean.ad.ConfigBean;
import com.zqlc.www.bean.address.AddressBean;
import com.zqlc.www.bean.coupon.MyCouponBean;
import com.zqlc.www.bean.coupon.ShopCouponBean;
import com.zqlc.www.bean.login.InfoBean;
import com.zqlc.www.bean.login.LoginBean;
import com.zqlc.www.bean.login.MineBean;
import com.zqlc.www.bean.login.VerifyImageBean;
import com.zqlc.www.bean.message.AppUpdateBean;
import com.zqlc.www.bean.news.AnnouncementBean;
import com.zqlc.www.bean.news.StudyCentreBean;
import com.zqlc.www.bean.otc.MyOtcListBean;
import com.zqlc.www.bean.otc.OtcDetailBean;
import com.zqlc.www.bean.otc.OtcInfoBean;
import com.zqlc.www.bean.otc.OtcListBean;
import com.zqlc.www.bean.otc.TradeRuleBean;
import com.zqlc.www.bean.shop.GoodsClassBean;
import com.zqlc.www.bean.shop.GoodsDetailsBean;
import com.zqlc.www.bean.shop.GoodsListBean;
import com.zqlc.www.bean.shop.HotGoodsBean;
import com.zqlc.www.bean.shop.OrderDetailBean;
import com.zqlc.www.bean.shop.OrderListBean;
import com.zqlc.www.bean.team.MyTeamDetailBean;
import com.zqlc.www.bean.team.RankingBean;
import com.zqlc.www.bean.team.TeamInviteBean;
import com.zqlc.www.bean.user.AuthRealInfoBean;
import com.zqlc.www.bean.user.AuthRealNameBean;
import com.zqlc.www.bean.user.AuthRealPayBean;
import com.zqlc.www.bean.user.ListReginBean;
import com.zqlc.www.bean.user.TaskBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    //短信
    @POST("user/send_code")
    Observable<BaseResponse<EmptyModel>> sendCode(
            @Body RequestBody body
    );

    //修改用户头像
    @Multipart
    @POST("user/reset_head_img")
    Observable<BaseResponse<String>> resetHead(
            @Part("uid") RequestBody uid,
            @Part MultipartBody.Part headImg
    );

    //设置昵称
    @POST("user/reset_nickname")
    Observable<BaseResponse<EmptyModel>> resetNickname(
            @Body RequestBody body
    );

    //启动 H5 人脸核身
    @POST("user/auth_real_name")
    Observable<BaseResponse<AuthRealNameBean>> authRealName(
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

    //生成图片滑块
    @POST("user/real_name_info")
    Observable<BaseResponse<AuthRealInfoBean>> realNameInfo(
            @Body RequestBody body
    );
    //根据上级区域代码获取下级区域列表集合
    @POST("user/list_region")
    Observable<BaseResponse<List<ListReginBean>>> listRegion(
            @Body RequestBody body
    );
    //--------------------user end


    //--------------------account start

    @POST("account/real_name_pay")
    Observable<BaseResponse<AuthRealPayBean>> authRealPay(
            @Body RequestBody body
    );
    @POST("account/fee_ratio")
    Observable<BaseResponse<Float>> feeRatio(
            @Body RequestBody body
    );

    //设置交易密码
    @POST("account/pay_password")
    Observable<BaseResponse<EmptyModel>> payPwd(
            @Body RequestBody body
    );

    //获取用户账户
    @POST("account/get_user_account")
    Observable<BaseResponse<UserAccountBean>> getUserAccount(
            @Body RequestBody body
    );
    //设置交易密码
    @POST("account/update_account_no")
    Observable<BaseResponse<EmptyModel>> updateAccountNo(
            @Body RequestBody body
    );

    //金豆账单
    @POST("account/beans_record")
    Observable<BaseResponse<BasePageModel<MyBillListBean>>> beansRecord(
            @Body RequestBody body
    );

    //可售额度账单
    @POST("account/sellable_beans_record")
    Observable<BaseResponse<BasePageModel<MyBillListBean>>> sellableBeansRecord(
            @Body RequestBody body
    );

    //劳动值账单
    @POST("account/labor_record")
    Observable<BaseResponse<BasePageModel<MyBillListBean>>> laborRecord(
            @Body RequestBody body
    );

    //贡献度账单
    @POST("account/contribution_record")
    Observable<BaseResponse<BasePageModel<MyBillListBean>>> contributionRecord(
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
    Observable<BaseResponse<BasePageModel<HotGoodsBean>>> getHotGoods(
            @Body RequestBody body
    );

    //获取商品分类
    @POST("shop/category")
    Observable<BaseResponse<List<GoodsClassBean>>> getGoodsClass(
            @Body RequestBody body
    );

    //按分类获取商品
    @POST("shop/category_goods")
    Observable<BaseResponse<BasePageModel<GoodsListBean>>> getGoodsList(
            @Body RequestBody body
    );

    //创建订单
    @POST("shop/create_order")
    Observable<BaseResponse<EmptyModel>> createOrder(
            @Body RequestBody body
    );

    //查询商品详情
    @POST("shop/goods_detail")
    Observable<BaseResponse<GoodsDetailsBean>> getGoodsDetails(
            @Body RequestBody body
    );

    //查看订单列表
    @POST("shop/order")
    Observable<BaseResponse<List<OrderListBean>>> getOrderList(
            @Body RequestBody body
    );

    //获取订单详情
    @POST("shop/order_detail")
    Observable<BaseResponse<OrderDetailBean>> getOrderDetail(
            @Body RequestBody body
    );

    //支付订单
    @POST("shop/pay_order")
    Observable<BaseResponse<EmptyModel>> payOrder(
            @Body RequestBody body
    );
    //取消订单
    @POST("shop/cancel_order")
    Observable<BaseResponse<EmptyModel>> cancelOrder(
            @Body RequestBody body
    );
    //确认收货
    @POST("shop/receive_order")
    Observable<BaseResponse<EmptyModel>> receiveOrder(
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

    //团队详情
    @POST("team/detail")
    Observable<BaseResponse<MyTeamDetailBean>> getTeamDetail(
            @Body RequestBody body
    );

    //我的直推用户
    @POST("team/myinvite")
    Observable<BaseResponse<TeamInviteBean>> getTeamInvite(
            @Body RequestBody body
    );

    //--------------------team end

    //--------------------otc start
    //我的OTC订单列表
    @POST("otc/my_otc_list")
    Observable<BaseResponse<BasePageModel<MyOtcListBean>>> getMyOtcList(
            @Body RequestBody body
    );

    //查询订单详情
    @POST("otc/order_detail")
    Observable<BaseResponse<OtcDetailBean>> getOtcDetail(
            @Body RequestBody body
    );

    //上传付款凭证
    @Multipart
    @POST("otc/payproof_uploads")
    Observable<BaseResponse<EmptyModel>> getOtcVoucher(
            @Part("uid") RequestBody uid,
            @Part("beansSendId") RequestBody beansSendId,
            @Part MultipartBody.Part file
    );

    //在点击买卖时校验订单是否有效
    @POST("otc/capture_order")
    Observable<BaseResponse<EmptyModel>> getOtcCheck(
            @Body RequestBody body
    );

    //处理OTC订单
    @POST("otc/deal_otc")
    Observable<BaseResponse<EmptyModel>> getOtcHandle(
            @Body RequestBody body
    );


    //获取当前OTC交易的整体信息
    @POST("otc/info")
    Observable<BaseResponse<OtcInfoBean>> getOtcInfo(
            @Body RequestBody body
    );

    //获取OTC订单列表
    @POST("otc/list")
    Observable<BaseResponse<BasePageModel<OtcListBean>>> getOtcList(
            @Body RequestBody body
    );

    //获取当前开放的渠道 当前otc可以的交易方式，
    // 0 只开求购通道
    // 1 只开转让通道
    // 2 两个通道都开
    // 当为0时候、app前端只开放求购渠道 当为1时候、app前端只开放转让渠道 当为2时候、app前端转让和求购的渠道都开放创
    @POST("otc/open")
    Observable<BaseResponse<String>> getOtcOpen(
            @Body RequestBody body
    );

    //发布买单
    @POST("otc/publish_buy")
    Observable<BaseResponse<EmptyModel>> sendOtcBuy(
            @Body RequestBody body
    );

    //发布卖单
    @POST("otc/publish_sell")
    Observable<BaseResponse<EmptyModel>> sendOtcSell(
            @Body RequestBody body
    );
    //京豆交易规则 返回值的内容包括 sell_small_min 转让散单最低单价
    @POST("otc/beans_trade_rule")
    Observable<BaseResponse<TradeRuleBean>> beansTradeRule(
            @Body RequestBody body
    );

    //上传申诉图片
    @Multipart
    @POST("otc/uploads")
    Observable<BaseResponse<String>> otcFeedback(
            @Part("uid") RequestBody uid,
            @Part MultipartBody.Part file
    );


    //--------------------otc end

    //--------------------user start
    @POST("user/mission")
    Observable<BaseResponse<TaskBean>> getTask(
            @Body RequestBody body
    );


    //--------------------user end

    //--------------------ad start
    @POST("ad/index")
    Observable<BaseResponse<List<BannerBean>>> getBanner(
            @Body RequestBody body
    );

    @POST("ad/ad_config_android")
    Observable<BaseResponse<ConfigBean>> getAdConfig(
            @Body RequestBody body
    );
    @POST("ad/view_video_callback")
    Observable<BaseResponse<ConfigBean>> viewVideoCallback(
            @Body RequestBody body
    );
    @POST("ad/read_news_callback")
    Observable<BaseResponse<EmptyModel>> readNewsCallback(
            @Body RequestBody body
    );
    @POST("ad/play_game_callback")
    Observable<BaseResponse<EmptyModel>> playGameCallback(
            @Body RequestBody body
    );
    @POST("ad/award_callback")
    Observable<BaseResponse<EmptyModel>> awardCallback(
            @Body RequestBody body
    );
    //签到回调 (奖励额外金豆)
    @POST("ad/signin_callback")
    Observable<BaseResponse<String>> signin(
            @Body RequestBody body
    );
    //--------------------ad end

    //--------------------news start
    //公告中心
    @POST("news/study_centre")
    Observable<BaseResponse<BasePageModel<StudyCentreBean>>> getStudyCentre(
            @Body RequestBody body
    );

    //学习中心
    @POST("news/announcement")
    Observable<BaseResponse<BasePageModel<AnnouncementBean>>> getAnnouncement(
            @Body RequestBody body
    );

    //--------------------ad end

    //--------------------message start

    @POST("message/update")
    Observable<BaseResponse<AppUpdateBean>> getAppUpdate(
            @Body RequestBody body
    );

    @POST("shop/is_open")
    Observable<BaseResponse<String>> shopIsOpen(
            @Body RequestBody body
    );
    //--------------------message end


}
