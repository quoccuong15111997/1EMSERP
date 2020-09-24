package com.firstems.erp.model;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 7/15/2020.
 */
public class FileIncludeModel implements Serializable {
    private String fileName;
    private String link;
    private int icon;
    private String fileType;
    private String fileCode;
    private String filePath;
    private int loadType;
    // load type = 1 ? url : path
    
    public String getFileCode() {
        return fileCode;
    }
    
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public int getLoadType() {
        return loadType;
    }
    
    public void setLoadType(int loadType) {
        this.loadType = loadType;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public FileIncludeModel(String fileName) {
        this.fileName = fileName;
    }

    public FileIncludeModel() {
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
