package com.zqlc.www.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zqlc.www.R;

public class TipDialog extends Dialog {

    Context mContext;

    TextView tv_title,btn_submit,tv_content;


    public TipDialog(Context context) {
        super(context, R.style.mdialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_tip, null);
        this.setContentView(layout);

        btn_submit = layout.findViewById(R.id.btn_submit);
        tv_content = layout.findViewById(R.id.tv_content);
        tv_title = layout.findViewById(R.id.tv_title);

        if(!TextUtils.isEmpty(title))
            tv_title.setText(title);

        if(!TextUtils.isEmpty(content)){
            tv_content.setText(content);
        }


        btn_submit.setOnClickListener(view -> {
            if(submitListener != null){
                submitListener.onClick(view);
            }
            dismiss();
        });
    }

    String title;
    public TipDialog setTitle(String title){
        this.title = title;
        return this;
    }

    String content;
    public TipDialog setContent(String content){
        this.content = content;
        return this;
    }

    View.OnClickListener submitListener;
    public TipDialog setSubmitListener(View.OnClickListener submitListener) {
        this.submitListener = submitListener;
        return this;
    }

}