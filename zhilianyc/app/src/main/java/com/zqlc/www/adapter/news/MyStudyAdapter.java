package com.zqlc.www.adapter.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.news.StudyCentreBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyStudyAdapter extends RecyclerView.Adapter<MyStudyAdapter.ViewHolder> {

    Context mContext;
    List<StudyCentreBean> datas;

    public MyStudyAdapter(Context context, List<StudyCentreBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_my_study, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final StudyCentreBean data = datas.get(position);
        if (data == null) return;

        holder.tv_title.setText(data.getTitle());

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<StudyCentreBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<StudyCentreBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
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
