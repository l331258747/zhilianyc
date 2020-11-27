package com.zqlc.www.bean.my;

import com.zqlc.www.R;
import com.zqlc.www.constant.Constant;

import java.util.ArrayList;
import java.util.List;

public class MyTabBean {

    int id;
    int imgId;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MyTabBean> getDatas(){
        List<MyTabBean> datas = new ArrayList<>();
        datas.add(getItem(Constant.MY_TAB_TEAM,R.mipmap.ic_my_team,"我的团队"));
        datas.add(getItem(Constant.MY_TAB_SECURITY,R.mipmap.ic_my_security,"安全中心"));
        datas.add(getItem(Constant.MY_TAB_ORDER,R.mipmap.ic_my_order,"我的账单"));
        datas.add(getItem(Constant.MY_TAB_TRANSACTION_DTS,R.mipmap.ic_my_transaction_dts,"交易明细"));

        datas.add(getItem(Constant.MY_TAB_INVITAT,R.mipmap.ic_my_invitat,"我的邀请"));
        datas.add(getItem(Constant.MY_TAB_STUDIO,R.mipmap.ic_my_studio,"学习中心"));
        datas.add(getItem(Constant.MY_TAB_NOTIFY,R.mipmap.ic_my_nofity,"公告中心"));
        datas.add(getItem(Constant.MY_TAB_CHANGE,R.mipmap.ic_my_change,"置换中心"));

        datas.add(getItem(Constant.MY_TAB_CLOUD,R.mipmap.ic_my_cloud,"我的云仓"));
        datas.add(getItem(Constant.MY_TAB_SHOP,R.mipmap.ic_my_shop,"我的店铺"));
        datas.add(getItem(Constant.MY_TAB_RED_PACKAGE,R.mipmap.ic_my_red_package,"去赚红包"));
        datas.add(getItem(Constant.MY_TAB_TASK,R.mipmap.ic_my_task,"今日任务"));
        return datas;

    }

    private MyTabBean getItem(int id, int imgId, String name){
        MyTabBean item = new MyTabBean();
        item.setId(id);
        item.setImgId(imgId);
        item.setName(name);
        return item;
    }
}
