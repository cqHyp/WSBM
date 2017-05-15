package com.qiqi.edmond.qishop.utils;

/**
 * Created by 启豪 on 2017/5/6.
 */

public class NewsNobannerData {
    private int code;
    private String message;
    private NewsNobanner data=new NewsNobanner();

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(NewsNobanner data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public NewsNobanner getData() {
        return data;
    }
}
