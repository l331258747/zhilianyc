package com.zqlc.www.view.web;

import android.text.TextUtils;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.util.webview.LWebView;

public class WebTextActivity extends BaseActivity {

    LWebView vebView;

    String content;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_text;
    }

    @Override
    public void initView() {
        showLeftAndTitle("详情");
        content = intent.getStringExtra("web_text");
        vebView = $(R.id.tv_content);
    }

    @Override
    public void initData() {
        if(!TextUtils.isEmpty(content)){
            vebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//         webview 需要加载空界面来释放资源
        vebView.loadUrl("about:blank");
        vebView.clearCache(false);
        vebView.destroy();
    }
}
