package com.zqlc.www.view.otc;

import android.widget.ImageView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.util.glide.GlideUtil;

public class BigImgActivity extends BaseActivity {

    ImageView iv_img;
    String imgUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_big_img;
    }

    @Override
    public void initView() {
        showLeftAndTitle("查看图片");

        imgUrl = intent.getStringExtra("imgUrl");

        iv_img = $(R.id.iv_img);


    }

    @Override
    public void initData() {
        GlideUtil.loadImage(context,imgUrl,iv_img);
    }
}
