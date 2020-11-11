package com.zlyc.www.bean;

import android.text.TextUtils;

import com.zlyc.www.bean.login.LoginModel;
import com.zlyc.www.util.SPUtils;

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


    public void setData(LoginModel model) {

        SPUtils.getInstance().putString(SPUtils.SP_USER_TOKEN, model.getToken());
        SPUtils.getInstance().putString(SPUtils.SP_USER_NICKNAME, model.getNickname());
        SPUtils.getInstance().putString(SPUtils.SP_USER_MOBILE, model.getMobile());
        SPUtils.getInstance().putString(SPUtils.SP_USER_AVATAR, model.getAvatar());
        SPUtils.getInstance().putString(SPUtils.SP_USER_ID, model.getUserId());
    }

    public String getUserId(){
        return SPUtils.getInstance().getString(SPUtils.SP_USER_ID);
    }

    public String getUserToken() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_TOKEN);
    }


    public String getUserNickname() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_NICKNAME);
    }

    public void setUserNickname(String nickname) {
        SPUtils.getInstance().putString(SPUtils.SP_USER_NICKNAME, nickname);
    }

    public String getUserMoble() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_MOBILE);
    }

    public void setUserMoble(String moble){
        SPUtils.getInstance().putString(SPUtils.SP_USER_MOBILE,moble);
    }

    public String getUserAvatar() {
        return SPUtils.getInstance().getString(SPUtils.SP_USER_AVATAR);
    }



    public void loginOff() {
        SPUtils.getInstance().logoff();
    }
}
