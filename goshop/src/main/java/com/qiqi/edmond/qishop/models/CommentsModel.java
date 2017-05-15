package com.qiqi.edmond.qishop.models;

import android.util.Log;

import com.qiqi.edmond.qishop.interfaces.mvp.models.CommentsModelInterface;
import com.qiqi.edmond.qishop.models.base.BaseModel;
import com.qiqi.edmond.qishop.net.Api;
import com.qiqi.edmond.qishop.net.BaseStringListener;
import com.qiqi.edmond.qishop.net.StringNetWork;
import com.qiqi.edmond.qishop.presenters.CommentsPresenter;
import com.qiqi.edmond.qishop.utils.CommentData;
import com.qiqi.edmond.qishop.utils.Comments;

import java.util.HashMap;

/**
 * Created by 启豪 on 2017/5/3.
 */

public class CommentsModel extends BaseModel<CommentData> implements CommentsModelInterface {
    public static final int COUNT = 5;
    private int lastId = 0;
    @Override
    public void init(final CommentsPresenter commentsPresenter, int nid) {
        HashMap<String,String> commentmap=new HashMap<>();
        commentmap.put("nid",String.valueOf(nid));
        commentmap.put("lid",String.valueOf(lastId));
        commentmap.put("count",String.valueOf(COUNT));
        StringNetWork CommentStringNetWork=new StringNetWork(0, Api.GET_NEWSCOMMENT, new BaseStringListener<CommentData>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, CommentData response) {
//                Log.i("qqqqq",response.getComments().get(0).getContent());
                getData().setData(response.getData());
                getData().setMessage(response.getMessage());
                if (response.getData().size()>0){
                    lastId=response.getData().get(response.getData().size()-1).getCid();
                }
                commentsPresenter.initCommented(getData());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                commentsPresenter.initError();
            }

            @Override
            public void onFinish(int what) {

            }
        },commentmap);
        CommentStringNetWork.doIt();
    }

    @Override
    public void refresh(final CommentsPresenter commentsPresenter, int nid) {
        lastId=0;
        HashMap<String,String> map=new HashMap<>();
        map.put("nid",String.valueOf(nid));
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));

        StringNetWork stringNetWork=new StringNetWork(0, Api.GET_NEWSCOMMENT, new BaseStringListener<CommentData>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, CommentData response) {
                getData().setData(response.getData());
                if (response.getData().size()>0){
                    lastId=response.getData().get(response.getData().size()-1).getCid();
                }
                commentsPresenter.refreshed(getData());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                commentsPresenter.refreshError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);
        stringNetWork.doIt();
    }

    @Override
    public void loadmore(final CommentsPresenter commentsPresenter, int nid) {
        HashMap<String,String> map=new HashMap<>();
        map.put("nid",String.valueOf(nid));
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));
        StringNetWork stringNetWork=new StringNetWork(0, Api.GET_NEWSCOMMENT, new BaseStringListener<CommentData>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, CommentData response) {
                getData().getData().addAll(response.getData());

                if (response.getData().size()>0){
                    lastId=response.getData().get(response.getData().size()-1).getCid();
                }
                commentsPresenter.loadMored(response.getData());
            }


            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                commentsPresenter.loadMoreError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);
        stringNetWork.doIt();
    }
}
