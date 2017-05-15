package com.qiqi.edmond.qishop.interfaces.mvp.views;

import com.qiqi.edmond.qishop.utils.AddCommentResult;

/**
 * Created by 启豪 on 2017/5/4.
 */

public interface AddCommentViewInterface {
    void click();
    void clicked(AddCommentResult result);
    void clickError();
}
