package com.zqlc.www.view.shop;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.login.MineBean;
import com.zqlc.www.bean.shop.OrderDetailBean;
import com.zqlc.www.dialog.OrderPayDialog;
import com.zqlc.www.dialog.TextDialog;
import com.zqlc.www.mvp.my.MyInfoContract;
import com.zqlc.www.mvp.my.MyInfoPresenter;
import com.zqlc.www.mvp.shop.OrderDetailContract;
import com.zqlc.www.mvp.shop.OrderDetailPresenter;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.glide.GlideUtil;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.AddressEditEvent;
import com.zqlc.www.view.security.AddressSetActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View, MyInfoContract.View, View.OnClickListener {

    OrderDetailPresenter mPresenter;
    MyInfoPresenter mInfoPresenter;

    TextView tv_status, tv_address_name, tv_address_phone, tv_address_detail, tv_address_edit, tv_name, tv_num, tv_price, tv_price_goods,
            tv_freight, tv_price_all, tv_word_NO, tv_word_NO_copy, tv_word_createTime, tv_word_payTime, tv_word_deliverTime, tv_word_receivingTime,
            btn_cancel, btn_pay,btn_receive,tv_surplus_time;

    ImageView iv_img;

    LinearLayout ll_btn;

    String orderId;

    OrderDetailBean data;

    Disposable disposableDetail;

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
        tv_surplus_time = $(R.id.tv_surplus_time);

        tv_freight = $(R.id.tv_freight);
        tv_price_all = $(R.id.tv_price_all);
        tv_word_NO = $(R.id.tv_word_NO);
        tv_word_NO_copy = $(R.id.tv_word_NO_copy);
        tv_word_createTime = $(R.id.tv_word_createTime);
        tv_word_payTime = $(R.id.tv_word_payTime);
        tv_word_deliverTime = $(R.id.tv_word_deliverTime);
        tv_word_receivingTime = $(R.id.tv_word_receivingTime);
        btn_cancel = $(R.id.btn_cancel);
        btn_receive = $(R.id.btn_receive);
        btn_pay = $(R.id.btn_pay);

        iv_img = $(R.id.iv_img);
        ll_btn = $(R.id.ll_btn);

        btn_cancel.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        tv_address_edit.setOnClickListener(this);
        tv_word_NO_copy.setOnClickListener(this);
        btn_receive.setOnClickListener(this);

    }

    @Override
    public void initData() {
        disposableDetail = RxBus2.getInstance().toObservable(AddressEditEvent.class, addressEditEvent ->
                mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(), orderId));

        mPresenter = new OrderDetailPresenter(context, this);
        mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(), orderId);

        mInfoPresenter = new MyInfoPresenter(context,this);
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
            iv_img.setImageResource(R.color.color_cc);
        } else {
            GlideUtil.loadRoundImage(context, data.getImgUrl(), iv_img, 5);
        }

        //TYPE_CREATE(1, "待付款"),
        //TYPE_PAID(2, "已支付待发货"),
        //TYPE_DELIVER(3, "已发货待收货"),
        //TYPE_RECEIVE(4, "已收货"),
        //TYPE_CANCEL(5, "已取消"),
        //TYPE_REFUND(6, "已退款");

        ll_btn.setVisibility(View.GONE);
        btn_cancel.setVisibility(View.GONE);
        btn_receive.setVisibility(View.GONE);

        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();

        if(data.getType() == 1){
            ll_btn.setVisibility(View.VISIBLE);
            btn_cancel.setVisibility(View.VISIBLE);
            btn_pay.setVisibility(View.VISIBLE);
            verifyEvent(data.getAutoCancel(),"自动取消");
        }else if(data.getType() == 3){
            btn_receive.setVisibility(View.VISIBLE);
            verifyEvent(data.getAutoCancel(),"自动收货");
        }
    }

    @Override
    public void getOrderDetailFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void receiveOrderSuccess(EmptyModel data) {
        showShortToast("确认收货成功");
        mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(),orderId);
    }

    @Override
    public void receiveOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void cancelOrderSuccess(EmptyModel data) {
        showShortToast("取消成功");
        mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(),orderId);
    }

    @Override
    public void cancelOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void payOrderSuccess(EmptyModel data) {
        showShortToast("支付成功");
        mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(),orderId);
        mOrderPayDialog.dismiss();
    }

    @Override
    public void payOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                new TextDialog(context).setContent("是否确认取消订单？").setSubmitListener(v1 -> {
                    mPresenter.cancelOrder(MySelfInfo.getInstance().getUserId(),orderId);
                }).show();
                break;
            case R.id.btn_pay:
                mInfoPresenter.mine(MySelfInfo.getInstance().getUserId(),true);
                break;
            case R.id.btn_receive:
                new TextDialog(context).setContent("是否确认收货？").setSubmitListener(v1 -> {
                    mPresenter.receiveOrder(MySelfInfo.getInstance().getUserId(),orderId);
                }).show();
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
        if (disposableDetail != null && !disposableDetail.isDisposed())
            disposableDetail.dispose();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    OrderPayDialog mOrderPayDialog;
    @Override
    public void mineSuccess(MineBean data) {
        mOrderPayDialog = new OrderPayDialog(context,this.data.getTotalSum(),data.getBeans()).setSubmitListener((dialog, content) -> {
            if(TextUtils.isEmpty(content)){
                showShortToast("请输入密码");
                return;
            }
            if(data.getBeans() < this.data.getTotalSum()){
                showShortToast("支付金豆不足");
                return;
            }
            mPresenter.payOrder(MySelfInfo.getInstance().getUserId(),orderId,content);
        });
        mOrderPayDialog.show();
    }

    @Override
    public void mineFailed(String msg) {
        showShortToast(msg);
    }

    Disposable disposable;
    public void verifyEvent(int count,String str) {
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        tv_surplus_time.setText("剩"+StringUtils.getHour(num) + str);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(),orderId);
                    }
                });
    }

    //验证码登录-------------end


}
