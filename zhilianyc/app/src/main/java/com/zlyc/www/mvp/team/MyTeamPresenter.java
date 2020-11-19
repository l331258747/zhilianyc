package com.zlyc.www.mvp.team;

import android.content.Context;

import com.zlyc.www.bean.team.MyTeamDetailBean;
import com.zlyc.www.bean.team.TeamInviteBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.util.http.MethodApi;
import com.zlyc.www.util.http.OnSuccessAndFaultSub;
import com.zlyc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class MyTeamPresenter implements MyTeamContract.Presenter {

    Context context;
    MyTeamContract.View iView;

    public MyTeamPresenter(Context context, MyTeamContract.View iView) {
        this.context = context;
        this.iView = iView;
    }


    @Override
    public void getTeamDetail(String uid) {
        ResponseCallback listener = new ResponseCallback<MyTeamDetailBean>() {
            @Override
            public void onSuccess(MyTeamDetailBean data) {
                iView.getTeamDetailSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getTeamDetailFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);

        MethodApi.getTeamDetail(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void getTeamInvite(String uid, int page) {
        ResponseCallback listener = new ResponseCallback<TeamInviteBean>() {
            @Override
            public void onSuccess(TeamInviteBean data) {
                iView.getTeamInviteSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.getTeamInviteFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("page",page + "");
        params.put("size", Constant.DEFAULT_SIZE + "");

        MethodApi.getTeamInvite(params, new OnSuccessAndFaultSub(listener, context));
    }
}
