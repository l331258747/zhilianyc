package com.zlyc.www.bean.login;

public class MineBean {


    /**
     * nickName : 156****7868   昵称
     * uid : 434720845688999936 用户ID
     * rank : 职员    称号
     * cityPartner : 0  城市合伙人，1是，0不是
     * cityPartnerName : null   如果是城市合伙人，这里有城市合伙人名称
     * contribution : 270.0 贡献度
     * contributionRank : lv0   贡献度等级
     * labor : 0.0  自身劳动力
     * extraLabor : 0.0 加成劳动力
     * beans : 100000.0 京豆
     * sellableBeans : 10000.0  京豆可售额度
     * todayBeans : 0.0 今日活动的京豆数量
     * middleman : 0    中间商：1是，0不是
     */

    private String nickName;
    private String uid;
    private String rank;
    private int cityPartner;
    private String cityPartnerName;
    private double contribution;
    private String contributionRank;
    private double labor;
    private double extraLabor;
    private double beans;
    private double sellableBeans;
    private double todayBeans;
    private int middleman;
    private String headImg;

    public String getHeadImg() {
        return headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUid() {
        return uid;
    }

    public String getRank() {
        return rank;
    }

    public int getCityPartner() {
        return cityPartner;
    }

    public String getCityPartnerName() {
        return cityPartnerName;
    }

    public int getContribution() {
        return new Double(contribution).intValue();
    }

    public String getContributionRank() {
        return contributionRank;
    }

    public int getLabor() {
        return new Double(labor).intValue();
    }

    public int getExtraLabor() {
        return new Double(extraLabor).intValue();
    }

    public int getBeans() {
        return new Double(beans).intValue();
    }

    public int getSellableBeans() {
        return new Double(sellableBeans).intValue();
    }

    public int getTodayBeans() {
        return new Double(todayBeans).intValue();
    }

    public int getMiddleman() {
        return middleman;
    }
}
