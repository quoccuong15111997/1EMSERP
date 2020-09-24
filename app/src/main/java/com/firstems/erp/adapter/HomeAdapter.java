package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.model.DataHome;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHoder> {
    private List<DataHome> list;
    public interface HomeItemOnClickListener{
        void onItemClick(int position);
    }
    private HomeItemOnClickListener homeItemOnClickListener;
    
    public void setHomeItemOnClickListener(HomeItemOnClickListener homeItemOnClickListener) {
        this.homeItemOnClickListener = homeItemOnClickListener;
    }
    
    public HomeAdapter(List<DataHome> list) {
        this.list = list;
    }
    
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_home_adapter, parent, false);
        return new ViewHoder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        DataHome dataHome = list.get(position);
        holder.tvTitle.setText(dataHome.getTitle());
        holder.tvDesc1.setText(dataHome.getDescription1());
        holder.tvDesc2.setText(dataHome.getDescription2());
        holder.ivImage.setImageResource(dataHome.getImage());
        
        holder.cvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemOnClickListener.onItemClick(position);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }
    
    class ViewHoder extends RecyclerView.ViewHolder{
        CardView cvParent;
        TextView tvTitle;
        TextView tvDesc1;
        TextView tvDesc2;
        ImageView ivImage;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc1 = itemView.findViewById(R.id.tvDesc1);
            tvDesc2 = itemView.findViewById(R.id.tvDesc2);
            ivImage = itemView.findViewById(R.id.ivImage);
            cvParent = itemView.findViewById(R.id.cvParent);
        }
    }
}
