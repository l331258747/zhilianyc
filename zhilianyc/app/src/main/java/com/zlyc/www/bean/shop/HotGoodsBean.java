package com.zlyc.www.bean.shop;

import com.zlyc.www.util.StringUtils;

public class HotGoodsBean {


    /**
     * goodsId : 1320943335557222402
     * name : 小白取暖器立卧两用暖风机小型电暖气
     * imgUrl : https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/518440481630195712.png
     * price : 450.0
     */

    private String goodsId;
    private String name;
    private String imgUrl;
    private double price;
    private int num;



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

    public int getNum(){
        return num;
    }

    public String getNumStr(){
        return "剩余" + num;
    }


}
