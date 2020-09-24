package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.approved.info.ApproveInfoDetailApiResponse;
import com.firstems.erp.api.model.response.reviewprocess.ReviewProcessDetail;
import com.firstems.erp.common.Util;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/15/2020.
 */
public class ReviewProgressAdapter extends RecyclerView.Adapter<ReviewProgressAdapter.ViewHolder>  {
    private List<ReviewProcessDetail> list;

    public ReviewProgressAdapter(List<ReviewProcessDetail> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.layout_item_review_progress,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewProcessDetail progressApproveModel = list.get(position);
        holder.txtUsername.setText(progressApproveModel.getEmpName());
        holder.txtDate.setText(Util.convertTime(progressApproveModel.getPrcsDate()));
        holder.txtTrinhKy.setText(progressApproveModel.getPrcsName());
        holder.txtContent.setText(progressApproveModel.getPrcsNote());
        holder.txtNumber.setText(String.valueOf(position+1));
        if (progressApproveModel.getPrcsApprove()!=1){
            holder.txtStatus.setVisibility(View.GONE);
        }
        else {
            holder.txtStatus.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtUsername, txtDate,txtTrinhKy, txtContent, txtNumber, txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername=itemView.findViewById(R.id.txtUsername);
            txtNumber=itemView.findViewById(R.id.txtNumber);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtTrinhKy=itemView.findViewById(R.id.txtTrinhKy);
            txtContent=itemView.findViewById(R.id.txtContent);
            txtStatus=itemView.findViewById(R.id.txtStatus);
        }
    }
}
