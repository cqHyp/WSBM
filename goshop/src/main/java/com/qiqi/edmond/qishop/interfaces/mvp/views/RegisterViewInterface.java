package com.qiqi.edmond.qishop.interfaces.mvp.views;

import com.qiqi.edmond.qishop.utils.RegisterResult;

/**
 * Created by 启豪 on 2017/5/10.
 */

public interface RegisterViewInterface {
    void click();
    void clicked(RegisterResult result);
    void clickError();

}
