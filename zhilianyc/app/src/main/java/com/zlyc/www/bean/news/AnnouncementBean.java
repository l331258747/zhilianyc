package com.zlyc.www.bean.news;

public class AnnouncementBean {


    /**
     * id : 1280818731811848193
     * createTime : 2020-07-20 18:09:53
     * updateTime : 2020-11-20 14:26:21
     * status : 1
     * title : 智链云仓守护者计划
     * img : https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/478609052595654656.png
     * tag :
     * readNum : 14155
     * content : <p><img src="https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/482693411132936192.png" alt="" width="355" height="488" /></p>
     * type : 1
     * weight : 1
     * newsType : 1
     */

    private String id;
    private String createTime;
    private String updateTime;
    private int status;
    private String title;
    private String img;
    private String tag;
    private int readNum;
    private String content;
    private int type;
    private int weight;
    private int newsType;

    public String getId() {
        return id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getTag() {
        return tag;
    }

    public int getReadNum() {
        return readNum;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public int getNewsType() {
        return newsType;
    }
}
