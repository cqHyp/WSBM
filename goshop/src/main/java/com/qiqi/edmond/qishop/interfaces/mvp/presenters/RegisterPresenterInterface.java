package com.qiqi.edmond.qishop.interfaces.mvp.presenters;

import com.qiqi.edmond.qishop.utils.RegisterResult;

/**
 * Created by 启豪 on 2017/5/10.
 */

public interface RegisterPresenterInterface {
    void click(String account,String pwd);
    void clicked(RegisterResult result);
    void clickError();
}
