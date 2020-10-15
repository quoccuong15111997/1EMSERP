package com.firstems.erp.adapter.diff.product;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.firstems.erp.api.model.response.product.ProgressItem;
import com.firstems.erp.api.model.response.product.ProgressProductDetailItem;

import java.util.List;

public class ProductDiffUtilCallback extends DiffUtil.Callback {
    private List<ProgressProductDetailItem> newList;
    private List<ProgressProductDetailItem> oldList;

    public ProductDiffUtilCallback(List<ProgressProductDetailItem> newList, List<ProgressProductDetailItem> oldList) {
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
        return newList.get(newItemPosition).getPrdccode().equals(oldList.get(oldItemPosition).getPrdccode());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (newList.size()!=oldList.size()){
            return false;
        }
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        ProgressProductDetailItem newModel = newList.get(newItemPosition);
        ProgressProductDetailItem oldModel = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if (newModel.getQuatityBad()!=oldModel.getQuatityBad()) {
            diff.putString("QuatityBad", newModel.getQuatityBad()+"");
        }
        if (newModel.getQuatityGood() != oldModel.getQuatityGood()) {
            diff.putString("QuatityGood", newModel.getQuatityGood()+"");
        }

        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
