package com.zlyc.www.bean.team;

import java.util.List;

public class TeamInviteBean {

    /**
     * code : DA8JFK
     * totalNum : 3
     * inviteNum : 0
     * teams : [{"headImg":null,"mobile":"18132112204","rank":"新人","time":"2020-07-20 17:39:20","labor":0,"teamLabor":0,"realName":3},{"headImg":null,"mobile":"13420938116","rank":"新人","time":"2020-06-05 11:28:21","labor":0,"teamLabor":0,"realName":3},{"headImg":null,"mobile":"17397311583","rank":"新人","time":"2020-04-21 15:12:08","labor":0,"teamLabor":0,"realName":3}]
     */

    private String code;
    private int totalNum;
    private int inviteNum;
    private List<TeamInviteListBean> teams;

    public String getCode() {
        return code;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getInviteNum() {
        return inviteNum;
    }

    public List<TeamInviteListBean> getTeams() {
        return teams;
    }
}
