package com.dounine.wopi.core.impl;

import com.dounine.wopi.core.Element;

import java.time.LocalDateTime;

/**
 * Created by lake on 17-4-21.
 */
public class ElementImpl implements Element {

    private String author;
    private String content;
    private String hash;
    private LocalDateTime createTime;

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content){
        if(content.length()>1024){
            throw new RuntimeException("只能存储1024个字符");
        }
        this.content = content;
    }

    @Override
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
