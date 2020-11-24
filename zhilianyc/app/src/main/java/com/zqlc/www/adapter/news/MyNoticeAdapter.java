package com.zqlc.www.adapter.news;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zqlc.www.R;
import com.zqlc.www.bean.news.AnnouncementBean;
import com.zqlc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyNoticeAdapter extends RecyclerView.Adapter<MyNoticeAdapter.ViewHolder> {

    Context mContext;
    List<AnnouncementBean> datas;

    public MyNoticeAdapter(Context context, List<AnnouncementBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyNoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_my_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNoticeAdapter.ViewHolder holder, int position) {
        if (holder == null) return;
        final AnnouncementBean data = datas.get(position);
        if (data == null) return;

        if(!TextUtils.isEmpty(data.getImg())){
            GlideUtil.loadImage(mContext,data.getImg(),holder.iv_img);
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<AnnouncementBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<AnnouncementBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }


    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
