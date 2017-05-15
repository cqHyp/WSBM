package com.qiqi.edmond.qishop.interfaces.mvp.presenters;

import com.qiqi.edmond.qishop.utils.AddCommentResult;

/**
 * Created by 启豪 on 2017/5/4.
 */

public interface AddCommentPresenterInterface {
    void click(int nid,String uid,String content);
    void clicked(AddCommentResult result);
    void clickError();
}
