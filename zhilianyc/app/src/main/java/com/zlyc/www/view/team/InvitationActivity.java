package com.zlyc.www.view.team;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.util.StatusBarUtil;
import com.zlyc.www.util.file.ImageBitmapUtil;
import com.zlyc.www.util.zxing.ZxingUtils;

import androidx.core.content.ContextCompat;

public class InvitationActivity extends BaseActivity implements View.OnClickListener {

    TextView tv_invitation_code,btn_Invitation_code,btn_qr_code,btn_link;

    ImageView iv_invitation_code;

    Bitmap qrCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invitation;
    }

    @Override
    public void initView() {
        hideTitleLayout();

        StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context,R.color.color_1C81E9));

        tv_invitation_code = $(R.id.tv_invitation_code);
        btn_Invitation_code = $(R.id.btn_Invitation_code);
        btn_qr_code = $(R.id.btn_qr_code);
        btn_link = $(R.id.btn_link);
        iv_invitation_code = $(R.id.iv_invitation_code);


        btn_Invitation_code.setOnClickListener(this);
        btn_qr_code.setOnClickListener(this);
        btn_link.setOnClickListener(this);


        qrCode = ZxingUtils.createQRCode(MySelfInfo.getInstance().getShareUrl());
        iv_invitation_code.setImageBitmap(qrCode);

    }

    @Override
    public void initData() {
        tv_invitation_code.setText(MySelfInfo.getInstance().getInviteCode());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Invitation_code:
                runOnUiThread(() -> {
                    ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    copy.setText(MySelfInfo.getInstance().getInviteCode());
                    showShortToast("复制邀请码成功");
                });
                break;
            case R.id.btn_qr_code:
                ImageBitmapUtil.saveBitmap2file(qrCode, getApplicationContext());
                break;
            case R.id.btn_link:
                runOnUiThread(() -> {
                    ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    copy.setText(MySelfInfo.getInstance().getShareUrl());
                    showShortToast("复制邀请链成功");
                });
                break;

        }
    }
}
