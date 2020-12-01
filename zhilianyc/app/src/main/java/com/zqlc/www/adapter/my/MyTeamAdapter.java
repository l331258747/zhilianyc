package com.zqlc.www.adapter.my;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.team.TeamInviteListBean;
import com.zqlc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyTeamAdapter extends RecyclerView.Adapter<MyTeamAdapter.ViewHolder> {

    Context mContext;
    List<TeamInviteListBean> datas;

    public MyTeamAdapter(Context context, List<TeamInviteListBean> datas) {
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
        final TeamInviteListBean data = datas.get(position);
        if (data == null) return;

        if(TextUtils.isEmpty(data.getHeadImg())){
            holder.iv_head.setImageResource(R.mipmap.default_head);
        }else{
            GlideUtil.loadCircleImage(mContext,data.getHeadImg(),holder.iv_head);
        }

        holder.tv_post.setText(data.getRank());

        holder.tv_people.setText(data.getLabor() + "");
        holder.tv_team.setText(data.getTeamLabor() + "");
        holder.tv_name.setText(data.getMobile());

        if(data.getRealName() == 0){
            holder.tv_realName.setText("审核不通过");
            holder.tv_realName.setBackgroundResource(R.color.color_FF4751);
        }else if(data.getRealName() == 1){
            holder.tv_realName.setText("已实名");
            holder.tv_realName.setBackgroundResource(R.color.color_61B53F);
        }else if(data.getRealName() == 2){
            holder.tv_realName.setText("审核中");
            holder.tv_realName.setBackgroundResource(R.color.color_61B53F);
        }else{
            holder.tv_realName.setText("未实名");
            holder.tv_realName.setBackgroundResource(R.color.color_FF4751);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<TeamInviteListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<TeamInviteListBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_head;
        TextView tv_post,tv_people,tv_team,tv_realName,tv_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_head = itemView.findViewById(R.id.iv_head);
            tv_post = itemView.findViewById(R.id.tv_post);
            tv_people = itemView.findViewById(R.id.tv_people);
            tv_team = itemView.findViewById(R.id.tv_team);
            tv_realName = itemView.findViewById(R.id.tv_realName);
            tv_name = itemView.findViewById(R.id.tv_name);

        }
    }
}
