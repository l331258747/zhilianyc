package com.zlyc.www.bean.my;

import java.util.ArrayList;
import java.util.List;

public class MyTeamBean {

    String imgUrl;
    String post;
    int laborPersonal;
    int laborTeam;
    int id;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getLaborPersonal() {
        return laborPersonal;
    }

    public void setLaborPersonal(int laborPersonal) {
        this.laborPersonal = laborPersonal;
    }

    public int getLaborTeam() {
        return laborTeam;
    }

    public void setLaborTeam(int laborTeam) {
        this.laborTeam = laborTeam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MyTeamBean> getDatas() {
        String url = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4131746241,2477555401&fm=11&gp=0.jpg";
        List<MyTeamBean> datas = new ArrayList<>();
        datas.add(getItem(1, url, "董事",166,4));
        datas.add(getItem(3, url, "副总",3,6));
        datas.add(getItem(5, url, "总经理",234,23));
        datas.add(getItem(6, url, "经理",3,376));

        datas.add(getItem(7, url, "主管",35,3));
        datas.add(getItem(8, url, "经理",23,46));
        datas.add(getItem(9, url, "主管",535,21));
        datas.add(getItem(0, url, "主管",2,77));

        return datas;

    }

    private MyTeamBean getItem(int id, String imgUrl, String post, int laborPersonal, int laborTeam) {
        MyTeamBean item = new MyTeamBean();
        item.setId(id);
        item.setImgUrl(imgUrl);
        item.setLaborPersonal(laborPersonal);
        item.setLaborTeam(laborTeam);
        item.setPost(post);
        return item;
    }


}
