package com.zlyc.www.view.otc;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
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

        int sendStatus = data.getSendStatus();
        String receiveId = data.getReceiveId();
        String sendId = data.getSendId();

        if (orderType == 0) {
            //求购单
            //uid 和 receiveId 相同
            if (TextUtils.equals(receiveId, MySelfInfo.getInstance().getUserId())) {
                if (sendStatus == 1) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("撤回订单");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                } else if (sendStatus == 2) {
                    btn_text.setVisibility(View.VISIBLE);
                    btn_text.setText("等待对方放豆");
                } else if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("上传凭证");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("确认提交");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                    });
                }
            } else {
                if (sendStatus == 1) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("我要转让");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                } else if (sendStatus == 4) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("确认放豆");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("我要申诉");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                    });
                }
            }


        } else if (orderType == 1) {
            //转让单
            if (TextUtils.equals(sendId, MySelfInfo.getInstance().getUserId())) {
                if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("撤回订单");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                } else if (sendStatus == 4) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("确认放豆");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("我要申诉");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                    });
                }
            } else {
                if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("我要求购");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                } else if (sendStatus == 2
                        && TextUtils.equals(data.getLockUid(), MySelfInfo.getInstance().getUserId())
                        && TextUtils.equals(data.getReceiveId(), MySelfInfo.getInstance().getUserId())) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("上传凭证");
                    btn_1.setOnClickListener(v -> {
                        //TODO
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("确认提交");
                    btn_2.setOnClickListener(v -> {
                        //TODO
                    });
                }
            }
        }
    }

    @Override
    public void getOtcDetailFailed(String msg) {
        showShortToast(msg);
    }
}
