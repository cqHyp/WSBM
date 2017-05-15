package com.qiqi.edmond.qishop.interfaces.mvp.views;

import com.qiqi.edmond.qishop.utils.AroundWillInfo;
import com.qiqi.edmond.qishop.utils.AroundWillData;
import com.qiqi.edmond.qishop.utils.News;
import com.qiqi.edmond.qishop.utils.NewsNobannerData;

import java.util.List;

/**
 * Created by edmond on 17-2-21.
 */

public interface AroundWillViewInterface {
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
