package com.firstems.erp.model;

public class DocumentSortBit {
    public DocumentSortBit() {
    }
    private boolean is_btnAZ;
    private boolean is_btnZA;
    private boolean is_btnNewToOld;
    private boolean is_btnOldToNew;
    
    public boolean isIs_btnAZ() {
        return is_btnAZ;
    }
    
    public void setIs_btnAZ(boolean is_btnAZ) {
        this.is_btnAZ = is_btnAZ;
    }
    
    public boolean isIs_btnZA() {
        return is_btnZA;
    }
    
    public void setIs_btnZA(boolean is_btnZA) {
        this.is_btnZA = is_btnZA;
    }
    
    public boolean isIs_btnNewToOld() {
        return is_btnNewToOld;
    }
    
    public void setIs_btnNewToOld(boolean is_btnNewToOld) {
        this.is_btnNewToOld = is_btnNewToOld;
    }
    
    public boolean isIs_btnOldToNew() {
        return is_btnOldToNew;
    }
    
    public void setIs_btnOldToNew(boolean is_btnOldToNew) {
        this.is_btnOldToNew = is_btnOldToNew;
    }
    public void resetValue(){
        System.out.println("AZ :"+ is_btnAZ);
        System.out.println("ZA :" +isIs_btnZA());
        System.out.println("New :"+is_btnNewToOld);
        System.out.println("Old :"+isIs_btnOldToNew());
       /* setIs_btnNewToOld(false);
        setIs_btnOldToNew(false);
        setIs_btnZA(false);
        setIs_btnAZ(false);*/
    }
}
