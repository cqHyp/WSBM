package com.qiqi.edmond.qishop.presenters;

import com.qiqi.edmond.qishop.fragments.NewsRoadFragment;
import com.qiqi.edmond.qishop.interfaces.mvp.presenters.NewsRoadPresenterInterface;
import com.qiqi.edmond.qishop.models.NewsRoadModel;
import com.qiqi.edmond.qishop.presenters.base.BasePresenter;
import com.qiqi.edmond.qishop.utils.News;
import com.qiqi.edmond.qishop.utils.NewsNobannerData;

import java.util.List;

/**
 * Created by 启豪 on 2017/5/8.
 */

public class NewsRoadPresenter extends BasePresenter<NewsRoadFragment,NewsRoadModel> implements NewsRoadPresenterInterface {
    public NewsRoadPresenter(NewsRoadFragment view) {
        super(view);
    }

    @Override
    public void initing() {
        getView().initing();
        getModel().init(this);
    }

    @Override
    public void inited(NewsNobannerData result) {
        getView().inited(result);
    }

    @Override
    public void initError() {
        getView().initError();
    }

    @Override
    public void refreshing() {
        getView().refreshing();
        getModel().refresh(this);
    }

    @Override
    public void refreshed(NewsNobannerData result) {
        getView().refreshed(result);
    }

    @Override
    public void refreshError() {
        getView().refreshError();
    }

    @Override
    public void loadMoring() {
        getView().loadMoring();
        getModel().loadmore(this);
    }

    @Override
    public void loadMored(List<News> result) {
        getView().loadMored(result);
    }

    @Override
    public void loadMoreError() {
        getView().loadMoreError();
    }
}
