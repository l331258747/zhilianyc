package com.zqlc.www.bean;

import com.zqlc.www.bean.ad.ConfigBean;
import com.zqlc.www.util.SPUtils;

/**
 * Created by LGQ
 * Time: 2018/8/24
 * Function:
 */

public class ConfigInfo {

    private String userToken;

    private ConfigInfo() {
    }

    private final static class HolderClass {
        private final static ConfigInfo INSTANCE = new ConfigInfo();
    }

    public static ConfigInfo getInstance() {
        return HolderClass.INSTANCE;
    }

    public void setConfigData(ConfigBean model){

        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_rsAppId, model.getRsAppId());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_rsAppTid, model.getRsAppTid());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_rsGameMediaId, model.getRsGameMediaId());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_taAppKey, model.getTaAppKey());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_taAppSecret, model.getTaAppSecret());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_taAwardAlotId, model.getTaAwardAlotId());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_taSiginAlotId, model.getTaSiginAlotId());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_zjAppKey, model.getZjAppKey());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_zjVideoId, model.getZjVideoId());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_zjVideoUserId, model.getZjVideoUserId());
        SPUtils.getInstance().putString(SPUtils.SP_CONFIG_zjSplashId, model.getZjSplashId());

    }

    public String getRsAppId() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_rsAppId);
    }

    public String getRsAppTid() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_rsAppTid);
    }

    public String getRsGameMediaId() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_rsGameMediaId);
    }

    public String getTaAppKey() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_taAppKey);
    }

    public String getTaAppSecret() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_taAppSecret);
    }

    public String getTaAwardAlotId() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_taAwardAlotId);
    }
    public String getTaSiginAlotId() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_taSiginAlotId);
    }

    public String getZjAppKey() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_zjAppKey);
    }

    public String getZjVideoId() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_zjVideoId);
    }

    public String getZjVideoUserId() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_zjVideoUserId);
    }

    public String getZjSplashId() {
        return SPUtils.getInstance().getString(SPUtils.SP_CONFIG_zjSplashId);
    }


}
