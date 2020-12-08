package com.zqlc.www.view.user;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.ActivityCollect;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.user.TaskBean;
import com.zqlc.www.mvp.user.TaskContract;
import com.zqlc.www.mvp.user.TaskPresenter;
import com.zqlc.www.view.home.HomeActivity;

import androidx.constraintlayout.widget.Group;

public class MyTaskActivity extends BaseActivity implements View.OnClickListener, TaskContract.View {

    TextView tv_num_all, tv_num_today, tv_num_use, tv_task_all, tv_video, tv_checkIn, tv_game, tv_read, tv_luck;
    TextView tv_video2, tv_checkIn2, tv_game2, tv_read2, tv_luck2;
    TextView btn_video, btn_checkIn, btn_game, btn_read, btn_luck;

    Group group_game;

    TaskPresenter mPresenter;

    String beans;
    String todayBeans;
    String sellableBeans;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_task;
    }

    @Override
    public void initView() {
        showLeftAndTitle("进入任务");

        beans = intent.getStringExtra("beans");
        todayBeans = intent.getStringExtra("todayBeans");
        sellableBeans = intent.getStringExtra("sellableBeans");


        tv_num_all = $(R.id.tv_num_all);
        tv_num_today = $(R.id.tv_num_today);
        tv_num_use = $(R.id.tv_num_use);

        group_game = $(R.id.group_game);

        tv_num_all.setText(beans);
        tv_num_today.setText(todayBeans);
        tv_num_use.setText(sellableBeans);

        tv_task_all = $(R.id.tv_task_all);
        tv_video = $(R.id.tv_video);
        tv_checkIn = $(R.id.tv_checkIn);
        tv_game = $(R.id.tv_game);
        tv_read = $(R.id.tv_read);
        tv_luck = $(R.id.tv_luck);
        btn_video = $(R.id.btn_video);
        btn_checkIn = $(R.id.btn_checkIn);
        btn_game = $(R.id.btn_game);
        btn_read = $(R.id.btn_read);
        btn_luck = $(R.id.btn_luck);

        tv_video2 = $(R.id.tv_video2);
        tv_checkIn2 = $(R.id.tv_checkIn2);
        tv_game2 = $(R.id.tv_game2);
        tv_read2 = $(R.id.tv_read2);
        tv_luck2 = $(R.id.tv_luck2);

        btn_video.setOnClickListener(this);
        btn_checkIn.setOnClickListener(this);
        btn_game.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        btn_luck.setOnClickListener(this);

        if(TextUtils.equals(MySelfInfo.getInstance().getUserState(),"1")){
            group_game.setVisibility(View.GONE);
        }else{
            group_game.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initData() {
        mPresenter = new TaskPresenter(context, this);

        mPresenter.getTask(MySelfInfo.getInstance().getUserId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_video:
                startActivity(new Intent(context,IncentiveVideoActivity.class));
                break;
            case R.id.btn_checkIn:
                mPresenter.signin(MySelfInfo.getInstance().getUserId());
                break;
            case R.id.btn_game:
                ActivityCollect.getAppCollect().finishAllNotHome();
                HomeActivity activity1 = (HomeActivity) ActivityCollect.getAppCollect().findActivity(HomeActivity.class);
                if (activity1 != null) {
                    activity1.setTabIndex(1);
                }
                break;
            case R.id.btn_read:
                ActivityCollect.getAppCollect().finishAllNotHome();
                HomeActivity activity0 = (HomeActivity) ActivityCollect.getAppCollect().findActivity(HomeActivity.class);
                if (activity0 != null) {
                    activity0.setTabIndex(0);
                }
                break;
            case R.id.btn_luck:
                startActivity(new Intent(context,ExcitationActivity.class));
                break;

        }
    }

    @Override
    public void getTaskSuccess(TaskBean data) {
        tv_task_all.setText(data.getTaskAll());

        tv_video.setText(data.getVideoMission());
        tv_checkIn.setText(data.getSignin());
        tv_game.setText(data.getGame());
        tv_read.setText(data.getReadNews());
        tv_luck.setText(data.getAward());

        setBtn(btn_video, data.getVideoMissionMax(), data.getVideoMissionNum());
        setBtn(btn_checkIn, data.getSigninMax(), data.getSigninNum());
        setBtn(btn_game, data.getGameMax(), data.getGameNum());
        setBtn(btn_read, data.getReadNewsMax(), data.getReadNewsNum());
        setBtn(btn_luck, data.getAwardMax(), data.getAwardNum());

        tv_video2.setText(data.getVideoBeansStr());
        tv_checkIn2.setText(data.getSigninBeansStr());
        tv_game2.setText(data.getGameSellableBeansStr());
        tv_read2.setText(data.getReadNewsContributionStr());
        tv_luck2.setText(data.getAwardContributionStr());
    }

    public void setBtn(TextView btn, int max, int num) {
        if (max <= num) {
            btn.setText("已完成");
            btn.setBackgroundResource(R.drawable.btn_cc_r40);
        } else {
            btn.setText("去完成");
            btn.setBackgroundResource(R.drawable.bg_gradients_btn_368feb);
        }
    }

    @Override
    public void getTaskFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void signinSuccess(String data) {
        showShortToast("签到成功");
        mPresenter.getTask(MySelfInfo.getInstance().getUserId());
    }

    @Override
    public void signinFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getTask(MySelfInfo.getInstance().getUserId());
    }
}
