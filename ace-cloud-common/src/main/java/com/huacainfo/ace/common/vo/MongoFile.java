package com.huacainfo.ace.common.vo;

import java.io.InputStream;

public class MongoFile implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6326016514288699830L;
    private InputStream inputStream;
    private Object id;
    private String filename;
    private String contentType;
    private long length;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
