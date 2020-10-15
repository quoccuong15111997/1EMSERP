package com.firstems.erp.adapter.progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.firstems.erp.R;
import com.firstems.erp.adapter.diff.product.ProductDiffUtilCallback;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductTableAdapter extends RecyclerView.Adapter<ProductTableAdapter.ViewHolder> {
    private List<ProgressProductDetailItem> progressProductDetailItems;

    public interface ProductItemClickListener {
        void ontemClick(ProgressProductDetailItem item, int position);
    }

    public List<ProgressProductDetailItem> getData() {
        return progressProductDetailItems;
    }

    public ProductTableAdapter(List<ProgressProductDetailItem> progressProductDetailItems) {
        this.progressProductDetailItems = progressProductDetailItems;
    }

    private ProductItemClickListener productItemClickListener;

    public void setProductItemClickListener(ProductItemClickListener productItemClickListener) {
        this.productItemClickListener = productItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProgressProductDetailItem item = progressProductDetailItems.get(position);
        holder.txtProductName.setText(item.getPrdcname());
        holder.txtProductQuatity.setText(String.valueOf((long) item.getPrdcqtty()));
        holder.txtProductQuatityPass.setText(String.valueOf(item.getQuatityGood()));
        holder.txtProductQuatityFail.setText(String.valueOf(item.getQuatityBad()));
        if (item.isEdit()){
            holder.imgEdit.setVisibility(View.GONE);
            holder.imgDone.setVisibility(View.VISIBLE);
        }
        else {
            holder.imgEdit.setVisibility(View.VISIBLE);
            holder.imgDone.setVisibility(View.GONE);
        }
        holder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productItemClickListener.ontemClick(item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return progressProductDetailItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtProductQuatity, txtProductQuatityPass, txtProductQuatityFail;
        MaterialRippleLayout materialRippleLayout;
        ImageView imgDone, imgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductQuatity = itemView.findViewById(R.id.txtProductQuatity);
            txtProductQuatityPass = itemView.findViewById(R.id.txtProductQuatityPass);
            txtProductQuatityFail = itemView.findViewById(R.id.txtProductQuatityFail);
            materialRippleLayout = itemView.findViewById(R.id.materialRippleLayout);
            imgDone = itemView.findViewById(R.id.imgDone);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }

    public void setData(List<ProgressProductDetailItem> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ProductDiffUtilCallback(newList, progressProductDetailItems));
        diffResult.dispatchUpdatesTo(this);
        progressProductDetailItems.clear();
        this.progressProductDetailItems.addAll(newList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("PrdCode") || key.equals("QuatityBad") || key.equals("QuatityGood")){
                    ProgressProductDetailItem item = progressProductDetailItems.get(position);
                    holder.txtProductName.setText(item.getPrdcname());
                    holder.txtProductQuatity.setText(String.valueOf((long) item.getPrdcqtty()));
                    holder.txtProductQuatityPass.setText(String.valueOf(item.getQuatityGood()));
                    holder.txtProductQuatityFail.setText(String.valueOf(item.getQuatityBad()));
                }
            }
        }
    }
}
