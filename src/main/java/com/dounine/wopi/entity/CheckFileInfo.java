package com.dounine.wopi.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by lake on 17-5-12.
 */
public class CheckFileInfo implements IFileInfo{

    @JSONField(name = "BaseFileName")
    private String BaseFileName;
    @JSONField(name = "OwnerId")
    private String OwnerId;
    @JSONField(name = "Size")
    private String Size;
    @JSONField(name = "SHA256")
    private String SHA256;
    @JSONField(name = "Version")
    private String Version;

    public String getBaseFileName() {
        return BaseFileName;
    }

    public void setBaseFileName(String baseFileName) {
        BaseFileName = baseFileName;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String ownerId) {
        OwnerId = ownerId;
    }

    @Override
    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getSHA256() {
        return SHA256;
    }

    public void setSHA256(String SHA256) {
        this.SHA256 = SHA256;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
