package com.firstems.erp.api.model.response.document;

import androidx.databinding.BaseObservable;

import com.firstems.erp.api.model.response.reviewprocess.documentfile.DocumentFile;
import com.firstems.erp.common.Util;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DocumentItemApiResponse extends BaseObservable implements Serializable {
    @SerializedName("COMPCODE")
    public String cOMPCODE;
    @SerializedName("LCTNCODE")
    public String lCTNCODE;
    @SerializedName("MAINNUMB")
    public String mAINNUMB;
    @SerializedName("DCTPCODE")
    public String dCTPCODE;
    @SerializedName("CNTNBRIF")
    public String cNTNBRIF;
    @SerializedName("CNTNDCMN")
    public String cNTNDCMN;
    @SerializedName("BEG_DATE")
    public String bEGDATE;
    @SerializedName("END_DATE")
    public String eNDDATE;
    @SerializedName("APRVDATE")
    public String aPRVDATE;
    @SerializedName("USEDSTTE")
    public int uSEDSTTE;
    @SerializedName("PBLSCODE")
    public String pBLSCODE;
    @SerializedName("DCTPNAME")
    public String dCTPNAME;
    @SerializedName("PBLSNAME")
    public String pBLSNAME;
    @SerializedName("LCTNNAME")
    public String lCTNNAME;
    @SerializedName("DDDD")
    public String dDDD;
    @SerializedName("ACCERGHT")
    public int aCCERGHT;
    @SerializedName("STTESIGN")
    public int sTTESIGN;
    @SerializedName("STTENAME")
    public String sTTENAME;
    @SerializedName("KKKK0000")
    public String kKKK0000;
    @SerializedName("DCMNFILE")
    public List<DocumentFile> documentFiles;
    
    public DocumentItemApiResponse() {
    }
    
    public String getcOMPCODE() {
        return cOMPCODE;
    }
    
    public void setcOMPCODE(String cOMPCODE) {
        this.cOMPCODE = cOMPCODE;
    }
    
    public String getlCTNCODE() {
        return lCTNCODE;
    }
    
    public void setlCTNCODE(String lCTNCODE) {
        this.lCTNCODE = lCTNCODE;
    }
    
    public String getmAINNUMB() {
        return mAINNUMB;
    }
    
    public void setmAINNUMB(String mAINNUMB) {
        this.mAINNUMB = mAINNUMB;
    }
    
    public String getdCTPCODE() {
        return dCTPCODE;
    }
    
    public void setdCTPCODE(String dCTPCODE) {
        this.dCTPCODE = dCTPCODE;
    }
    
    public String getcNTNBRIF() {
        return cNTNBRIF;
    }
    
    public void setcNTNBRIF(String cNTNBRIF) {
        this.cNTNBRIF = cNTNBRIF;
    }
    
    public String getcNTNDCMN() {
        return cNTNDCMN;
    }
    
    public void setcNTNDCMN(String cNTNDCMN) {
        this.cNTNDCMN = cNTNDCMN;
    }
    
    public String getbEGDATE() {
        return bEGDATE;
    }
    
    public void setbEGDATE(String bEGDATE) {
        this.bEGDATE = bEGDATE;
    }
    
    public String geteNDDATE() {
        return eNDDATE;
    }
    
    public void seteNDDATE(String eNDDATE) {
        this.eNDDATE = eNDDATE;
    }
    
    public String getaPRVDATE() {
        return aPRVDATE;
    }
    
    public void setaPRVDATE(String aPRVDATE) {
        this.aPRVDATE = aPRVDATE;
    }
    
    public int getuSEDSTTE() {
        return uSEDSTTE;
    }
    
    public void setuSEDSTTE(int uSEDSTTE) {
        this.uSEDSTTE = uSEDSTTE;
    }
    
    public String getpBLSCODE() {
        return pBLSCODE;
    }
    
    public void setpBLSCODE(String pBLSCODE) {
        this.pBLSCODE = pBLSCODE;
    }
    
    public String getdCTPNAME() {
        return dCTPNAME;
    }
    
    public void setdCTPNAME(String dCTPNAME) {
        this.dCTPNAME = dCTPNAME;
    }
    
    public String getpBLSNAME() {
        return pBLSNAME;
    }
    
    public void setpBLSNAME(String pBLSNAME) {
        this.pBLSNAME = pBLSNAME;
    }
    
    public List<DocumentFile> getDocumentFiles() {
        return documentFiles;
    }
    
    public void setDocumentFiles(List<DocumentFile> documentFiles) {
        this.documentFiles = documentFiles;
    }
    
    public String getlCTNNAME() {
        return lCTNNAME;
    }
    
    public void setlCTNNAME(String lCTNNAME) {
        this.lCTNNAME = lCTNNAME;
    }
    
    public String getdDDD() {
        return dDDD;
    }
    
    public void setdDDD(String dDDD) {
        this.dDDD = dDDD;
    }
    
    public int getaCCERGHT() {
        return aCCERGHT;
    }
    
    public void setaCCERGHT(int aCCERGHT) {
        this.aCCERGHT = aCCERGHT;
    }
    
    public int getsTTESIGN() {
        return sTTESIGN;
    }
    
    public void setsTTESIGN(int sTTESIGN) {
        this.sTTESIGN = sTTESIGN;
    }
    
    public String getsTTENAME() {
        return sTTENAME;
    }
    
    public void setsTTENAME(String sTTENAME) {
        this.sTTENAME = sTTENAME;
    }
    
    public String getkKKK0000() {
        return kKKK0000;
    }
    
    public void setkKKK0000(String kKKK0000) {
        this.kKKK0000 = kKKK0000;
    }
    
    public String getBegDateFormat() {
        return (bEGDATE != null && !bEGDATE.equals("") ? Util.formatDate(bEGDATE) : "");
    }
    public Date  getBegDateTypeDate(){
        return Util.convertStringToDate(Util.formatDateSystem(bEGDATE),"yyyy-MM-dd");
    }
}
