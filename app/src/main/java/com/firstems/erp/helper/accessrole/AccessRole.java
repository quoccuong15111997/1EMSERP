package com.firstems.erp.helper.accessrole;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/20/2020.
 */
public class AccessRole implements Serializable {
    private boolean isRead;
    private boolean isAdd;
    private boolean isDelete;
    private boolean isEdit;

    public AccessRole() {
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

}
