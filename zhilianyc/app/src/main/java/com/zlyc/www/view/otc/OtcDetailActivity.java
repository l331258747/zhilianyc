package com.zlyc.www.view.otc;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.otc.OtcDetailBean;
import com.zlyc.www.mvp.otc.OtcDetailContract;
import com.zlyc.www.mvp.otc.OtcDetailPresenter;
import com.zlyc.www.util.StringUtils;

public class OtcDetailActivity extends BaseActivity implements OtcDetailContract.View {

    int orderType;
    String beansSendId;
    OtcDetailPresenter mPresenter;

    TextView tv_status, tv_count_down, tv_No, tv_seller, tv_buyer, tv_unit_price, tv_number, tv_total_price, tv_order_time, tv_collection_code, tv_voucher;
    TextView btn_1, btn_2, btn_text;

    boolean isCheck;

    @Override
    public int getLayoutId() {
        return R.layout.activity_otc_detail;
    }

    @Override
    public void initView() {
        orderType = intent.getIntExtra("orderType", 0);
        beansSendId = intent.getStringExtra("beansSendId");
        if (orderType == 0) {
            showLeftAndTitle("求购单详情");
        } else {
            showLeftAndTitle("转让单详情");
        }


        btn_text = $(R.id.btn_text);
        btn_1 = $(R.id.btn_1);
        btn_2 = $(R.id.btn_2);

        tv_status = $(R.id.tv_status);
        tv_count_down = $(R.id.tv_count_down);
        tv_No = $(R.id.tv_No);
        tv_seller = $(R.id.tv_seller);
        tv_buyer = $(R.id.tv_buyer);
        tv_unit_price = $(R.id.tv_unit_price);
        tv_number = $(R.id.tv_number);
        tv_total_price = $(R.id.tv_total_price);
        tv_order_time = $(R.id.tv_order_time);
        tv_collection_code = $(R.id.tv_collection_code);
        tv_voucher = $(R.id.tv_voucher);

    }

    @Override
    public void initData() {
        mPresenter = new OtcDetailPresenter(context, this);
        mPresenter.getOtcDetail(MySelfInfo.getInstance().getUserId(), beansSendId);

        mPresenter.getOtcCheck(MySelfInfo.getInstance().getUserId(), beansSendId);
    }

    @Override
    public void getOtcDetailSuccess(OtcDetailBean data) {
        tv_status.setText(data.getSendStatusStr());
        tv_count_down.setText("倒计时。。。");
        tv_No.setText(data.getId());
        tv_seller.setText(data.getSendName());
        tv_buyer.setText(data.getReceiveName());
        tv_unit_price.setText(StringUtils.getStringNum(data.getUnitPrice()));
        tv_number.setText(data.getCount() + "");
        tv_total_price.setText(StringUtils.getStringNum(data.getTotalPrice()));
        tv_order_time.setText(data.getCreateTime());
        tv_collection_code.setText(data.getSendAccount());

        //TODO 上传图片处理 有图片显示图片，没图片显示文字
        tv_voucher.setVisibility(View.VISIBLE);
        tv_voucher.setText("没有上传付款凭证");

        btn_1.setVisibility(View.GONE);
        btn_2.setVisibility(View.GONE);
        btn_text.setVisibility(View.GONE);
        btn_1.setOnClickListener(null);
        btn_2.setOnClickListener(null);

        int sendStatus = data.getSendStatus();
        String receiveId = data.getReceiveId();
        String sendId = data.getSendId();

        if (orderType == 0) {
            //求购单
            //uid 和 receiveId 相同
            if (TextUtils.equals(receiveId, MySelfInfo.getInstance().getUserId())) {//买方
                if (sendStatus == 1) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("撤回订单");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(9);
                    });
                } else if (sendStatus == 2) {
                    btn_text.setVisibility(View.VISIBLE);
                    btn_text.setText("等待对方放豆");
                } else if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("上传凭证");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        sendVoucher();
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("确认提交");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(2);
                    });
                }
            } else {//卖方
                if (sendStatus == 1) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("我要转让");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(5);
                    });
                } else if (sendStatus == 4) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("确认放豆");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(3);
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("我要申诉");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(7);
                    });
                }
            }
        } else if (orderType == 1) {
            //转让单
            if (TextUtils.equals(sendId, MySelfInfo.getInstance().getUserId())) {//买方
                if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("撤回订单");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(9);
                    });
                } else if (sendStatus == 4) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("确认放豆");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(3);
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("我要申诉");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(7);
                    });
                }
            } else {//卖方
                if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("我要求购");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(2);
                    });
                } else if (sendStatus == 2
                        && TextUtils.equals(data.getLockUid(), MySelfInfo.getInstance().getUserId())
                        && TextUtils.equals(data.getReceiveId(), MySelfInfo.getInstance().getUserId())) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("上传凭证");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        sendVoucher();
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("确认提交");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                        if (!isCheck()) return;
                        getOtcHandle(2);

                    });
                }
            }
        }
    }

    //上传凭证 TODO
    private void sendVoucher() {
//        mPresenter.getOtcVoucher(MySelfInfo.getInstance().getUserId(),beansSendId,);
    }

    //otc sendStatus 0已成交1订单已发起2订单已锁定3卖方已放豆4买方已付款5卖方确认7卖方申诉中9用户撤回10系统撤回11系统解除申诉
    public void getOtcHandle(int sendStatus){
        mPresenter.getOtcHandle(MySelfInfo.getInstance().getUserId(),sendStatus,beansSendId);
    }

    public boolean isCheck() {
        if (!isCheck)
            showShortToast("该订单被占用！");
        return isCheck;
    }

    @Override
    public void getOtcDetailFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getOtcVoucherSuccess(EmptyModel data) {
        //支付凭证
    }

    @Override
    public void getOtcVoucherFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getOtcCheckSuccess(EmptyModel data) {
        //校验
        isCheck = true;
    }

    @Override
    public void getOtcCheckFailed(String msg) {
        showShortToast(msg);
        isCheck = false;
    }

    @Override
    public void getOtcHandleSuccess(EmptyModel data) {
        //处理
    }

    @Override
    public void getOtcHandleFailed(String msg) {
        showShortToast(msg);
    }
}
