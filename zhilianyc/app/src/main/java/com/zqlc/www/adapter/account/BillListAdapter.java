package com.zqlc.www.adapter.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.account.MyBillListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder> {

    Context mContext;
    List<MyBillListBean> datas;

    public BillListAdapter(Context context, List<MyBillListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public BillListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_bill_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillListAdapter.ViewHolder holder, int position) {
        if (holder == null) return;
        final MyBillListBean data = datas.get(position);
        if (data == null) return;


        holder.tv_name.setText(data.getSource());
        holder.tv_time.setText(data.getTime());


        holder.tv_value.setText(data.getCountStr());
        if (data.getCount() > 0) {
            holder.tv_value.setTextColor(ContextCompat.getColor(mContext, R.color.color_368feb));
        } else if (data.getCount() < 0) {
            holder.tv_value.setTextColor(ContextCompat.getColor(mContext, R.color.color_FF4751));
        } else {
            holder.tv_value.setTextColor(ContextCompat.getColor(mContext, R.color.color_text));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MyBillListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_time, tv_value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_value = itemView.findViewById(R.id.tv_value);
        }
    }
}
