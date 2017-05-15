package com.qiqi.edmond.qishop.presenters;

import com.qiqi.edmond.qishop.activitys.AddCommentActivity;
import com.qiqi.edmond.qishop.interfaces.mvp.presenters.AddCommentPresenterInterface;
import com.qiqi.edmond.qishop.models.AddCommentModel;
import com.qiqi.edmond.qishop.presenters.base.BasePresenter;
import com.qiqi.edmond.qishop.utils.AddCommentResult;

/**
 * Created by 启豪 on 2017/5/4.
 */

public class AddCommentPresenter extends BasePresenter<AddCommentActivity,AddCommentModel> implements AddCommentPresenterInterface {
    public AddCommentPresenter(AddCommentActivity view) {
        super(view);
    }

    @Override
    public void click(int nid, String uid, String content) {
        getView().click();
        getModel().click(this,nid,uid,content);
    }

    @Override
    public void clicked(AddCommentResult result) {
        getView().clicked(result);
    }

    @Override
    public void clickError() {
        getView().clickError();
    }
}
