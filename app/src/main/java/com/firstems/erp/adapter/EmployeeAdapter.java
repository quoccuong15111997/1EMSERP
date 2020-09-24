package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.callback.ItemCheckCallback;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/4/2020.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{
    private List<Employee> list;
    private ItemCheckCallback itemCheckCallback;
    private boolean isSingelChoise;

    public EmployeeAdapter(List<Employee> list, ItemCheckCallback itemCheckCallback, boolean isSingelChoise) {
        this.list = list;
        this.itemCheckCallback = itemCheckCallback;
        this.isSingelChoise = isSingelChoise;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=  LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_check,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee item = list.get(position);
        holder.txtContent.setText(item.getItemName());
        holder.chk.setChecked(item.isCheck());


        holder.chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSingelChoise){
                    System.out.println("Check box "+holder.chk.isChecked());
                    System.out.println("Position "+position);
                    clearCheck();
                    list.get(position).setCheck(holder.chk.isChecked());
                    notifyDataSetChanged();
                    itemCheckCallback.onCheckChange(item.getItemCode(),holder.chk.isChecked());
                }
                else {
                    itemCheckCallback.onCheckChange(item.getItemCode(),holder.chk.isChecked());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSingelChoise){
                    holder.chk.setChecked(!holder.chk.isChecked());
                    System.out.println("Check box "+holder.chk.isChecked());
                    System.out.println("Position "+position);
                    clearCheck();
                    list.get(position).setCheck(holder.chk.isChecked());
                    notifyDataSetChanged();
                    itemCheckCallback.onCheckChange(item.getItemCode(),holder.chk.isChecked());
                }
                else {
                    itemCheckCallback.onCheckChange(item.getItemCode(),holder.chk.isChecked());
                }
            }
        });
        holder.txtContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSingelChoise){
                    holder.chk.setChecked(!holder.chk.isChecked());
                    System.out.println("Check box "+holder.chk.isChecked());
                    System.out.println("Position "+position);
                    clearCheck();
                    list.get(position).setCheck(holder.chk.isChecked());
                    notifyDataSetChanged();
                    itemCheckCallback.onCheckChange(item.getItemCode(),holder.chk.isChecked());
                }
                else {
                    itemCheckCallback.onCheckChange(item.getItemCode(),holder.chk.isChecked());
                }
            }
        });
    }
    private void clearCheck(){
        for (Employee employee : list){
            employee.setCheck(false);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtContent;
        CheckBox chk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContent=itemView.findViewById(R.id.txtContent);
            chk=itemView.findViewById(R.id.chk);
            txtContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Adapter Position "+ getAdapterPosition());
                }
            });
        }
    }
}
