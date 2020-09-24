package com.firstems.erp.api.model.response.reviewprocess.documentfile;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 7/31/2020.
 */
public class DocumentFile implements Serializable {
    @SerializedName("KEY_CODE")
    private String keyCode;
    @SerializedName("FILECODE")
    private String fileCode;
    @SerializedName("FILETYPE")
    private String fileType;
    @SerializedName("FILENAME")
    private String fileName;
    @SerializedName("LINKFILE")
    private String linkFile;

    public DocumentFile() {
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLinkFile() {
        return linkFile;
    }

    public void setLinkFile(String linkFile) {
        this.linkFile = linkFile;
    }
}
