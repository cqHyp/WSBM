package com.qiqi.edmond.qishop.interfaces.mvp.models;

import com.qiqi.edmond.qishop.presenters.RegisterPresenter;

/**
 * Created by 启豪 on 2017/5/10.
 */

public interface RegisterModelInterface {
    void click(RegisterPresenter presenter,String account,String pwd);
}
