package com.zlyc.www.mvp.team;

import com.zlyc.www.bean.team.MyTeamDetailBean;
import com.zlyc.www.bean.team.TeamInviteBean;

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
