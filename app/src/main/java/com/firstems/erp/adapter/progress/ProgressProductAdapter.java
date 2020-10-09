package com.firstems.erp.adapter.progress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.firstems.erp.R;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.common.Util;

import java.util.List;

public class ProgressProductAdapter extends RecyclerView.Adapter<ProgressProductAdapter.ViewHolder> {
    private List<ProgressItem> progressItemList;
    public interface ProgressProductOnlickListener{
        void onItemClick(ProgressItem progressItem);
    }

    public ProgressProductAdapter(List<ProgressItem> progressItemList) {
        this.progressItemList = progressItemList;
    }
    private ProgressProductOnlickListener progressProductOnlickListener;

    public void setProgressProductOnlickListener(ProgressProductOnlickListener progressProductOnlickListener) {
        this.progressProductOnlickListener = progressProductOnlickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_progress, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProgressItem progressItem = progressItemList.get(position);
        holder.txtMainCode.setText(progressItem.getCmmdcode());
        holder.txtDate.setText(Util.formatDateCustomChar(progressItem.getCmmddate(),"-"));
        holder.txtProgressCode.setText(progressItem.getPcpdcode());
        holder.txtProgressName.setText(progressItem.getPcpdname());
        holder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressProductOnlickListener.onItemClick(progressItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return progressItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMainCode, txtDate, txtProgressCode, txtProgressName;
        ImageView imgBarCode;
        MaterialRippleLayout materialRippleLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMainCode = itemView.findViewById(R.id.txtMainCode);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtProgressCode = itemView.findViewById(R.id.txtProgressCode);
            txtProgressName =itemView.findViewById(R.id.txtProgressName);
            imgBarCode = itemView.findViewById(R.id.imgBarcode);
            materialRippleLayout = itemView.findViewById(R.id.materialRippleLayout);
        }
    }
}
