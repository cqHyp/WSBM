package com.qiqi.edmond.qishop.models;

import com.qiqi.edmond.qishop.interfaces.mvp.models.AddCommentModelInterface;
import com.qiqi.edmond.qishop.models.base.BaseModel;
import com.qiqi.edmond.qishop.net.Api;
import com.qiqi.edmond.qishop.net.BaseStringListener;
import com.qiqi.edmond.qishop.net.StringNetWork;
import com.qiqi.edmond.qishop.presenters.AddCommentPresenter;
import com.qiqi.edmond.qishop.utils.AddCommentResult;

import java.util.HashMap;

/**
 * Created by 启豪 on 2017/5/4.
 */

public class AddCommentModel extends BaseModel<AddCommentResult> implements AddCommentModelInterface {
    @Override
    public void click(final AddCommentPresenter presenter, int nid, String uid, String content) {
        HashMap<String,String> map=new HashMap<>();
        map.put("nid",String.valueOf(nid));
        map.put("uid",String.valueOf(uid));
        map.put("content",content);

        StringNetWork stringNetWork=new StringNetWork(0, Api.ADD_NEWSCOMMENT, new BaseStringListener<AddCommentResult>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, AddCommentResult response) {
                getData().setCode(response.getCode());
                getData().setMessage(response.getMessage());
                presenter.clicked(getData());
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
