package com.qiqi.edmond.qishop.interfaces.mvp.models;

import com.qiqi.edmond.qishop.presenters.CommentsPresenter;

/**
 * Created by 启豪 on 2017/5/3.
 */

public interface CommentsModelInterface {
    void init(CommentsPresenter commentsPresenter,int nid);
    void refresh(CommentsPresenter commentsPresenter,int nid);
    void loadmore(CommentsPresenter commentsPresenter,int nid);
}
