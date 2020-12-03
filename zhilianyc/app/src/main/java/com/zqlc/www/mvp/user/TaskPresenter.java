package com.zqlc.www.mvp.user;

import android.content.Context;

import com.zqlc.www.bean.user.TaskBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class TaskPresenter implements TaskContract.Presenter {

    Context context;
    TaskContract.View iView;

    public TaskPresenter(Context context, TaskContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void getTask(String uid) {
        ResponseCallback listener = new ResponseCallback<TaskBean>() {
            @Override
            public void onSuccess(TaskBean data) {
                iView.getTaskSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getTaskFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.getTask(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void signin(String uid) {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.signinSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.signinFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.signin(params, new OnSuccessAndFaultSub(listener, context));
    }
}
