package com.zlyc.www.mvp.otc;

import android.content.Context;

import com.zlyc.www.bean.BasePageModel;
import com.zlyc.www.bean.otc.MyOtcListBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class MyOtcListPresenter implements MyOtcListContract.Presenter {

    Context context;
    MyOtcListContract.View iView;

    public MyOtcListPresenter(Context context, MyOtcListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getMyOtcList(String uid,int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<MyOtcListBean>>() {
            @Override
            public void onSuccess(BasePageModel<MyOtcListBean> data) {
                iView.getMyOtcListSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getMyOtcListFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("page", page + "");
        params.put("size", Constant.DEFAULT_SIZE + "");

        MethodApi.getMyOtcList(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
