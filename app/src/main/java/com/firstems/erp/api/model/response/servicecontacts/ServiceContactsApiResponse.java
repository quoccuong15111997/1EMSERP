package com.firstems.erp.api.model.response.servicecontacts;import com.firstems.erp.api.model.response.ApiResponse;import com.google.gson.annotations.SerializedName;import java.io.Serializable;import java.util.List;/** * Created by Nguyen Quoc Cuong on 9/1/2020. */public class ServiceContactsApiResponse extends ApiResponse implements Serializable {    @SerializedName("RETNDATA")    private List<ServiceContactsItem> serviceContactsItems;    public ServiceContactsApiResponse() {    }    public List<ServiceContactsItem> getServiceContactsItems() {        return serviceContactsItems;    }    public void setServiceContactsItems(List<ServiceContactsItem> serviceContactsItems) {        this.serviceContactsItems = serviceContactsItems;    }}