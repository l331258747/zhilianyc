package com.zlyc.www.bean.login;

import com.zlyc.www.util.StringUtils;

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
    private float contribution;
    private String contributionRank;
    private float labor;
    private float extraLabor;
    private float beans;
    private float sellableBeans;
    private float todayBeans;
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

    public String getContribution() {
        return StringUtils.getStringNum(contribution);
    }

    public String getContributionRank() {
        return contributionRank;
    }

    public String getLabor() {
        return StringUtils.getStringNum(labor);
    }

    public String getExtraLabor() {
        return StringUtils.getStringNum(extraLabor);
    }


    public float getBeans() {
        return beans;
    }

    public String getBeansStr() {
        return StringUtils.getStringNum(beans);
    }

    public String getSellableBeans() {
        return StringUtils.getStringNum(sellableBeans);
    }

    public String getTodayBeans() {
        return StringUtils.getStringNum(todayBeans);
    }

    public int getMiddleman() {
        return middleman;
    }
}
