package com.firstems.erp.model;

import java.io.Serializable;

public class ImageModel implements Serializable {
    private String imgUrl;
    private String imgPath;
    // type = 1 => Image Url
    // type = 2 => Image Path Local
    private int type;
    private String fileCode;
    
    public ImageModel() {
    }
    
    public String getFileCode() {
        return fileCode;
    }
    
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public String getImgPath() {
        return imgPath;
    }
    
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
}
