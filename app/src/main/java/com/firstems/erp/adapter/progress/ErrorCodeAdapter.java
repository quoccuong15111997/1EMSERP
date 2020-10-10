package com.firstems.erp.adapter.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firstems.erp.R;
import com.firstems.erp.model.product.ErrorCodeModel;

import java.util.List;

public class ErrorCodeAdapter extends BaseAdapter {
    private List<ErrorCodeModel> errorCodeModels;
    private Context context;
    public interface OnSpinnerMultiCheckListener{
        void onIteCheck(int position);
    }
    private OnSpinnerMultiCheckListener onSpinnerMultiCheckListener;

    public void setOnSpinnerMultiCheckListener(OnSpinnerMultiCheckListener onSpinnerMultiCheckListener) {
        this.onSpinnerMultiCheckListener = onSpinnerMultiCheckListener;
    }

    public ErrorCodeAdapter(List<ErrorCodeModel> errorCodeModels, Context context) {
        this.errorCodeModels = errorCodeModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return errorCodeModels.size();
    }

    @Override
    public Object getItem(int position) {
        return errorCodeModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_spinner_multiple_check, parent, false);
        TextView txtName = view.findViewById(R.id.text);
        CheckBox checkBox = view.findViewById(R.id.checkbox);
        txtName.setText(errorCodeModels.get(position).getErrorName());
        checkBox.setChecked(errorCodeModels.get(position).isCheck());
        if (position == 0){
            checkBox.setVisibility(View.INVISIBLE);
            txtName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSpinnerMultiCheckListener.onIteCheck(position);
            }
        });
        return view;
    }
}
