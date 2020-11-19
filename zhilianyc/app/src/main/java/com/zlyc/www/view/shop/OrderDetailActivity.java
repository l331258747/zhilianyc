package com.zlyc.www.view.shop;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.shop.OrderDetailBean;
import com.zlyc.www.mvp.shop.OrderDetailContract;
import com.zlyc.www.mvp.shop.OrderDetailPresenter;
import com.zlyc.www.util.glide.GlideUtil;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.AddressEditEvent;
import com.zlyc.www.view.security.AddressSetActivity;

import io.reactivex.disposables.Disposable;

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View, View.OnClickListener {

    OrderDetailPresenter mPresenter;

    TextView tv_status, tv_address_name, tv_address_phone, tv_address_detail, tv_address_edit, tv_name, tv_num, tv_price, tv_price_goods,
            tv_freight, tv_price_all, tv_word_NO, tv_word_NO_copy, tv_word_createTime, tv_word_payTime, tv_word_deliverTime, tv_word_receivingTime,
            btn_cancel, btn_pay;

    ImageView iv_img;

    LinearLayout ll_btn;

    String orderId;

    OrderDetailBean data;

    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        showLeftAndTitle("订单详情");

        orderId = intent.getStringExtra("orderId");

        tv_status = $(R.id.tv_status);
        tv_address_name = $(R.id.tv_address_name);
        tv_address_phone = $(R.id.tv_address_phone);
        tv_address_detail = $(R.id.tv_address_detail);
        tv_address_edit = $(R.id.tv_address_edit);
        tv_name = $(R.id.tv_name);
        tv_num = $(R.id.tv_num);
        tv_price = $(R.id.tv_price);
        tv_price_goods = $(R.id.tv_price_goods);

        tv_freight = $(R.id.tv_freight);
        tv_price_all = $(R.id.tv_price_all);
        tv_word_NO = $(R.id.tv_word_NO);
        tv_word_NO_copy = $(R.id.tv_word_NO_copy);
        tv_word_createTime = $(R.id.tv_word_createTime);
        tv_word_payTime = $(R.id.tv_word_payTime);
        tv_word_deliverTime = $(R.id.tv_word_deliverTime);
        tv_word_receivingTime = $(R.id.tv_word_receivingTime);
        btn_cancel = $(R.id.btn_cancel);
        btn_pay = $(R.id.btn_pay);

        iv_img = $(R.id.iv_img);
        ll_btn = $(R.id.ll_btn);

        btn_cancel.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        tv_address_edit.setOnClickListener(this);
        tv_word_NO_copy.setOnClickListener(this);

    }

    @Override
    public void initData() {
        disposable = RxBus2.getInstance().toObservable(AddressEditEvent.class, addressEditEvent ->
                mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(), orderId));

        mPresenter = new OrderDetailPresenter(context, this);
        mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(), orderId);
    }

    @Override
    public void getOrderDetailSuccess(OrderDetailBean data) {

        this.data = data;

        tv_status.setText(data.getTypeStr());

        tv_address_name.setText(data.getReceiveName());
        tv_address_phone.setText(data.getReceiveMobile());
        tv_address_detail.setText(data.getReceiveAddress());

        tv_name.setText(data.getName());
        tv_num.setText(data.getNumStr());
        tv_price.setText(data.getPriceStr());
        tv_price_goods.setText(data.getSumStr());
        tv_freight.setText(data.getPostageStr());
        tv_price_all.setText(data.getTotalSumStr());

        tv_word_NO.setText(data.getId());
        tv_word_createTime.setText(data.getCreateTime());
        tv_word_payTime.setText(data.getPayTime());
        tv_word_deliverTime.setText(data.getDeliverTime());
        tv_word_receivingTime.setText(data.getReceiveTime());

        if (TextUtils.isEmpty(data.getImgUrl())) {
            iv_img.setImageResource(R.mipmap.default_head);
        } else {
            GlideUtil.loadRoundImage(context, data.getImgUrl(), iv_img, 5);
        }

        ll_btn.setVisibility(View.GONE);
        if(data.getType() == 1){
            ll_btn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getOrderDetailFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                showShortToast("取消");
                break;
            case R.id.btn_pay:
                showShortToast("付款");
                break;
            case R.id.tv_address_edit:
                startActivity(new Intent(context, AddressSetActivity.class));
                break;
            case R.id.tv_word_NO_copy:
                ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(data.getId());
                showShortToast("复制订单编号成功");
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
