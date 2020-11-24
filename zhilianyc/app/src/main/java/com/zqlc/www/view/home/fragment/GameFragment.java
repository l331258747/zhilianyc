package com.zqlc.www.view.home.fragment;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseFragment;
import com.zqlc.www.util.log.LogUtil;

import androidx.fragment.app.Fragment;
import vlion.cn.game.core.VlionGameManager;
import vlion.cn.game.reward.interfaces.VlionMediaIdCallBack;

public class GameFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        VlionGameManager.getInstance().setMediaID("157");
        VlionGameManager.getInstance().getVlionRewardFragment("default" ,new VlionMediaIdCallBack() {//sceneId 瑞狮提供
            @Override
            public void vlionGetFragmentSuccess(Fragment fragment) {
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_game, fragment)   // 此处的R.id.fragment_container是要盛放fragment的父容器
                        .commit();
            }

            @Override
            public void vlionGetFragmentFail(String message) {
                LogUtil.e("vlionGetFragmentFail: " + message );
            }


        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
