package com.zqlc.www.bean;

import android.text.TextUtils;

import com.zqlc.www.bean.login.LoginBean;
import com.zqlc.www.util.SPUtils;

/**
 * Created by LGQ
 * Time: 2018/8/24
 * Function:
 */

public class MySelfInfo {

    private String userToken;

    private MySelfInfo() {
    }

    private final static class HolderClass {
        private final static MySelfInfo INSTANCE = new MySelfInfo();
    }

    public static MySelfInfo getInstance() {
        return HolderClass.INSTANCE;
    }

    public boolean isLogin() {
        if (!TextUtils.isEmpty(getUserToken())) {
            return true;
        }
        return false;
    }

    public void setLoginData(LoginBean model,String phone){
        SPUtils.getInstance().putString(SPUtils.SP_USER_TOKEN, model.getToken());
        SPUtils.getInstance().putString(SPUtils.SP_USER_ID, model.getUserId());
        SPUtils.getInstance().putString(SPUtils.SP_USER_USERSTATE, model.getUserState());
        SPUtils.getInstance().putString(SPUtils.SP_USER_MOBILE, phone);
        SPUtils.getInstance().putString(SPUtils.SP_USER_SHARE_URL, model.getShareUrl());
        SPUtils.getInstance().putString(SPUtils.SP_USER_INVITE_CODE, model.getInviteCode());
    }

    public String getUserId(){
        return SPUtils.getInstance().getString(SPUtils.SP_USER_ID);
    }

    public String getUserState(){
        return SPUtils.getInstance().getString(SPUtils.SP_USER_USERSTATE);
    }

    public String getShareUrl(){
        return SPUtils.getInstance().getString(SPUtils.SP_USER_SHARE_URL);
    }
    public String getInviteCode(){
        return SPUtils.getInstance().getString(SPUtils.SP_USER_INVITE_CODE);
    }

    public String getUserToken() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_TOKEN);
    }

    public String getUserMobile(){
        return SPUtils.getInstance().getString(SPUtils.SP_USER_MOBILE);
    }


    public void loginOff() {
        SPUtils.getInstance().logoff();
    }
}
