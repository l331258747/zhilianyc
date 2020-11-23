package com.zlyc.www.bean.shop;

import java.io.Serializable;

public class GoodsClassBean implements Serializable {

    String id;
    String name;
    String imgUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
