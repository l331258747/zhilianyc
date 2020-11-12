package com.zlyc.www.bean.my;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressBean implements Serializable {

    String name;
    String phone;
    String address;
    String addressDts;
    boolean isDefault;

    public String getName() {
        return name;
    }

    public String getNameHead(){
        if(TextUtils.isEmpty(name))
            return "";
        return name.substring(0,1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDts() {
        return addressDts;
    }

    public void setAddressDts(String addressDts) {
        this.addressDts = addressDts;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getAddressAll(){
        if(!TextUtils.isEmpty(address) && !TextUtils.isEmpty(addressDts)){
            return address + " " + addressDts;
        }
        if(!TextUtils.isEmpty(address)){
            return address;
        }
        if(!TextUtils.isEmpty(addressDts)){
            return addressDts;
        }
        return "";
    }

    public List<AddressBean> getDatas(){
        List<AddressBean> datas = new ArrayList<>();
        datas.add(getItem("张三","13511111111","湖南省 长沙市 岳麓区","天顶街道 麓谷企业 广场c1栋301室",false));
        datas.add(getItem("李四","13522222222","湖南省 长沙市 岳麓区","天顶街道 麓谷企业 广场c1栋305室",false));
        datas.add(getItem("王五","13533333333","湖南省 长沙市 岳麓区","天顶街道 麓谷企业 广场c1栋309室",false));
        datas.add(getItem("李智链","13544444444","湖南省 长沙市 岳麓区","天顶街道 麓谷企业 广场c1栋355室",true));

        return datas;

    }
    public List<AddressBean> getDatas2(){
        List<AddressBean> datas = new ArrayList<>();
        datas.add(getItem("张三","13511111111","湖南省 长沙市 岳麓区","天顶街道 麓谷企业 广场c1栋301室",false));
        datas.add(getItem("李四","13522222222","湖南省 长沙市 岳麓区","天顶街道 麓谷企业 广场c1栋305室",false));
        datas.add(getItem("王五","13533333333","湖南省 长沙市 岳麓区","天顶街道 麓谷企业 广场c1栋309室",true));
        datas.add(getItem("李智链2","13544444444","湖南省 长沙市 岳麓区2","天顶街道 麓谷企业 广场c1栋355室2",false));

        return datas;

    }

    private AddressBean getItem(String name, String phone, String address, String addressDts, boolean isDefault){
        AddressBean item = new AddressBean();
        item.setName(name);
        item.setPhone(phone);
        item.setAddress(address);
        item.setAddressDts(addressDts);
        item.setDefault(isDefault);
        return item;
    }
}
