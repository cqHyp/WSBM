package com.qiqi.edmond.qishop.presenters;

import com.qiqi.edmond.qishop.activitys.RegisterActivity;
import com.qiqi.edmond.qishop.interfaces.mvp.presenters.RegisterPresenterInterface;
import com.qiqi.edmond.qishop.models.RegisterModel;
import com.qiqi.edmond.qishop.presenters.base.BasePresenter;
import com.qiqi.edmond.qishop.utils.RegisterResult;

/**
 * Created by 启豪 on 2017/5/10.
 */

public class RegisterPresenter extends BasePresenter<RegisterActivity,RegisterModel> implements RegisterPresenterInterface {
    public RegisterPresenter(RegisterActivity view) {
        super(view);
    }

    @Override
    public void click(String account, String pwd) {
        getView().click();
        getModel().click(this,account,pwd);
    }

    @Override
    public void clicked(RegisterResult result) {
        getView().clicked(result);
    }

    @Override
    public void clickError() {
        getView().clickError();
    }
}
