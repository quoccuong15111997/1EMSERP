package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.model.approved.Approved;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/5/2020.
 */
public class ApprovedItemAdapter extends RecyclerView.Adapter<ApprovedItemAdapter.ViewHolder> {
    public interface ApprovedItemClick{
        void onEditClick(Approved approved, int position);
        void onDeleteClick(Approved approved, int position);
    }
    private List<Approved> list;
    private ApprovedItemClick itemClick;
    private int role = 0;
    SimpleDateFormat simpleDateFormatDisplay = new SimpleDateFormat("dd/MM/yyyy");

    public void setRole(int role) {
        this.role = role;
    }

    public ApprovedItemAdapter(List<Approved> list, ApprovedItemClick itemClick) {
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_switch_shift,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Approved item =list.get(position);
        holder.txtBeginDate.setText(simpleDateFormatDisplay.format(item.getDateBegin()));
        holder.txtendDate.setText(simpleDateFormatDisplay.format(item.getDateEnd()));


        if (!item.isMorning() && item.getContentMornig()==null){
            holder.llSang.setVisibility(View.GONE);
        }
        else{
            holder.llSang.setVisibility(View.VISIBLE);
            holder.txtContentSang.setText(item.getContentMornig().getItemName());
        }

        if (!item.isAfternoon()&& item.getContentAfternoon()==null){
            holder.llChieu.setVisibility(View.GONE);
        }
        else{
            holder.llChieu.setVisibility(View.VISIBLE);
            holder.txtContentChieu.setText(item.getContentAfternoon().getItemName());
        }

        if (!item.isEverning() && item.getContentEverning()==null){
            holder.llToi.setVisibility(View.GONE);
        }
        else{
            holder.llToi.setVisibility(View.VISIBLE);
            holder.txtContentToi.setText(item.getContentEverning().getItemName());
        }
        long time=  item.getNumberDay();
        holder.txtSum.setText(String.valueOf(time));
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onEditClick(item,position);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onDeleteClick(item,position);
            }
        });

        holder.imgDelete.setVisibility(role == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.imgEdit.setVisibility(role == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.llSang.setVisibility(!item.isMorning() ? View.GONE : View.VISIBLE);
        holder.llChieu.setVisibility(!item.isAfternoon() ? View.GONE : View.VISIBLE);
        holder.llToi.setVisibility(!item.isEverning() ? View.GONE : View.VISIBLE);
        
        System.out.println(item.getContentMornig());
        System.out.println(item.getContentAfternoon());
        System.out.println(item.getContentAfternoon());
    
        System.out.println(item.isMorning());
        System.out.println(item.isAfternoon());
        System.out.println(item.isEverning());
        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtBeginDate, txtendDate, txtContentSang, txtContentChieu, txtContentToi, txtSum;
        ImageView imgDelete, imgEdit;
        LinearLayout llSang, llChieu, llToi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBeginDate=itemView.findViewById(R.id.txtBeginDate);
            txtendDate =itemView.findViewById(R.id.txtEndDate);
            txtContentSang=itemView.findViewById(R.id.txtContentSang);
            txtContentChieu=itemView.findViewById(R.id.txtContentChieu);
            txtContentToi=itemView.findViewById(R.id.txtContentToi);
            txtSum = itemView.findViewById(R.id.txtSum);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgEdit=itemView.findViewById(R.id.imgEdit);

            llSang=itemView.findViewById(R.id.llSang);
            llChieu=itemView.findViewById(R.id.llChieu);
            llToi=itemView.findViewById(R.id.llToi);
        }
    }
}
