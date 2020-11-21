package com.zlyc.www.bean.user;

import com.zlyc.www.util.StringUtils;

public class TaskBean {


    /**
     * videoMissionNum : 0
     * videoMissionMax : 3
     * videoBeans : 0
     * signinNum : 0
     * signinMax : 1
     * signinBeans : 0.1
     * gameNum : 0
     * gameMax : 1
     * gameSellableBeans : 0
     * readNewsNum : 0
     * readNewsMax : 1
     * readNewsContribution : 10
     * awardNum : 0
     * awardMax : 1
     * awardContribution : 10
     */

    private int videoMissionNum;
    private int videoMissionMax;
    private double videoBeans;
    private int signinNum;
    private int signinMax;
    private double signinBeans;
    private int gameNum;
    private int gameMax;
    private double gameSellableBeans;
    private int readNewsNum;
    private int readNewsMax;
    private double readNewsContribution;
    private int awardNum;
    private int awardMax;
    private double awardContribution;

    public int getVideoMissionNum() {
        return videoMissionNum;
    }

    public int getVideoMissionMax() {
        return videoMissionMax;
    }

    public double getVideoBeans() {
        return videoBeans;
    }

    public String getVideoBeansStr() {
        if(videoBeans == 0)
            return "获得卷轴及加成金豆";
        return "获得卷轴及加成金豆" + StringUtils.getStringNum(videoBeans);
    }

    public String getVideoMission() {
        return "今日视频任务（" + videoMissionNum + "/" + videoMissionMax + "）";
    }

    public int getSigninNum() {
        return signinNum;
    }

    public int getSigninMax() {
        return signinMax;
    }

    public double getSigninBeans() {
        return signinBeans;
    }

    public String getSigninBeansStr() {
        if(signinBeans == 0)
            return "获得额外金豆";
        return "获得额外金豆" + StringUtils.getStringNum(signinBeans);
    }

    public String getSignin() {
        return "今日签到次数（" + signinNum + "/" + signinMax + "）";
    }

    public int getGameNum() {
        return gameNum;
    }

    public int getGameMax() {
        return gameMax;
    }

    public double getGameSellableBeans() {
        return gameSellableBeans;
    }

    public String getGameSellableBeansStr() {
        if(gameSellableBeans == 0)
            return "获得可售额度";
        return "获得可售额度" + StringUtils.getStringNum(gameSellableBeans);
    }

    public String getGame() {
        return "今日游戏完成（" + gameNum + "/" + gameMax + "）";
    }

    public int getReadNewsNum() {
        return readNewsNum;
    }

    public int getReadNewsMax() {
        return readNewsMax;
    }

    public double getReadNewsContribution() {
        return readNewsContribution;
    }

    public String getReadNewsContributionStr() {
        if(readNewsContribution == 0)
            return "获得贡献值";
        return "获得贡献值" + StringUtils.getStringNum(readNewsContribution);
    }

    public String getReadNews() {
        return "今日阅读（" + readNewsNum + "/" + readNewsMax + "）";
    }

    public int getAwardNum() {
        return awardNum;
    }

    public int getAwardMax() {
        return awardMax;
    }

    public double getAwardContribution() {
        return awardContribution;
    }

    public String getAwardContributionStr() {
        if(awardContribution == 0)
            return "获得贡献值";
        return "获得贡献值" + StringUtils.getStringNum(awardContribution);
    }

    public String getAward() {
        return "今日抽奖（" + awardNum + "/" + awardMax + "）";
    }

    public String getTaskAll() {
        int max = videoMissionMax + signinMax + gameMax + readNewsMax + awardMax;
        int num = videoMissionNum + signinNum + gameNum + readNewsNum + awardNum;
        return "开心赚金豆（" + num + "/" + max + "）";
    }
}
