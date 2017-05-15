package com.qiqi.edmond.qishop.presenters;

import com.qiqi.edmond.qishop.activitys.NewsInfoActivity;
import com.qiqi.edmond.qishop.interfaces.mvp.presenters.CommentsPresenterInterface;
import com.qiqi.edmond.qishop.models.CommentsModel;
import com.qiqi.edmond.qishop.presenters.base.BasePresenter;
import com.qiqi.edmond.qishop.utils.CommentData;
import com.qiqi.edmond.qishop.utils.Comments;

import java.util.List;

/**
 * Created by 启豪 on 2017/5/3.
 */

public class CommentsPresenter extends BasePresenter<NewsInfoActivity,CommentsModel> implements CommentsPresenterInterface {
    public CommentsPresenter(NewsInfoActivity view) {
        super(view);
    }

    @Override
    public void initingComment(int nid) {
        getView().initing();
        getModel().init(this,nid);

    }

    @Override
    public void initCommented(CommentData result) {
        getView().initComment(result);
    }

    @Override
    public void initError() {
        getView().initError();
    }

    @Override
    public void refreshing(int nid) {
        getView().refreshing();
        getModel().refresh(this,nid);
    }

    @Override
    public void refreshed(CommentData result) {
        getView().refreshed(result);
    }

    @Override
    public void refreshError() {
        getView().refreshError();
    }

    @Override
    public void loadMoring(int nid) {
        getView().loadMoring();
        getModel().loadmore(this,nid);
    }

    @Override
    public void loadMored(List<Comments> result) {
        getView().loadMored(result);
    }

    @Override
    public void loadMoreError() {
        getView().loadMoreError();
    }
}
