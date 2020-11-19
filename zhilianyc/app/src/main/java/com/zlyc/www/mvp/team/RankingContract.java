package com.zlyc.www.mvp.team;

import com.zlyc.www.bean.team.RankingBean;

import java.util.List;

public interface RankingContract {

    interface Presenter {
        void inviteRanking(int type);
    }

    interface View {
        void inviteRankingSuccess(List<RankingBean> data);
        void inviteRankingFailed(String msg);

    }
}
