package com.qiqi.edmond.qishop.utils;

/**
 * Created by 启豪 on 2017/5/2.
 */

public class NewInfo {
    private String title;
    private String content;
    private String ptime;
    private int comment_count;
    private int nid;

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getNid() {
        return nid;
    }

    public String getContent() {
        return content;
    }

    public String getPtime() {
        return ptime;
    }

    public String getTitle() {
        return title;
    }
}
