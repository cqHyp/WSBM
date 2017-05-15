package com.qiqi.edmond.qishop.interfaces.mvp.models;

import com.qiqi.edmond.qishop.presenters.NewsRoadPresenter;

/**
 * Created by 启豪 on 2017/5/8.
 */

public interface NewsRoadModelInterface {
    void init(NewsRoadPresenter presenter);
    void refresh(NewsRoadPresenter presenter);
    void loadmore(NewsRoadPresenter presenter);
}
