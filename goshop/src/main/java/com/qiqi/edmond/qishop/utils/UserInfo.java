package com.qiqi.edmond.qishop.utils;

/**
 * Created by 启豪 on 2017/5/10.
 */

public class UserInfo {
    private String token;
    private String accid;
    private String name;

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccid() {
        return accid;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
