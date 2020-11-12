package com.zlyc.www.mvp.login;

import android.content.Context;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;
import com.zlyc.www.util.md5.MD5Utils;

import java.util.HashMap;
import java.util.Map;

public class ResetPwdPresenter implements ResetPwdContract.Presenter{

    ResetPwdContract.View iView;
    Context context;

    public ResetPwdPresenter(Context context, ResetPwdContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void resetPwd(String oldPassword, String newPassword) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.resetPwdSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.resetPwdFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("oldPassword", MD5Utils.MD5(oldPassword));
        params.put("newPassword", MD5Utils.MD5(newPassword));
        params.put("uid", MySelfInfo.getInstance().getUserId());

        MethodApi.resetPwd(params, new OnSuccessAndFaultSub(listener, context));
    }
}
