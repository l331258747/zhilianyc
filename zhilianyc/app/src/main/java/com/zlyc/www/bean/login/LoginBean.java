package com.zlyc.www.bean.login;

/**
 * Created by LGQ
 * Time: 2018/8/23
 * Function:
 */

public class LoginBean {

    /**
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJBUFAiLCJ1aWQiOiI0MzQ3MjA4NDU2ODg5OTk5MzYiLCJpc3MiOiJTZXJ2aWNlIiwiZXhwIjoxNjA2MDI5MTAwLCJpYXQiOjE2MDUxNjUxMDB9.N4llblGK1HonBwxfp0p7_lYVcw1d20MiHwdX26xO01I
     * userId : 434720845688999936
     * userState : 0
     */

    private String token;
    private String userId;
    private String userState;
    private String inviteCode;
    private String shareUrl;

    public String getInviteCode() {
        return inviteCode;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public String getToken() {
        return token;
    }


    public String getUserId() {
        return userId;
    }

    public String getUserState() {
        return userState;
    }

}
