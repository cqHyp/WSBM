package com.qiqi.edmond.qishop.utils;

/**
 * Created by 启豪 on 2017/5/10.
 */

public class RegisterResult {
    private int code;
    private String message;
    private UserInfo userInfo;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
