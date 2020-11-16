package com.zlyc.www.bean.controller;

import com.zlyc.www.util.StringUtils;

public class GoodsDetailsBean {


    /**
     * goodsId : 1277478890869063682
     * name : 潘婷400ml丝质顺滑去屑洗发露
     * imgUrl : https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/474976359538495488.png
     * price : 390
     * sellNum : 5
     * leftNum : 7
     * content : <p><img src="https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/474976198246535168.png" alt="" /></p>
     * postage : 117
     * minNum : 1
     * maxNum : 1
     */

    private String goodsId;
    private String name;
    private String imgUrl;
    private double price;
    private int sellNum;
    private int leftNum;
    private String content;
    private int postage;
    private int minNum;
    private int maxNum;

    public String getGoodsId() {
        return goodsId;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceStr(){
        return StringUtils.getStringNum(price);
    }

    public int getSellNum() {
        return sellNum;
    }
    public String getSellNumStr() {
        return "已售:"+sellNum;
    }

    public int getLeftNum() {
        return leftNum;
    }
    public String getLeftNumStr() {
        return "库存:"+leftNum;
    }

    public String getContent() {
        return content;
    }

    public int getPostage() {
        return postage;
    }

    public String getPostageStr(){
        return "运费:<font color=\"#FF4751\">"+postage+"京豆</font>";
    }

    public int getMinNum() {
        return minNum;
    }

    public String getMinNumStr() {
        if(minNum == 0){
            return "";
        }else{
            return "(最多可买<font color=\"#368FEB\">"+minNum+"</font>件)";
        }
    }

    public int getMaxNum() {
        return maxNum;
    }
}
