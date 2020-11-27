package com.zqlc.www.util.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.zqlc.www.bean.otc.OtcDetailBean;
import com.zqlc.www.dialog.VerifyDialog;
import com.zqlc.www.mvp.my.SendCodeContract;
import com.zqlc.www.mvp.my.SendCodePresenter;
import com.zqlc.www.util.DecimalUtil;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.ToastUtil;

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

public class PopOtcSellSubmit extends BackgroundDarkPopupWindow implements SendCodeContract.View{
    private View contentView;
    private Activity context;

    EditText et_password,et_verify;
    TextView btn_submit,tv_verify_code;
    TextView et_account,et_price,et_num;

    SendCodePresenter mPresenterCode;
    OtcDetailBean data;

    public PopOtcSellSubmit(final Activity context, View parentView, OtcDetailBean data) {
        super(parentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        contentView = inflater.inflate(R.layout.popup_otc_sell_submit, null);
        this.context = context;
        this.data = data;

        initView();
        initData();

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

    private void initData() {
        mPresenterCode = new SendCodePresenter(context,this);
    }

    private void initView() {
        et_password = contentView.findViewById(R.id.et_password);
        et_verify = contentView.findViewById(R.id.et_verify);
        btn_submit = contentView.findViewById(R.id.btn_submit);
        tv_verify_code = contentView.findViewById(R.id.tv_verify_code);
        et_account = contentView.findViewById(R.id.et_account);
        et_price = contentView.findViewById(R.id.et_price);
        et_num = contentView.findViewById(R.id.et_num);

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
            if (!LoginUtil.verifyPasswordPay(et_password.getText().toString()))
                return;
            if (!LoginUtil.verifyVerify(et_verify.getText().toString()))
                return;

            if(mOnItemClickListener != null){
                mOnItemClickListener.onClick(et_password.getText().toString(),et_verify.getText().toString());
                dismissPopupWindow();
            }
        });

        et_price.setText(StringUtils.getStringNum(data.getUnitPrice()));
        et_num.setText(StringUtils.getStringNum(data.getCount()));
        et_account.setText(DecimalUtil.multiply(data.getCount() , data.getUnitPrice()) + "");

        tv_verify_code.setOnClickListener(v -> {
            new VerifyDialog(context).setSubmitListener(() -> {
                verifyEvent();
                mPresenterCode.sendCode(MySelfInfo.getInstance().getUserMobile());
            }).show();
        });

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
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        if (this.isShowing())
            this.dismiss();
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
    public void sendCodeSuccess(EmptyModel data) {
        ToastUtil.showShortToast(context,"手机验证码发送成功");
    }

    @Override
    public void sendCodeFailed(String msg) {
        ToastUtil.showShortToast(context,msg);
    }


    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(String pwd,String vCode);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
