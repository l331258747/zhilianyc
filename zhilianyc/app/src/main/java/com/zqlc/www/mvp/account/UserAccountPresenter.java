package com.zqlc.www.mvp.account;

import android.content.Context;

import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.account.UserAccountBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class UserAccountPresenter implements UserAccountContract.Presenter {

    UserAccountContract.View iView;
    Context context;

    public UserAccountPresenter(Context context, UserAccountContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void getUserAccount(String uid) {
        ResponseCallback listener = new ResponseCallback<UserAccountBean>() {
            @Override
            public void onSuccess(UserAccountBean data) {
                iView.getUserAccountSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getUserAccountFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);

        MethodApi.getUserAccount(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void updateAccountNo(String uid, String account) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.updateAccountNoSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.updateAccountNoFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("account", account);

        MethodApi.updateAccountNo(params, new OnSuccessAndFaultSub(listener, context));
    }
}
