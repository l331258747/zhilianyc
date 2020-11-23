package com.zlyc.www.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.util.StringUtils;

public class OrderPayDialog  extends Dialog {

    Context mContext;

    TextView tv_price,tv_my_jingdou,btn_submit;
    EditText et_password;
    ImageView iv_close;

    float price;
    float myJingdou;

    public OrderPayDialog(Context context,float price,float myJingdou) {
        super(context, R.style.mdialog);
        mContext = context;
        this.price = price;
        this.myJingdou = myJingdou;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_order_pay, null);
        this.setContentView(layout);

        tv_price = layout.findViewById(R.id.tv_price);
        tv_my_jingdou = layout.findViewById(R.id.tv_my_jingdou);
        btn_submit = layout.findViewById(R.id.btn_submit);
        et_password = layout.findViewById(R.id.et_password);
        iv_close = layout.findViewById(R.id.iv_close);

        tv_price.setText(StringUtils.getStringNum(price));
        tv_my_jingdou.setText(StringUtils.getStringNum(myJingdou));

        btn_submit.setOnClickListener(view -> {
            if(submitListener != null){
                submitListener.onClick(OrderPayDialog.this,et_password.getText().toString());
            }
        });


        iv_close.setOnClickListener(view -> {
            dismiss();
        });
    }




    public interface OnItemClickListener {
        void onClick(Dialog dialog,String content);
    }

    OnItemClickListener submitListener;
    public OrderPayDialog setSubmitListener(OnItemClickListener submitListener) {
        this.submitListener = submitListener;
        return this;
    }
}
