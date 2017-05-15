package com.qiqi.edmond.qishop.interfaces.mvp.views;

import com.qiqi.edmond.qishop.utils.CommentData;
import com.qiqi.edmond.qishop.utils.Comments;
import com.qiqi.edmond.qishop.utils.NewsInfoData;

import java.util.List;

/**
 * Created by 启豪 on 2017/5/2.
 */

public interface NewsInfoViewInterface {
    void initing();
    void initingcomment();
    void inited(NewsInfoData result);
    void initComment(CommentData result);
    void initError();

    void refreshing();
    void refreshed(CommentData result);
    void refreshError();

    void loadMoring();
    void loadMored(List<Comments> result);
    void loadMoreError();
}
