package com.zqlc.www.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.luozm.captcha.Captcha;
import com.zqlc.www.R;

public class VerifyDialog extends Dialog {

    Context mContext;

    Captcha captCha;

    public VerifyDialog(Context context) {
        super(context, R.style.mdialog);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_verify, null);
        this.setContentView(layout);




        captCha = layout.findViewById(R.id.captCha);
        captCha.setMode(Captcha.MODE_BAR);

        int drawableId = (int) (Math.random() * 3); //a是已经生成的随机数
        if(drawableId == 1){
            captCha.setBitmap(R.mipmap.ic_img_varify2);
        }else if(drawableId == 2){
            captCha.setBitmap(R.mipmap.ic_img_varify3);
        }else{
            captCha.setBitmap(R.mipmap.ic_img_varify);
        }

        captCha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
                Toast.makeText(mContext, "验证成功", Toast.LENGTH_SHORT).show();


                dismiss();
                setCanceledOnTouchOutside(true);

                if (submitListener != null) {
                    submitListener.onClick();
                }

                return "验证通过";

            }

            @Override
            public String onFailed(int count) {
                Toast.makeText(mContext, "验证失败,失败次数" + count, Toast.LENGTH_SHORT).show();
                return "验证失败";
            }

            @Override
            public String onMaxFailed() {
                Toast.makeText(mContext, "验证超过次数，你的帐号被封锁", Toast.LENGTH_SHORT).show();
                return "可以走了";
            }

        });
    }

    public interface OnItemClickListener {
        void onClick();
    }

    OnItemClickListener submitListener;

    public VerifyDialog setSubmitListener(OnItemClickListener submitListener) {
        this.submitListener = submitListener;
        return this;
    }
}
