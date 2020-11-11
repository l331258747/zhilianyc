package com.zlyc.www.adapter.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.my.MyTeamBean;
import com.zlyc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyTeamAdapter extends RecyclerView.Adapter<MyTeamAdapter.ViewHolder> {

    Context mContext;
    List<MyTeamBean> datas;

    public MyTeamAdapter(Context context, List<MyTeamBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_my_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (holder == null) return;
        final MyTeamBean data = datas.get(position);
        if (data == null) return;

        GlideUtil.LoadCircleImage(mContext,data.getImgUrl(),holder.iv_head);
        holder.tv_post.setText(data.getPost());
        holder.tv_people.setText(data.getLaborPersonal() + "");
        holder.tv_team.setText(data.getLaborTeam() + "");

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MyTeamBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_head;
        TextView tv_post,tv_people,tv_team;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_head = itemView.findViewById(R.id.iv_head);
            tv_post = itemView.findViewById(R.id.tv_post);
            tv_people = itemView.findViewById(R.id.tv_people);
            tv_team = itemView.findViewById(R.id.tv_team);

        }
    }
}
