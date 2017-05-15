package com.qiqi.edmond.qishop.interfaces.mvp.models;

import com.qiqi.edmond.qishop.presenters.NewsInfoPresenter;

/**
 * Created by 启豪 on 2017/5/2.
 */

public interface NewsInfoModelInterface {
    void init(NewsInfoPresenter newsInfoPresenter,int nid);
    void refresh(NewsInfoPresenter newsInfoPresenter);
}
