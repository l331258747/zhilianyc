package com.zqlc.www.mvp.my;

import android.content.Context;

import com.zqlc.www.bean.otc.TradeRuleBean;
import com.zqlc.www.util.http.MethodApi;
import com.zqlc.www.util.http.OnSuccessAndFaultSub;
import com.zqlc.www.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class TradeRulePresenter implements TradeRuleContract.Presenter{

    TradeRuleContract.View iView;
    Context context;

    public TradeRulePresenter(Context context, TradeRuleContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void beansTradeRule() {
        ResponseCallback listener = new ResponseCallback<TradeRuleBean>() {
            @Override
            public void onSuccess(TradeRuleBean data) {
                iView.beansTradeRuleSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.beansTradeRuleFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        MethodApi.beansTradeRule(params, new OnSuccessAndFaultSub(listener, context));
    }
}
