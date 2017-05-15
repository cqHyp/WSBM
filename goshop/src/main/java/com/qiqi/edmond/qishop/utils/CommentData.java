package com.qiqi.edmond.qishop.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 启豪 on 2017/5/2.
 */

public class CommentData {
    private List<Comments> data=new ArrayList<>();
    private int code;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Comments> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public List<Comments> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
