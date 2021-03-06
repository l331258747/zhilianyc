package com.zqlc.www.mvp.otc;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.otc.OtcDetailBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;
import com.zqlc.www.util.md5.MD5Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OtcDetailPresenter implements OtcDetailContract.Presenter {

    Context context;
    OtcDetailContract.View iView;

    public OtcDetailPresenter(Context context, OtcDetailContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getOtcDetail(String uid,String beansSendId) {
        ResponseCallback listener = new ResponseCallback<OtcDetailBean>() {
            @Override
            public void onSuccess(OtcDetailBean data) {
                iView.getOtcDetailSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcDetailFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("beansSendId", beansSendId);

        MethodApi.getOtcDetail(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void getOtcVoucher(String uid, String beansSendId, File file) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.getOtcVoucherSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcVoucherFailed(errorMsg);
            }
        };

        MethodApi.getOtcVoucher(uid,beansSendId, file,new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void getOtcCheck(String uid, String beansSendId) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.getOtcCheckSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcCheckFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("beansSendId", beansSendId);

        MethodApi.getOtcCheck(params, new OnSuccessAndFaultSub(listener, context, false));
    }

    @Override
    public void getOtcHandle(String uid, int sendStatus, String beansSendId) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.getOtcHandleSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcHandleFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("sendStatus", sendStatus + "");
        params.put("beansSendId", beansSendId);

        MethodApi.getOtcHandle(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void getOtcHandleSubmit(String uid, int sendStatus, String beansSendId, String payPassword, String vcode) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.getOtcHandleSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcHandleFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("sendStatus", sendStatus + "");
        params.put("beansSendId", beansSendId);
        params.put("payPassword", MD5Utils.MD5(payPassword));
        params.put("vcode", vcode);

        MethodApi.getOtcHandle(params, new OnSuccessAndFaultSub(listener, context));
    }
}
