package com.firstems.erp.model;

public class QueryModel {
    private String key;
    private String value;
    
    public QueryModel() {
    }
    
    public QueryModel(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return key +" LIKE N'%"+value+"%'";
    }
}
