package com.zlyc.www.mvp.otc;

import android.content.Context;

import com.zlyc.www.bean.BasePageModel;
import com.zlyc.www.bean.otc.OtcListBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class OtcListPresenter implements OtcListContract.Presenter {

    Context context;
    OtcListContract.View iView;

    public OtcListPresenter(Context context, OtcListContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getOtcList(String uid, int orderType, int priceSort, int numSort, int numType, int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<OtcListBean>>() {
            @Override
            public void onSuccess(BasePageModel<OtcListBean> data) {
                iView.getOtcListSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcListFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("orderType", orderType + "");
        params.put("priceSort", priceSort + "");
        params.put("numSort", numSort + "");
        params.put("numType", numType + "");
        params.put("page", page + "");
        params.put("size", Constant.DEFAULT_SIZE + "");

        MethodApi.getOtcList(params, new OnSuccessAndFaultSub(listener, context));
    }

}
