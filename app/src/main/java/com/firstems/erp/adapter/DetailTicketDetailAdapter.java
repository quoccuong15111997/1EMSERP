package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.ui.signature.billpaymentrequest.model.TicketBillPaymentDetail;

import java.text.DecimalFormat;
import java.util.List;

public class DetailTicketDetailAdapter extends RecyclerView.Adapter<DetailTicketDetailAdapter.ViewHolder> {
    public interface TicketDetailClickListener{
        void onEditClick(TicketBillPaymentDetail detail, int position);
        void onDeleteClick(TicketBillPaymentDetail detail, int position);
    }
    private TicketDetailClickListener ticketDetailClickListener;
    private List<TicketBillPaymentDetail> paymentDetails;
    private DecimalFormat dfnd;
    private boolean isEditable = true;
    
    public void setEditable(boolean editable) {
        isEditable = editable;
    }
    
    public DetailTicketDetailAdapter(List<TicketBillPaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
        dfnd = new DecimalFormat("#,###");
    }
    
    public void setTicketDetailClickListener(TicketDetailClickListener ticketDetailClickListener) {
        this.ticketDetailClickListener = ticketDetailClickListener;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_ticket_bill_payment_detail, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TicketBillPaymentDetail item = paymentDetails.get(position);
        holder.txtCode.setText(String.valueOf(position+1));
        holder.txtContent.setText(item.getContent());
        holder.txtNumberPrice.setText(dfnd.format((int) item.getNumberPrice()));
        
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketDetailClickListener.onEditClick(item,position);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticketDetailClickListener.onDeleteClick(item,position);
            }
        });
        holder.imgDelete.setVisibility(isEditable ? View.VISIBLE : View.INVISIBLE);
        holder.imgEdit.setVisibility(isEditable ? View.VISIBLE : View.INVISIBLE);
    }
    
    @Override
    public int getItemCount() {
        return paymentDetails.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCode, txtContent, txtNumberPrice;
        ImageView imgEdit, imgDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.txtTitleCode);
            txtContent = itemView.findViewById(R.id.txtTitleLyDoChiTien);
            txtNumberPrice = itemView.findViewById(R.id.txtSoTien);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }
}
