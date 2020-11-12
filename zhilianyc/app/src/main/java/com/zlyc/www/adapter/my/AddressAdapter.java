package com.zlyc.www.adapter.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.my.AddressBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context mContext;
    List<AddressBean> datas;

    public AddressAdapter(Context context, List<AddressBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (holder == null) return;
        final AddressBean data = datas.get(position);
        if (data == null) return;

        holder.tv_head.setText(data.getNameHead());
        holder.tv_name.setText(data.getName());
        holder.tv_address.setText(data.getAddressDts());

        if (mOnItemClickListener != null) {
            holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<AddressBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_head,tv_name,tv_address,btn_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_head = itemView.findViewById(R.id.tv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            btn_edit = itemView.findViewById(R.id.btn_edit);
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
