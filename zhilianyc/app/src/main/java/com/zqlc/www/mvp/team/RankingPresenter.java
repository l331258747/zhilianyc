package com.zqlc.www.mvp.team;

import android.content.Context;

import com.zqlc.www.bean.team.RankingBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingPresenter implements RankingContract.Presenter {

    Context context;
    RankingContract.View iView;

    public RankingPresenter(Context context, RankingContract.View iView) {
        this.context = context;
        this.iView = iView;
    }

    @Override
    public void inviteRanking(int type) {
        ResponseCallback listener = new ResponseCallback<List<RankingBean>>() {
            @Override
            public void onSuccess(List<RankingBean> data) {
                iView.inviteRankingSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.inviteRankingFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("type",type+"");

        MethodApi.inviteRanking(params, new OnSuccessAndFaultSub(listener, context));
    }
}
