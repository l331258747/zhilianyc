package com.zqlc.www.mvp.my;

import com.zqlc.www.bean.otc.TradeRuleBean;

public interface TradeRuleContract {

    interface Presenter {
        void beansTradeRule();
    }

    interface View {
        void beansTradeRuleSuccess(TradeRuleBean data);
        void beansTradeRuleFailed(String msg);
    }
}
