package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.login.InfoBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AccountPresenter implements AccountContract.Presenter{

    AccountContract.View iView;
    Context context;

    public AccountPresenter(Context context, AccountContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void info(String uid) {
        ResponseCallback listener = new ResponseCallback<InfoBean>() {
            @Override
            public void onSuccess(InfoBean data) {
                iView.infoSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.infoFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.info(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void resetNickname(String uid, String nickname) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.resetNicknameSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.resetNicknameFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("nickname",nickname);

        MethodApi.resetNickname(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void resetHead(String uid, File headImg) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.resetHeadSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.resetHeadFailed(errorMsg);
            }
        };

        MethodApi.resetHead(uid,headImg, new OnSuccessAndFaultSub(listener, context));
    }
}
