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
import com.zqlc.www.bean.otc.TradeRuleBean;
import com.zqlc.www.mvp.my.TradeRuleContract;
import com.zqlc.www.mvp.my.TradeRulePresenter;
import com.zqlc.www.mvp.otc.OtcBuyContract;
import com.zqlc.www.mvp.otc.OtcBuyPresenter;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.ToastUtil;
import com.zqlc.www.util.log.LogUtil;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.OtcMarkerEvent;

/**
 * Created by LGQ
 * Time: 2018/12/14
 * Function:
 */

public class PopOtcBuySend extends BackgroundDarkPopupWindow implements OtcBuyContract.View, TradeRuleContract.View {
    private View contentView;
    private Activity context;

    EditText et_price,et_num,et_password;
    TextView btn_submit,tip_num,tip_price;

    OtcBuyPresenter mPresenter;
    TradeRulePresenter mPresenterRule;


    public PopOtcBuySend(final Activity context, View parentView) {
        super(parentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        contentView = inflater.inflate(R.layout.popup_otc_buy_send, null);
        this.context = context;

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
        mPresenter = new OtcBuyPresenter(context, this);
        mPresenterRule = new TradeRulePresenter(context,this);

        mPresenterRule.beansTradeRule();
    }

    private void initView() {
        et_price = contentView.findViewById(R.id.et_price);
        et_num = contentView.findViewById(R.id.et_num);
        et_password = contentView.findViewById(R.id.et_password);
        btn_submit = contentView.findViewById(R.id.btn_submit);
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
            if(Float.valueOf(et_num.getText().toString()) < 1){
                ToastUtil.showShortToast(context,"数量最低1个");
                return;
            }
            if(Float.valueOf(et_num.getText().toString()) < mTradeRuleBean.getSmall_big_order_num()){
                if(Double.valueOf(et_price.getText().toString()) < mTradeRuleBean.getBuy_small_min()
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

            mPresenter.sendOtcBuy(MySelfInfo.getInstance().getUserId(),
                    Float.valueOf(et_price.getText().toString()),
                    Integer.parseInt(et_num.getText().toString()),
                    et_password.getText().toString());
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
        if (this.isShowing())
            this.dismiss();
    }

    @Override
    public void sendOtcBuySuccess(EmptyModel data) {
        ToastUtil.showShortToast(context,"发布成功");
        RxBus2.getInstance().post(new OtcMarkerEvent());
        dismissPopupWindow();
    }

    @Override
    public void sendOtcBuyFailed(String msg) {
        ToastUtil.showShortToast(context,msg);
    }

    TradeRuleBean mTradeRuleBean;
    @Override
    public void beansTradeRuleSuccess(TradeRuleBean data) {
        this.mTradeRuleBean = data;
        String priceStr = "散单单价：" + data.getBuy_small_min_str() + "-" + data.getBuy_small_max_str()
                + "元，整单单价：" + data.getBuy_big_min_str() + "-" + data.getBuy_big_max_str() + "元";
        String numStr = "散单：小于" + data.getSmall_big_order_num_str() + "个，整单：大于等于" + data.getSmall_big_order_num_str() + "个";
        tip_price.setText(priceStr);
        tip_num.setText(numStr);
    }

    @Override
    public void beansTradeRuleFailed(String msg) {
        LogUtil.e(msg);
    }
}
