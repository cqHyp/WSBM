package com.qiqi.edmond.qishop.interfaces.mvp.presenters;

import com.qiqi.edmond.qishop.utils.CommentData;
import com.qiqi.edmond.qishop.utils.Comments;

import java.util.List;

/**
 * Created by 启豪 on 2017/5/3.
 */

public interface CommentsPresenterInterface {
    void initingComment(int nid);
    void initCommented(CommentData result);
    void initError();

    void refreshing(int nid);
    void refreshed(CommentData result);
    void refreshError();

    void loadMoring(int nid);
    void loadMored(List<Comments> result);
    void loadMoreError();
}
