package com.zlyc.www.util.http;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

    public static void beansRecord(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().beansRecord(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void sellableBeansRecord(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().sellableBeansRecord(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void laborRecord(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().laborRecord(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void contributionRecord(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().contributionRecord(getRequestBody(params)); //在HttpServer中
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

    public static void resetHead(String uid, File headImg, DisposableObserver subscriber) {
        MultipartBody.Part part = fileToMultipartBodyParts(headImg,"headImg");
        Observable observable = HttpMethods.getInstance().getHttpService().resetHead(getStringPart(uid), part); //在HttpServer中
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

    public static void getGoodsClass(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getGoodsClass(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void getGoodsList(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getGoodsList(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void createOrder(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().createOrder(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getGoodsDetails(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getGoodsDetails(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOrderList(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOrderList(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOrderDetail(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOrderDetail(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void receiveOrder(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().receiveOrder(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void cancelOrder(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().cancelOrder(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void payOrder(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().payOrder(getRequestBody(params)); //在HttpServer中
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

    public static void getTeamDetail(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getTeamDetail(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getTeamInvite(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getTeamInvite(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


    //--------------------team end

    //--------------------otc end
    public static void getMyOtcList(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getMyOtcList(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOtcDetail(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOtcDetail(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOtcVoucher(String uid, String beansSendId, File file, DisposableObserver subscriber) {
        MultipartBody.Part part = fileToMultipartBodyParts(file);
        Observable observable = HttpMethods.getInstance().getHttpService().getOtcVoucher(getStringPart(uid), getStringPart(beansSendId), part); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOtcCheck(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOtcCheck(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOtcHandle(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOtcHandle(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOtcInfo(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOtcInfo(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOtcList(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOtcList(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    public static void getOtcOpen(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getOtcOpen(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void sendOtcBuy(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().sendOtcBuy(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void sendOtcSell(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().sendOtcSell(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void otcFeedback(String uid, File file, DisposableObserver subscriber) {
        MultipartBody.Part part = fileToMultipartBodyParts(file);
        Observable observable = HttpMethods.getInstance().getHttpService().otcFeedback(getStringPart(uid), part); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


    //--------------------otc end

    //--------------------user end
    public static void getTask(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getTask(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    //--------------------user end

    //--------------------ad start
    public static void getBanner(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getBanner(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //--------------------ad end

    //--------------------news start
    public static void getAnnouncement(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getAnnouncement(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    public static void getStudyCentre(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().getStudyCentre(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    //--------------------news end

    private static RequestBody getRequestBody(Map<String, String> params) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                new JSONObject(params).toString());
        return requestBody;
    }

    private static MultipartBody.Part fileToMultipartBodyParts(File file) {
        return fileToMultipartBodyParts(file,"file");
    }
    private static MultipartBody.Part fileToMultipartBodyParts(File file,String name) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(name, file.getName(), requestBody);
        return part;
    }

    private static RequestBody getStringPart(String str) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), str);
        return requestBody;
    }

}
