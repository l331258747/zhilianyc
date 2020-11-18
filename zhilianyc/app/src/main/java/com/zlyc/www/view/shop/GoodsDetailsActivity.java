package com.zlyc.www.view.shop;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.shop.GoodsDetailsBean;
import com.zlyc.www.mvp.shop.GoodsDetailsContract;
import com.zlyc.www.mvp.shop.GoodsDetailsPresenter;
import com.zlyc.www.util.StringUtils;
import com.zlyc.www.util.glide.GlideUtil;
import com.zlyc.www.util.webview.LWebView;
import com.zlyc.www.widget.NumberView;

import androidx.constraintlayout.widget.Group;

public class GoodsDetailsActivity extends BaseActivity implements GoodsDetailsContract.View, View.OnClickListener {

    ImageView iv_img;
    TextView tv_price,tv_title,tv_freight,tv_volume,tv_stock,tv_num_demand,btn_submit;
    NumberView numberView;

    LWebView vebView;

    Group group_content;

    GoodsDetailsPresenter mPresenter;

    String id;

    @Override
    public int getLayoutId() {
        return R.layout.goods_details;
    }

    @Override
    public void initView() {

        showLeftAndTitle("商品详情");
        iv_img = $(R.id.iv_img);
        tv_price = $(R.id.tv_price);
        tv_title = $(R.id.tv_title);
        tv_freight = $(R.id.tv_freight);
        tv_volume = $(R.id.tv_volume);
        tv_stock = $(R.id.tv_stock);
        tv_num_demand = $(R.id.tv_num_demand);
        vebView = $(R.id.tv_content);
        btn_submit = $(R.id.btn_submit);
        numberView = $(R.id.numberView);
        group_content = $(R.id.group_content);

        btn_submit.setOnClickListener(this);


    }

    @Override
    public void initData() {
        id = intent.getStringExtra("id");
        mPresenter = new GoodsDetailsPresenter(context,this);
        mPresenter.getGoodsDetails(MySelfInfo.getInstance().getUserId(),id);
    }

    @Override
    public void getGoodsDetailsSuccess(GoodsDetailsBean data) {
        GlideUtil.loadImage(context,data.getImgUrl(),iv_img);
        tv_price.setText(data.getPriceStr());
        tv_title.setText(data.getName());
        if(data.getPostage() == 0){
            tv_freight.setVisibility(View.GONE);
        }else{
            tv_freight.setVisibility(View.VISIBLE);
            StringUtils.setHtml(tv_freight,data.getPostageStr());
        }
        tv_volume.setText(data.getSellNumStr());
        tv_stock.setText(data.getLeftNumStr());
        if(data.getMinNum() > 0){
            StringUtils.setHtml(tv_num_demand,data.getMinNumStr());
        }

        if(TextUtils.isEmpty(data.getContent())){
            group_content.setVisibility(View.GONE);
        }else{
            group_content.setVisibility(View.VISIBLE);
            vebView.loadDataWithBaseURL(null, data.getContent(), "text/html", "utf-8", null);
        }

    }

    @Override
    public void getGoodsDetailsFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//         webview 需要加载空界面来释放资源
        vebView.loadUrl("about:blank");
        vebView.clearCache(false);
        vebView.destroy();
    }
}
