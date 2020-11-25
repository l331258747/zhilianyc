package com.zqlc.www.mvp.user;

import android.content.Context;

import com.zqlc.www.bean.user.ListReginBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRegionPresenter implements ListRegionContract.Presenter {

    Context context;
    ListRegionContract.View iView;

    public ListRegionPresenter(Context context, ListRegionContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void listRegion(String pCode) {
        ResponseCallback listener = new ResponseCallback<List<ListReginBean>>() {
            @Override
            public void onSuccess(List<ListReginBean> data) {
                iView.listRegionSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.listRegionFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("pCode", pCode);

        MethodApi.listRegion(params, new OnSuccessAndFaultSub(listener, context));
    }
}
