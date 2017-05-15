package com.qiqi.edmond.qishop.utils;

/**
 * Created by 启豪 on 2017/5/2.
 */

public class NewsInfoData {
    private NewInfo data=new NewInfo();
    private int code;
    private String message;

    public void setData(NewInfo data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public NewInfo getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
