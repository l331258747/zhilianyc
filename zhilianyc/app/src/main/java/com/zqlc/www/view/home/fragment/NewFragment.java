package com.zqlc.www.view.home.fragment;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.mvp.ad.ReadNewsContract;
import com.zqlc.www.mvp.ad.ReadNewsPresenter;

import androidx.fragment.app.Fragment;
import vlion.cn.news.VlionWebFragment;
import vlion.cn.news.core.VlionNewsManager;
import vlion.cn.news.interfaces.LoadH5OrNativeInterface;

public class NewFragment extends BaseFragment implements ReadNewsContract.View {
    private VlionWebFragment vlionWebFragment;

    ReadNewsPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {
        mPresenter = new ReadNewsPresenter(context,this);

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


        //表示点击列表是否进入到了详情页面。
        VlionNewsManager.getInstance().setVlionIncomeDetailCallBack(isDetail -> {
            if(isDetail){
                mPresenter.readNewsCallback();
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void readNewsCallbackSuccess(EmptyModel datas) {
        
    }

    @Override
    public void readNewsCallbackFailed(String msg) {

    }
}
