package com.zqlc.www.mvp.message;

import android.content.Context;

import com.zqlc.www.bean.message.AppUpdateBean;
import com.zqlc.www.util.AppUtils;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class AppUpdatePresenter implements AppUpdateContract.Presenter{

    AppUpdateContract.View iView;
    Context context;

    public AppUpdatePresenter(Context context, AppUpdateContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void getAppUpdate() {
        ResponseCallback listener = new ResponseCallback<AppUpdateBean>() {
            @Override
            public void onSuccess(AppUpdateBean data) {
                iView.getAppUpdateSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getAppUpdateFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("name", "jmallapp");
        params.put("version", AppUtils.getVersionName());
        params.put("platform", "android");

        MethodApi.getAppUpdate(params, new OnSuccessAndFaultSub(listener, context, false));
    }
}
