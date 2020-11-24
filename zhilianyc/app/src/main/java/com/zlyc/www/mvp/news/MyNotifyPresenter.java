package com.zlyc.www.mvp.news;

import android.content.Context;

import com.zlyc.www.bean.BasePageModel;
import com.zlyc.www.bean.news.AnnouncementBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class MyNotifyPresenter implements MyNotifyContract.Presenter{

    MyNotifyContract.View iView;
    Context context;

    public MyNotifyPresenter(Context context, MyNotifyContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void getAnnouncement(int page) {
        ResponseCallback listener = new ResponseCallback<BasePageModel<AnnouncementBean>>() {
            @Override
            public void onSuccess(BasePageModel<AnnouncementBean> data) {
                iView.getAnnouncementSuccess(data.getList());
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getAnnouncementFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("size", Constant.DEFAULT_SIZE + "");

        MethodApi.getAnnouncement(params, new OnSuccessAndFaultSub(listener, context, false));
    }
}
