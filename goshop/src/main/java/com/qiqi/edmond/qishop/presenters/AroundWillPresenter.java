package com.qiqi.edmond.qishop.presenters;

import com.qiqi.edmond.qishop.fragments.AroundWillFragment;
import com.qiqi.edmond.qishop.models.AroundWillModel;
import com.qiqi.edmond.qishop.presenters.base.BasePresenter;
import com.qiqi.edmond.qishop.interfaces.mvp.presenters.AroundWillPresenterInterface;
import com.qiqi.edmond.qishop.utils.AroundWillInfo;
import com.qiqi.edmond.qishop.utils.AroundWillData;
import com.qiqi.edmond.qishop.utils.News;
import com.qiqi.edmond.qishop.utils.NewsNobannerData;

import java.util.List;

/**
 * Created by edmond on 17-2-21.
 */

public class AroundWillPresenter extends BasePresenter<AroundWillFragment,AroundWillModel> implements AroundWillPresenterInterface {
    public AroundWillPresenter(AroundWillFragment view) {
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
        getModel().loadMore(this);
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
