package com.qiqi.edmond.qishop.interfaces.mvp.presenters;

import com.qiqi.edmond.qishop.utils.NewsInfoData;

/**
 * Created by 启豪 on 2017/5/2.
 */

public interface NewsInfoPresenterInterface {
    void initing(int nid);
    void inited(NewsInfoData result);
    void initError();

    void refreshing();
    void refreshed(NewsInfoData result);
    void refreshError();
}
