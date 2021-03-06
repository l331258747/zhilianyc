package com.zqlc.www.util.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.otc.TradeRuleBean;
import com.zqlc.www.dialog.VerifyDialog;
import com.zqlc.www.mvp.my.SendCodeContract;
import com.zqlc.www.mvp.my.SendCodePresenter;
import com.zqlc.www.mvp.my.TradeRuleContract;
import com.zqlc.www.mvp.my.TradeRulePresenter;
import com.zqlc.www.mvp.otc.OtcSellContract;
import com.zqlc.www.mvp.otc.OtcSellPresenter;
import com.zqlc.www.util.DecimalUtil;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.MyTextWatcher.MyTexxtWatcher;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.ToastUtil;
import com.zqlc.www.util.log.LogUtil;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.OtcMarkerEvent;

import java.util.concurrent.TimeUnit;

import androidx.core.content.ContextCompat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by LGQ
 * Time: 2018/12/14
 * Function:
 */

public class PopOtcSellSend extends BackgroundDarkPopupWindow implements OtcSellContract.View, SendCodeContract.View, TradeRuleContract.View {
    private View contentView;
    private Activity context;

    EditText et_price,et_num,et_password,et_verify;
    TextView tv_verify_code,btn_submit,tv_fee,tip_num,tip_price;

    OtcSellPresenter mPresenter;

    SendCodePresenter mPresenterCode;
    TradeRulePresenter mPresenterRule;

    float feeRate = 0;

    public PopOtcSellSend(final Activity context, View parentView,float feeRate) {
        super(parentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        contentView = inflater.inflate(R.layout.popup_otc_sell_send, null);
        this.context = context;
        this.feeRate = feeRate;

        initView();

        this.setContentView(contentView);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(dw);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        this.setOutsideTouchable(true);
        this.setFocusable(true);

    }

    private void initView() {
        et_verify = contentView.findViewById(R.id.et_verify);
        tv_verify_code = contentView.findViewById(R.id.tv_verify_code);
        et_price = contentView.findViewById(R.id.et_price);
        et_num = contentView.findViewById(R.id.et_num);
        et_password = contentView.findViewById(R.id.et_password);
        btn_submit = contentView.findViewById(R.id.btn_submit);
        tv_fee = contentView.findViewById(R.id.tv_fee);
        tip_num = contentView.findViewById(R.id.tip_num);
        tip_price = contentView.findViewById(R.id.tip_price);

        btn_submit.setOnClickListener(v -> {


            if(mTradeRuleBean == null){
                ToastUtil.showLongToast(context,"获取注释失败，请重新进入");
                return;
            }
            if(!LoginUtil.verifyEmpty(et_price.getText().toString(),"请输入单价"))
                return;
            if(!LoginUtil.verifyEmpty(et_num.getText().toString(),"请输入数量"))
                return;
            if (!LoginUtil.verifyEmpty(et_password.getText().toString(),"密码不能为空"))
                return;
            if (!LoginUtil.verifyVerify(et_verify.getText().toString()))
                return;
            if(Float.valueOf(et_num.getText().toString()) < 1){
                ToastUtil.showShortToast(context,"数量最低1个");
                return;
            }
            if(Float.valueOf(et_num.getText().toString()) < mTradeRuleBean.getSmall_big_order_num()){
                if(Float.valueOf(et_price.getText().toString()) < mTradeRuleBean.getBuy_small_min()
                        || Float.valueOf(et_price.getText().toString()) > mTradeRuleBean.getBuy_small_max()){
                    ToastUtil.showLongToast(context,"请输入正确的散单单价");
                    return;
                }
            }else{
                if(Float.valueOf(et_price.getText().toString()) < mTradeRuleBean.getBuy_big_min()
                        || Float.valueOf(et_price.getText().toString()) > mTradeRuleBean.getBuy_big_max()){
                    ToastUtil.showLongToast(context,"请输入正确的整单单价");
                    return;
                }
            }

            mPresenter.sendOtcSell(MySelfInfo.getInstance().getUserId(),
                    Float.valueOf(et_price.getText().toString()),
                    Integer.parseInt(et_num.getText().toString()),
                    et_password.getText().toString(),
                    et_verify.getText().toString());
        });

        tv_verify_code.setOnClickListener(v -> {
            new VerifyDialog(context).setSubmitListener(() -> {
                verifyEvent();
                mPresenterCode.sendCode(MySelfInfo.getInstance().getUserMobile());
            }).show();
        });

        tv_fee.setText("手续费：" + 0 + "金豆");

        et_num.addTextChangedListener(new MyTexxtWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                tv_fee.setVisibility(View.GONE);
                if(TextUtils.isEmpty(s.toString())){
                    tv_fee.setText("手续费：" + 0 + "金豆");
                    return;
                }
                tv_fee.setVisibility(View.VISIBLE);
                int num = Integer.parseInt(s.toString());
                tv_fee.setText("手续费：" + DecimalUtil.multiply(num , feeRate) + "金豆");

            }
        });

        initData();
    }

    private void initData() {
        mPresenter = new OtcSellPresenter(context,this);
        mPresenterCode = new SendCodePresenter(context,this);

        mPresenterRule = new TradeRulePresenter(context,this);

        mPresenterRule.beansTradeRule();
    }


    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            setDarkStyle(-1);
            setDarkColor(Color.parseColor("#a0000000"));
            resetDarkPosition();
            darkAbove(parent);

            showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    public void dismissPopupWindow() {
        if (this.isShowing())
            this.dismiss();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    Disposable disposable;

    private void verifyEvent() {
        final int count = 60;//倒计时10秒
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                    tv_verify_code.setEnabled(false);//在发送数据的时候设置为不能点击
                    tv_verify_code.setTextColor(ContextCompat.getColor(context, R.color.color_66));
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        StringUtils.setHtml(tv_verify_code, String.format(context.getResources().getString(R.string.verify), num));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //回复原来初始状态
                        tv_verify_code.setEnabled(true);
                        tv_verify_code.setText("重新发送");
                        tv_verify_code.setTextColor(ContextCompat.getColor(context, R.color.white));
                    }
                });
    }

    @Override
    public void sendOtcSellSuccess(EmptyModel data) {
        ToastUtil.showShortToast(context,"发布成功");
        RxBus2.getInstance().post(new OtcMarkerEvent());
        dismissPopupWindow();
    }

    @Override
    public void sendOtcSellFailed(String msg) {
        ToastUtil.showShortToast(context,msg);
    }

    @Override
    public void sendCodeSuccess(EmptyModel data) {
        ToastUtil.showShortToast(context,"手机验证码发送成功");
    }

    @Override
    public void sendCodeFailed(String msg) {
        ToastUtil.showShortToast(context,msg);
    }


    TradeRuleBean mTradeRuleBean;
    @Override
    public void beansTradeRuleSuccess(TradeRuleBean data) {
        this.mTradeRuleBean = data;
        String priceStr = "散单单价：" + data.getSell_small_min_str() + "-" + data.getSell_small_max_str()
                + "元，整单单价：" + data.getSell_big_min_str() + "-" + data.getSell_big_max_str() + "元";
        String numStr = "散单：小于" + data.getSmall_big_order_num_str() + "个，整单：大于等于" + data.getSmall_big_order_num_str() + "个";
        tip_price.setText(priceStr);
        tip_num.setText(numStr);
    }

    @Override
    public void beansTradeRuleFailed(String msg) {
        LogUtil.e(msg);
    }
}
