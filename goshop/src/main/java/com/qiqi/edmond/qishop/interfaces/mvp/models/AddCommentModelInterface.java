package com.qiqi.edmond.qishop.interfaces.mvp.models;

import com.qiqi.edmond.qishop.presenters.AddCommentPresenter;

/**
 * Created by 启豪 on 2017/5/4.
 */

public interface AddCommentModelInterface {
    void click(AddCommentPresenter presenter,int nid,String uid,String content);

}
