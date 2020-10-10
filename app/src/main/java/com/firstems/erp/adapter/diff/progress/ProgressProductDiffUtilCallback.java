package com.firstems.erp.adapter.diff.progress;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;

import java.util.List;

public class ProgressProductDiffUtilCallback extends DiffUtil.Callback {
    private List<ProgressItem> newList;
    private List<ProgressItem> oldList;

    public ProgressProductDiffUtilCallback(List<ProgressItem> newList, List<ProgressItem> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getCmmdcode().equals(oldList.get(oldItemPosition).getCmmdcode());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        ProgressItem newModel = newList.get(newItemPosition);
        ProgressItem oldModel = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if (!newModel.getCmmdcode().equals(oldModel.getCmmdcode())) {
            diff.putString("CmmdCode", newModel.getCmmdcode());
        }
        if (!newModel.getCmmddate().equals(oldModel.getCmmddate())) {
            diff.putString("CmmdDate", newModel.getCmmddate());
        }
        if (!newModel.getPcpdcode().equals(oldModel.getPcpdcode())) {
            diff.putString("PcpdCode", newModel.getPcpdcode());
        }


        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
