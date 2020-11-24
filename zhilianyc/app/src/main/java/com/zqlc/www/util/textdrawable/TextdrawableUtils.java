package com.zqlc.www.util.textdrawable;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

public class TextdrawableUtils {

    Context mContext;
    String lableStr;

    public TextdrawableUtils(Context context,String lableStr) {
        mContext = context;
        getTextDrawable(lableStr);
    }

    private int width;
    private int height;
    Drawable drawable;

    private void getTextDrawable(String lableStr){
        width = mContext.getResources().getDisplayMetrics().widthPixels;
        height = mContext.getResources().getDisplayMetrics().heightPixels;
        height = height / 38;
        width = width / 25;

        drawable = TextDrawable.builder()
                .beginConfig()
                .width(lableStr.length() * width)  // width in px
                .height(height) // height in px
                .textColor(Color.parseColor("#368FEB"))
                .fontSize((int) (height * 0.7))
                .endConfig()
                .buildRoundRect(lableStr, Color.parseColor("#E5F2FF"), 5);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
    }

    public void setTextStyle(String content, TextView tv) {
        SpannableString spanText = new SpannableString(" " + " " + " " + content);
        // 替换0,1的字符
        if (drawable != null) {
            spanText.setSpan(new ImageSpan(drawable), 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            spanText.setSpan("", 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        tv.setText(spanText);
    }
}
