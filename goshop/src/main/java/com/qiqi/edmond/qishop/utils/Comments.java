package com.qiqi.edmond.qishop.utils;

/**
 * Created by 启豪 on 2017/5/2.
 */

public class Comments {
    private int cid;
    private int uid;
    private String ptime;
    private String content;
    private String nickname;
    private String image;

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPtime() {
        return ptime;
    }

    public String getContent() {
        return content;
    }

    public int getUid() {
        return uid;
    }

    public String getImage() {
        return image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }
}
