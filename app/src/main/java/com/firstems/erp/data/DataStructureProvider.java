package com.firstems.erp.data;

import com.firstems.erp.api.model.request.ApprovedRequest;
import com.firstems.erp.api.model.request.SignatureRequest;

/**
 * Created by Nguyen Quoc Cuong on 8/14/2020.
 */
public class DataStructureProvider {
    public static String idRuncodeGenerate(String runcode, String parameter ,String company, String location){
        return company+"."+location+"."+runcode+"."+parameter;
    }
    public static SignatureRequest createSignatureRequest() {
        SignatureRequest signatureRequest= new SignatureRequest();
        signatureRequest.setBeginDate("2018-07-01");
        signatureRequest.setEndDate("2020-09-30");
        return signatureRequest;
    }
    public static ApprovedRequest createApprovedRequest(String dateBegin,String dateEnd ) {
        ApprovedRequest approvedRequest= new ApprovedRequest();
        approvedRequest.setEndDate(dateEnd);
        approvedRequest.setBeginDate(dateBegin);
        System.out.println(approvedRequest.convertToJson());
        return approvedRequest;
    }
}