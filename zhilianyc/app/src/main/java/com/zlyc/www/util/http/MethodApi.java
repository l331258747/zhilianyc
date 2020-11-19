package com.zlyc.www.util.http;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by LGQ
 * Time: 2018/7/18
 * Function:
 */

public class MethodApi {


    //--------------------account start
    //设置交易密码
    public static void payPwd(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().payPwd(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    //--------------------account end


    //--------------------user start
    public static void verifyImageConfirm(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().verifyImageConfirm(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void verifyImage(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().verifyImage(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void mine(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().mine(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void realNameStatus(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().realNameStatus(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void info(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().info(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void resetNickname(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().resetNickname(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void authRealName(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().authRealName(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //登录
    public static void login(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().login(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void register(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().register(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void forgetPwd(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().forgetPwd(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //修改密码
    public static void resetPwd(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().resetPwd(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    //--------------------user end

    //--------------------address start
    public static void addressList(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().addressList(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void addressAdd(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().addressAdd(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void addressEdit(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().addressEdit(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void addressDelete(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().addressDelete(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    //--------------------address end


    //--------------------shop start
    public static void getHotGoods(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getHotGoods(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void getGoodsDetails(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getGoodsDetails(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //--------------------shop end

    //--------------------coupon end
    public static void getMyCoupon(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getMyCoupon(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void getShopCoupon(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getShopCoupon(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void buyShopCoupon(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().buyShopCoupon(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //--------------------coupon end

    //--------------------team end
    public static void inviteRanking(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().inviteRanking(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //--------------------team end

    private static RequestBody getRequestBody(Map<String, String> params){
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                new JSONObject(params).toString());
        return requestBody;
    }

}
