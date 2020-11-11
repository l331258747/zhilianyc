package com.zlyc.www.view.my;

import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.my.MyTeamAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.my.MyTeamBean;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTeamActivity extends BaseActivity {

    TextView tv_position_num,tv_effective_value_num,tv_team_people_num,tv_team_labour_num,tv_elite_labour_num,tv_union_labour_num;
    TextView tv_people_director_num,tv_people_manager_num,tv_people_big_manager_num,tv_people_small_boss_num,tv_people_boss_num;
    RecyclerView recyclerView;

    MyTeamAdapter mAdapter;

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

        tv_people_director_num = $(R.id.tv_people_director_num);
        tv_people_manager_num = $(R.id.tv_people_manager_num);
        tv_people_big_manager_num = $(R.id.tv_people_big_manager_num);
        tv_people_small_boss_num = $(R.id.tv_people_small_boss_num);
        tv_people_boss_num = $(R.id.tv_people_boss_num);

        initRecycler();
    }

    @Override
    public void initData() {
        tv_position_num.setText("245");
        tv_effective_value_num.setText("52");
        tv_team_people_num.setText("124");
        tv_team_labour_num.setText("223");
        tv_elite_labour_num.setText("363");
        tv_union_labour_num.setText("553");

        tv_people_director_num.setText("112");
        tv_people_manager_num.setText("24");
        tv_people_big_manager_num.setText("5");
        tv_people_small_boss_num.setText("2");
        tv_people_boss_num.setText("1");
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyTeamBean myTabBean = new MyTeamBean();
        List<MyTeamBean>  datas = myTabBean.getDatas();
        mAdapter = new MyTeamAdapter(context, datas);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }
}
