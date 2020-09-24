package com.firstems.erp.model;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/15/2020.
 */
public class ApproveModel {
    private String name;
    private int number;
    private List<ApproveDetailModel> detailModels;

    public ApproveModel() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApproveDetailModel> getDetailModels() {
        return detailModels;
    }

    public void setDetailModels(List<ApproveDetailModel> detailModels) {
        this.detailModels = detailModels;
    }
}
