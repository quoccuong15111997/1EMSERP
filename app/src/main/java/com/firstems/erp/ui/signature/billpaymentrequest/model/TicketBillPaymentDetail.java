package com.firstems.erp.ui.signature.billpaymentrequest.model;

import java.io.Serializable;
import java.util.Date;

public class TicketBillPaymentDetail implements Serializable {
    private String content;
    private double numberPrice;
    private String billCode;
    private Date dateBill;
    
    public TicketBillPaymentDetail() {
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public double getNumberPrice() {
        return numberPrice;
    }
    
    public void setNumberPrice(double numberPrice) {
        this.numberPrice = numberPrice;
    }
    
    public String getBillCode() {
        return billCode;
    }
    
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    
    public Date getDateBill() {
        return dateBill;
    }
    
    public void setDateBill(Date dateBill) {
        this.dateBill = dateBill;
    }
}
