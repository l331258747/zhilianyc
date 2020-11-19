package com.zlyc.www.bean.shop;

import com.zlyc.www.util.StringUtils;

public class OrderListBean {


    /**
     * id : 1326795072288624641
     * imgUrl : https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/518440481630195712.png
     * name : 小白取暖器立卧两用暖风机小型电暖气
     * num : 1
     * price : 450
     * sum : 450
     * totalSum : 570
     * type : 1
     */

    private String id;
    private String imgUrl;
    private String name;
    private int num;
    private float price;
    private int sum;
    private float totalSum;
    private int type;

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public String getNumStr() {
        return "数量："+num+"件";
    }

    public float getPrice() {
        return price;
    }

    public String getPriceStr() {
        return "单价："+ StringUtils.getStringNum(price) +"京豆";
    }


    public int getSum() {
        return sum;
    }

    public float getTotalSum() {
        return totalSum;
    }

    public String getTotalSumStr() {
        return StringUtils.getStringNum(totalSum);
    }

    public int getType() {
        return type;
    }
    public String getTypeStr() {
        switch (type){
            case 1:
                return "待付款";
            case 2:
                return "待发货";
            case 3:
                return "待收货";
        }
        return "" + type;
    }
}
