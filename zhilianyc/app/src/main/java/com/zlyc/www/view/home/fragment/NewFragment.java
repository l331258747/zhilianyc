package com.zlyc.www.view.home.fragment;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseFragment;

import androidx.fragment.app.Fragment;

public class NewFragment extends BaseFragment {


//    public static NewFragment newInstance() {
//
//        Bundle args = new Bundle();
//
//        NewFragment fragment = new NewFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static Fragment newInstance() {
        NewFragment fragment = new NewFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
