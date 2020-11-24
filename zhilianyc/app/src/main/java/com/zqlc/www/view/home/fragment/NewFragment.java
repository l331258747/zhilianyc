package com.zqlc.www.view.home.fragment;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseFragment;

import androidx.fragment.app.Fragment;
import vlion.cn.news.VlionWebFragment;
import vlion.cn.news.core.VlionNewsManager;
import vlion.cn.news.interfaces.LoadH5OrNativeInterface;

public class NewFragment extends BaseFragment {
    private VlionWebFragment vlionWebFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {

        VlionNewsManager.getInstance().startWebOrNetive(context, "default", new LoadH5OrNativeInterface() { //scence场景必选参数，瑞狮提供
            @Override
            public void loadH5OrNative(Fragment fragment) {
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_news, fragment)   // 此处的R.id.fragment_container是要盛放fragment的父容器
                        .commit();

                //用于返回按钮的实现，可选
                if (fragment instanceof VlionWebFragment) {
                    vlionWebFragment = (VlionWebFragment) fragment;
                }
            }
            @Override
            public void loadFail(String s) {//失败的回调

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
