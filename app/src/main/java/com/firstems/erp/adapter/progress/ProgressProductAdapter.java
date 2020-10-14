package com.firstems.erp.adapter.progress;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firstems.erp.R;
import com.firstems.erp.adapter.diff.progress.ProgressProductDiffUtilCallback;
import com.firstems.erp.adapter.diff.signature.SignatureDiffUtilCallBack;
import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.callback.barcode.BarCodeGenerateCallback;
import com.firstems.erp.common.Util;
import com.firstems.erp.helper.barcode.BarCodeHelper;
import com.tuyenmonkey.mkloader.MKLoader;

import org.mozilla.javascript.typedarrays.NativeUint8Array;

import java.util.List;

public class ProgressProductAdapter extends RecyclerView.Adapter<ProgressProductAdapter.ViewHolder> {
    private List<ProgressItem> progressItemList;

    public interface ProgressProductOnlickListener {
        void onItemClick(ProgressItem progressItem);
        void onBarCodeClick(ProgressItem progressItem);
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
        holder.txtDate.setText(Util.formatDateCustomChar(progressItem.getCmmddate(), "-"));
        holder.txtProgressCode.setText(progressItem.getPcpdcode());
        holder.imgSeen.setVisibility(progressItem.isCheck() ? View.VISIBLE : View.INVISIBLE);
        holder.txtProgressName.setText(progressItem.getPcpdname());
        holder.itemView.post(new Runnable() {
            @Override
            public void run() {
                BarCodeHelper.getInstance().generate(progressItem.getCmmdcode(), new BarCodeGenerateCallback() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.loadding.setVisibility(View.GONE);
                                holder.imgBarCode.setImageBitmap(bitmap);
                                holder.imgBarCode.setVisibility(View.VISIBLE);
                            }
                        }, 700);

                    }

                    @Override
                    public void onFail(String mess) {
                        System.out.println(mess);
                        holder.loadding.setVisibility(View.GONE);
                        holder.imgBarCode.setImageResource(R.drawable.qr_icon);
                        holder.imgBarCode.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        holder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressProductOnlickListener.onItemClick(progressItem);
                holder.imgSeen.setVisibility(View.VISIBLE);
                //progressItemList.get(position).setCheck(true);
            }
        });
        holder.imgBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressProductOnlickListener.onBarCodeClick(progressItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return progressItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMainCode, txtDate, txtProgressCode, txtProgressName;
        ImageView imgBarCode;
        MaterialRippleLayout materialRippleLayout;
        ImageView imgSeen;
        MKLoader loadding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMainCode = itemView.findViewById(R.id.txtMainCode);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtProgressCode = itemView.findViewById(R.id.txtProgressCode);
            txtProgressName = itemView.findViewById(R.id.txtProgressName);
            imgBarCode = itemView.findViewById(R.id.imgBarcode);
            materialRippleLayout = itemView.findViewById(R.id.materialRippleLayout);
            imgSeen = itemView.findViewById(R.id.imgSeen);
            loadding = itemView.findViewById(R.id.loadding);
        }
    }

    public void setProgressItemList(List<ProgressItem> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ProgressProductDiffUtilCallback(newList,progressItemList));
        diffResult.dispatchUpdatesTo(this);
        progressItemList.clear();
        this.progressItemList.addAll(newList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("CmmdCode") || key.equals("CmmdDate") || key.equals("PcpdCode")){
                    ProgressItem progressItem = progressItemList.get(position);
                    holder.txtMainCode.setText(progressItem.getCmmdcode());
                    holder.txtDate.setText(Util.formatDateCustomChar(progressItem.getCmmddate(), "-"));
                    holder.txtProgressCode.setText(progressItem.getPcpdcode());
                    holder.txtProgressName.setText(progressItem.getPcpdname());
                    holder.imgSeen.setVisibility(progressItem.isCheck() ? View.VISIBLE : View.INVISIBLE);
                    holder.itemView.post(new Runnable() {
                        @Override
                        public void run() {
                            BarCodeHelper.getInstance().generate(progressItem.getCmmdcode(), new BarCodeGenerateCallback() {
                                @Override
                                public void onSuccess(Bitmap bitmap) {
                                    holder.imgBarCode.setImageBitmap(bitmap);
                                }

                                @Override
                                public void onFail(String mess) {
                                    System.out.println(mess);
                                    holder.imgBarCode.setImageResource(R.drawable.qr_icon);
                                }
                            });
                        }
                    });
                }
            }
        }
    }
}
