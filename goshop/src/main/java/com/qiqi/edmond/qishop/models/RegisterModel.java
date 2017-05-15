package com.qiqi.edmond.qishop.models;

import com.qiqi.edmond.qishop.interfaces.mvp.models.RegisterModelInterface;
import com.qiqi.edmond.qishop.models.base.BaseModel;
import com.qiqi.edmond.qishop.net.Api;
import com.qiqi.edmond.qishop.net.BaseStringListener;
import com.qiqi.edmond.qishop.net.StringNetWork;
import com.qiqi.edmond.qishop.presenters.RegisterPresenter;
import com.qiqi.edmond.qishop.utils.RegisterResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 启豪 on 2017/5/10.
 */

public class RegisterModel extends BaseModel<RegisterResult> implements RegisterModelInterface{
    @Override
    public void click(final RegisterPresenter presenter, String account, String pwd) {
        Map<String,String> map=new HashMap<>();
        map.put("nickname",String.valueOf(account));
        map.put("upwd",String.valueOf(pwd));

        StringNetWork stringNetWork=new StringNetWork(0, Api.REGISTER, new BaseStringListener<RegisterResult>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, RegisterResult response) {
                getData().setCode(response.getCode());
                getData().setMessage(response.getMessage());
                getData().setUserInfo(response.getUserInfo());
                presenter.clicked(response);
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                presenter.clickError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);
        stringNetWork.doIt();
    }
}
