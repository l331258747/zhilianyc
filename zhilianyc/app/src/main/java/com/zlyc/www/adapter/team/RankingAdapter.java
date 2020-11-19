package com.zlyc.www.adapter.team;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.team.RankingBean;
import com.zlyc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    Context mContext;
    List<RankingBean> datas;

    public RankingAdapter(Context context, List<RankingBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final RankingBean data = datas.get(position);
        if (data == null) return;

        holder.tv_num.setVisibility(View.GONE);
        holder.iv_img.setVisibility(View.GONE);

        holder.tv_people.setText(data.getInviteNum() + "");
        holder.tv_name.setText(data.getNickName());

        if(position < 3){
            holder.tv_num.setVisibility(View.GONE);
            holder.iv_img.setVisibility(View.VISIBLE);
            if(position == 0){
                holder.iv_img.setImageResource(R.mipmap.ranking_jin);
            }else if(position == 1){
                holder.iv_img.setImageResource(R.mipmap.ranking_yin);
            }else if(position == 2){
                holder.iv_img.setImageResource(R.mipmap.ranking_tong);
            }
        }else {
            holder.tv_num.setVisibility(View.VISIBLE);
            holder.iv_img.setVisibility(View.GONE);
            holder.tv_num.setText(position + "");
        }

        if(TextUtils.isEmpty(data.getHeadImg())){
            holder.iv_head.setImageResource(R.mipmap.default_head);
        }else{
            GlideUtil.loadCircleImage(mContext,data.getHeadImg(),holder.iv_head);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<RankingBean> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_people,tv_name,tv_num;
        ImageView iv_head,iv_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_people = itemView.findViewById(R.id.tv_people);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_num = itemView.findViewById(R.id.tv_num);
            iv_head = itemView.findViewById(R.id.iv_head);
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }
}
