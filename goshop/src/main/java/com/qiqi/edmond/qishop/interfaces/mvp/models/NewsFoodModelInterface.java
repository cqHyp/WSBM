package com.qiqi.edmond.qishop.interfaces.mvp.models;

import com.qiqi.edmond.qishop.presenters.NewsFoodPresenter;

/**
 * Created by 启豪 on 2017/5/7.
 */

public interface NewsFoodModelInterface {
    void init(NewsFoodPresenter presenter);
    void refresh(NewsFoodPresenter presenter);
    void loadmore(NewsFoodPresenter presenter);
}
