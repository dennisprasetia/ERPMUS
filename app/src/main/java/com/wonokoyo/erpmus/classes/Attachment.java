package com.wonokoyo.erpmus.classes;

public class Attachment {
    private String url;
    private int type;
    private String name;

    public Attachment(String url, int type, String name) {
        this.url = url;
        this.type = type;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
