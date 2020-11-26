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
import com.zqlc.www.dialog.VerifyDialog;
import com.zqlc.www.mvp.otc.OtcSellContract;
import com.zqlc.www.mvp.otc.OtcSellPresenter;
import com.zqlc.www.util.DecimalUtil;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.MyTextWatcher.MyTexxtWatcher;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.ToastUtil;
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

public class PopOtcSell extends BackgroundDarkPopupWindow implements OtcSellContract.View {
    private View contentView;
    private Activity context;

    EditText et_price,et_num,et_password,et_verify;
    TextView tv_verify_code,btn_submit;
    TextView et_account;

    OtcSellPresenter mPresenter;

    public PopOtcSell(final Activity context, View parentView) {
        super(parentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        contentView = inflater.inflate(R.layout.popup_otc_sell, null);
        this.context = context;

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
        et_account = contentView.findViewById(R.id.et_account);
        et_price = contentView.findViewById(R.id.et_price);
        et_num = contentView.findViewById(R.id.et_num);
        et_password = contentView.findViewById(R.id.et_password);
        btn_submit = contentView.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            if(!LoginUtil.verifyEmpty(et_price.getText().toString(),"请输入单价"))
                return;
            if(!LoginUtil.verifyEmpty(et_num.getText().toString(),"请输入数量"))
                return;
            if(!LoginUtil.verifyEmpty(et_account.getText().toString(),"请输入数量和单价"))
                return;
            if(Float.valueOf(et_account.getText().toString()) <= 0){
                ToastUtil.showLongToast(context,"请输入正确的数量和单价");
                return;
            }
            if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                return;
            if (!LoginUtil.verifyVerify(et_verify.getText().toString()))
                return;

            mPresenter.sendOtcSell(MySelfInfo.getInstance().getUserId(),
                    Float.valueOf(et_price.getText().toString()),
                    Integer.parseInt(et_num.getText().toString()),
                    et_password.getText().toString(),
                    et_verify.getText().toString());
        });

        tv_verify_code.setOnClickListener(v -> {
            new VerifyDialog(context).setSubmitListener(() -> {
                verifyEvent();
            }).show();
        });

        et_price.addTextChangedListener(new MyTexxtWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(et_num.getText().toString())){
                    et_account.setText(0 + "");
                    return;
                }
                int num = Integer.parseInt(et_num.getText().toString());

                if(TextUtils.isEmpty(s.toString())){
                    et_account.setText(0 + "");
                    return;
                }


                et_account.setText(DecimalUtil.multiply(num , Float.parseFloat(s.toString())) + "");
            }
        });

        et_num.addTextChangedListener(new MyTexxtWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(et_price.getText().toString())){
                    et_account.setText(0 + "");
                    return;
                }
                float price = Float.parseFloat(et_price.getText().toString());

                if(TextUtils.isEmpty(s.toString())){
                    et_account.setText(0 + "");
                    return;
                }

                et_account.setText(DecimalUtil.multiply(price , Integer.parseInt(s.toString())) + "");
            }
        });

        et_account.setText(0+"");

        initData();
    }

    private void initData() {
        mPresenter = new OtcSellPresenter(context,this);
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
}
