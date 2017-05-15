package com.qiqi.edmond.qishop.interfaces.mvp.presenters;

import com.qiqi.edmond.qishop.utils.News;
import com.qiqi.edmond.qishop.utils.NewsNobannerData;

import java.util.List;

/**
 * Created by 启豪 on 2017/5/8.
 */

public interface NewsRoadPresenterInterface {
    void initing();
    void inited(NewsNobannerData result);
    void initError();

    void refreshing();
    void refreshed(NewsNobannerData result);
    void refreshError();

    void loadMoring();
    void loadMored(List<News> result);
    void loadMoreError();
}
