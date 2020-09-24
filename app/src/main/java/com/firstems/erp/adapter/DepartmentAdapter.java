package com.firstems.erp.adapter;

import android.companion.WifiDeviceFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.department.DepartmentItemApiResponse;
import com.firstems.erp.enums.TypeSelect;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/27/2020.
 */
public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {
    public interface OnDepartmentClickListener{
        void onItemLick(DepartmentItemApiResponse item);
    }
    private OnDepartmentClickListener onDepartmentClickListener;

    public DepartmentAdapter(List<DepartmentItemApiResponse> list) {
        this.list = list;
    }

    public void setOnDepartmentClickListener(OnDepartmentClickListener onDepartmentClickListener) {
        this.onDepartmentClickListener = onDepartmentClickListener;
    }

    private List<DepartmentItemApiResponse> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_check,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DepartmentItemApiResponse item = list.get(position);
        holder.txtContent.setText(item.getItemName());
        if (item.getCheck()==0)
            holder.chk.setChecked(false);
        if (item.getCheck()==1)
            holder.chk.setChecked(true);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.chk.setChecked(!holder.chk.isChecked());
                onDepartmentClickListener.onItemLick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtContent;
        CheckBox chk;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContent=itemView.findViewById(R.id.txtContent);
            chk=itemView.findViewById(R.id.chk);
            layout = itemView.findViewById(R.id.layoutMain);
        }
    }
}
