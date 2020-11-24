package com.zqlc.www.mvp.news;

import android.content.Context;

import com.zqlc.www.bean.BasePageModel;
import com.zqlc.www.bean.news.StudyCentreBean;
import com.zqlc.www.constant.Constant;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class MyStudyPresenter implements MyStudyContract.Presenter{

    MyStudyContract.View iView;
    Context context;

    public MyStudyPresenter(Context context, MyStudyContract.View view) {
        this.iView = view;
        this.context = context;
    }


    @Override
    public void getStudyCentre(int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<StudyCentreBean>>() {
            @Override
            public void onSuccess(BasePageModel<StudyCentreBean> data) {
                iView.getStudyCentreSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getStudyCentreFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("page",page +"");
        params.put("size", Constant.DEFAULT_SIZE + "");

        MethodApi.getStudyCentre(params, new OnSuccessAndFaultSub(listener, context,false));
    }
}
