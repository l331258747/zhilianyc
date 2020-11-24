package com.zqlc.www.mvp.otc;

import android.content.Context;

import com.zqlc.www.bean.BasePageModel;
import com.zqlc.www.bean.otc.OtcInfoBean;
import com.zqlc.www.bean.otc.OtcListBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class OtcMarkerPresenter implements OtcMarkerContract.Presenter {

    Context context;
    OtcMarkerContract.View iView;

    public OtcMarkerPresenter(Context context, OtcMarkerContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void getOtcInfo() {
        ResponseCallback listener = new ResponseCallback<OtcInfoBean>() {
            @Override
            public void onSuccess(OtcInfoBean data) {
                iView.getOtcInfoSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcInfoFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.getOtcInfo(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void getOtcOpen() {
        ResponseCallback listener = new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                iView.getOtcOpenSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getOtcOpenFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();

        MethodApi.getOtcOpen(params, new OnSuccessAndFaultSub(listener, context));
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

        MethodApi.getOtcList(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
