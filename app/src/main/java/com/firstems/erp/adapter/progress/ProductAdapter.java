package com.firstems.erp.adapter.progress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.firstems.erp.R;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProgressProductDetailItem> progressProductDetailItems;
    public interface ProductItemClickListener{
        void ontemClick(ProgressProductDetailItem item);
    }

    public ProductAdapter(List<ProgressProductDetailItem> progressProductDetailItems) {
        this.progressProductDetailItems = progressProductDetailItems;
    }
    private ProductItemClickListener productItemClickListener;

    public void setProductItemClickListener(ProductItemClickListener productItemClickListener) {
        this.productItemClickListener = productItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProgressProductDetailItem item = progressProductDetailItems.get(position);
        holder.txtProductName.setText(item.getPrdcname());
        holder.txtProductQuatity.setText(String.valueOf((long) item.getPrdcqtty()));
        holder.txtProductQuatityPass.setText(String.valueOf(0));
        holder.txtProductQuatityFail.setText(String.valueOf(0));
        holder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productItemClickListener.ontemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return progressProductDetailItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtProductName, txtProductQuatity, txtProductQuatityPass,txtProductQuatityFail,txtNote;
        MaterialRippleLayout materialRippleLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName =  itemView.findViewById(R.id.txtProductName);
            txtProductQuatity = itemView.findViewById(R.id.txtProductQuatity);
            txtProductQuatityPass = itemView.findViewById(R.id.txtProductQuatityPass);
            txtProductQuatityFail = itemView.findViewById(R.id.txtProductQuatityFail);
            txtNote = itemView.findViewById(R.id.txtNote);
            materialRippleLayout = itemView.findViewById(R.id.materialRippleLayout);
        }
    }
}
