package com.qiqi.edmond.qishop.presenters;

import com.qiqi.edmond.qishop.activitys.NewsInfoActivity;
import com.qiqi.edmond.qishop.interfaces.mvp.presenters.NewsInfoPresenterInterface;
import com.qiqi.edmond.qishop.models.NewsInfoModel;
import com.qiqi.edmond.qishop.presenters.base.BasePresenter;
import com.qiqi.edmond.qishop.utils.CommentData;
import com.qiqi.edmond.qishop.utils.NewsInfoData;

/**
 * Created by 启豪 on 2017/5/2.
 */

public class NewsInfoPresenter extends BasePresenter<NewsInfoActivity,NewsInfoModel> implements NewsInfoPresenterInterface{
    public NewsInfoPresenter(NewsInfoActivity view) {
        super(view);
    }

    @Override
    public void initing(int nid) {
        getView().initing();
        getModel().init(this,nid);
    }

//    @Override
//    public void initingComment(int nid) {
//        getView().initingcomment();
//        getModel().initComment(this,nid);
//    }

    @Override
    public void inited(NewsInfoData result) {
        getView().inited(result);
    }

//    @Override
//    public void initCommented(CommentData result) {
//
//    }

    @Override
    public void initError() {
        getView().initError();
    }

    @Override
    public void refreshing() {

    }

    @Override
    public void refreshed(NewsInfoData result) {

    }

    @Override
    public void refreshError() {

    }
}
