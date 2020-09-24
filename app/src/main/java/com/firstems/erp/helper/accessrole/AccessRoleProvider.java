package com.firstems.erp.helper.accessrole;

/**
 * Created by Nguyen Quoc Cuong on 8/20/2020.
 */
public class AccessRoleProvider {
    private int value_Read = 0;
    private int value_Add = 1;
    private int value_Delete = 2;
    private int value_Edit = 4;
    private static AccessRoleProvider instance;
    private AccessRoleProvider(){

    }
    public static AccessRoleProvider getInstance(){
        if (instance ==null){
            instance = new AccessRoleProvider();
        }
        return instance;
    }
    public AccessRole getAccessRole(int accessRight){
        AccessRole accessRole = new AccessRole();
        accessRole.setRead((value_Read & accessRight) >  0);
        accessRole.setAdd((value_Add & accessRight) >  0);
        accessRole.setDelete((value_Delete & accessRight) >  0);
        accessRole.setEdit((value_Edit & accessRight) >  0);
        System.out.println("Xem :"+accessRole.isRead());
        System.out.println("Thêm :"+accessRole.isAdd());
        System.out.println("Xóa :"+accessRole.isDelete());
        System.out.println("Sửa :"+accessRole.isEdit());
        return accessRole;
    }
}
