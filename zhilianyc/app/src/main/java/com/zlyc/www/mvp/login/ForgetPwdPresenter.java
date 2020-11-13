package com.zlyc.www.mvp.login;

import android.content.Context;

import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;
import com.zlyc.www.util.md5.MD5Utils;

import java.util.HashMap;
import java.util.Map;

public class ForgetPwdPresenter implements ForgetPwdContract.Presenter{

    ForgetPwdContract.View iView;
    Context context;

    public ForgetPwdPresenter(Context context, ForgetPwdContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void forgetPwd(String mobile, String vcode,String password) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.forgetPwdSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.forgetPwdFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("mobile", MD5Utils.MD5(mobile));
        params.put("password", MD5Utils.MD5(password));
        params.put("vcode", vcode);

        MethodApi.forgetPwd(params, new OnSuccessAndFaultSub(listener, context));
    }
}
