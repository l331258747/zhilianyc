package com.zlyc.www.view.my;

import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.my.MyTeamAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.team.MyTeamDetailBean;
import com.zlyc.www.bean.team.TeamInviteBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.mvp.team.MyTeamContract;
import com.zlyc.www.mvp.team.MyTeamPresenter;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTeamActivity extends BaseActivity implements MyTeamContract.View {

    TextView tv_position_num,tv_effective_value_num,tv_team_people_num,tv_team_labour_num,tv_elite_labour_num,tv_union_labour_num;
    TextView tv_people_director_num,tv_people_manager_num,tv_people_big_manager_num,tv_people_small_boss_num,tv_people_boss_num;
    TextView tv_invite_people,tv_invite_valid;
    RecyclerView recyclerView;

    MyTeamAdapter mAdapter;

    MyTeamPresenter mPresenter;

    int page;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_team;
    }

    @Override
    public void initView() {

        showLeftAndTitle("我的团队");

        tv_position_num = $(R.id.tv_position_num);
        tv_effective_value_num = $(R.id.tv_effective_value_num);
        tv_team_people_num = $(R.id.tv_team_people_num);
        tv_team_labour_num = $(R.id.tv_team_labour_num);
        tv_elite_labour_num = $(R.id.tv_elite_labour_num);
        tv_union_labour_num = $(R.id.tv_union_labour_num);

        tv_invite_people = $(R.id.tv_invite_people);
        tv_invite_valid = $(R.id.tv_invite_valid);

        tv_people_director_num = $(R.id.tv_people_director_num);
        tv_people_manager_num = $(R.id.tv_people_manager_num);
        tv_people_big_manager_num = $(R.id.tv_people_big_manager_num);
        tv_people_small_boss_num = $(R.id.tv_people_small_boss_num);
        tv_people_boss_num = $(R.id.tv_people_boss_num);

        initRecycler();
    }

    @Override
    public void initData() {
        page = Constant.DEFAULT_PAGE;

        mPresenter = new MyTeamPresenter(context,this);
        mPresenter.getTeamDetail(MySelfInfo.getInstance().getUserId());
        mPresenter.getTeamInvite(MySelfInfo.getInstance().getUserId(), page);
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MyTeamAdapter(context, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void getTeamDetailSuccess(MyTeamDetailBean data) {
        tv_position_num.setText(data.getRank());
        tv_effective_value_num.setText(data.getInviteNum() + "");
        tv_team_people_num.setText(data.getTeamUserNum() + "");
        tv_team_labour_num.setText(data.getTeamLabor() + "");
        tv_elite_labour_num.setText(data.getEliteLabor() + "");
        tv_union_labour_num.setText(data.getUnionLabor() + "");

        tv_people_director_num.setText(data.getDirectorNum() + "");
        tv_people_manager_num.setText(data.getViceManagerNum() + "");
        tv_people_big_manager_num.setText(data.getGeneralManagerNum() + "");
        tv_people_small_boss_num.setText(data.getViceManagerNum() + "");
        tv_people_boss_num.setText(data.getPresidentNum() + "");

    }

    @Override
    public void getTeamDetailFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getTeamInviteSuccess(TeamInviteBean data) {
        tv_invite_people.setText(data.getTotalNum() + "");
        tv_invite_valid.setText(data.getInviteNum() + "");

        mAdapter.setData(data.getTeams());
    }

    @Override
    public void getTeamInviteFailed(String msg) {
        showShortToast(msg);
    }
}
