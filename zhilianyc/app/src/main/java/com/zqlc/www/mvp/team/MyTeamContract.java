package com.zqlc.www.mvp.team;

import com.zqlc.www.bean.team.MyTeamDetailBean;
import com.zqlc.www.bean.team.TeamInviteBean;

public interface MyTeamContract {

    interface Presenter {
        void getTeamDetail(String uid);
        void getTeamInvite(String uid,int page);
    }

    interface View {
        void getTeamDetailSuccess(MyTeamDetailBean data);
        void getTeamDetailFailed(String msg);

        void getTeamInviteSuccess(TeamInviteBean data);
        void getTeamInviteFailed(String msg);

    }
}
