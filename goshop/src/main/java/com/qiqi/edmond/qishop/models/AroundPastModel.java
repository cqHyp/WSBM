package com.qiqi.edmond.qishop.models;

import com.qiqi.edmond.qishop.interfaces.mvp.models.AroundPastModelInterface;
import com.qiqi.edmond.qishop.models.base.BaseModel;
import com.qiqi.edmond.qishop.net.Api;
import com.qiqi.edmond.qishop.net.BaseStringListener;
import com.qiqi.edmond.qishop.net.StringNetWork;
import com.qiqi.edmond.qishop.presenters.AroundPastPresenter;
import com.qiqi.edmond.qishop.utils.AroundPastData;
import com.qiqi.edmond.qishop.utils.AroundPastInfoMore;
import com.qiqi.edmond.qishop.utils.NewsNobannerData;

import java.util.HashMap;

/**
 * Created by edmond on 17-2-21.
 */

public class AroundPastModel extends BaseModel<NewsNobannerData> implements AroundPastModelInterface {
    public static final int COUNT = 5;
    private int lastId = 0;
    @Override
    public void init(final AroundPastPresenter presenter) {
        HashMap<String,String> map = new HashMap<>();
        map.put("tid","3");
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));

        StringNetWork stringNetWork = new StringNetWork(0, Api.GET_NEWS, new BaseStringListener<NewsNobannerData>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, NewsNobannerData response) {
                getData().getData().getNews().addAll(response.getData().getNews());
                if(response.getData().getNews().size()>0){
                    lastId = response.getData().getNews().get(response.getData().getNews().size()-1).getNid();
                }
                presenter.inited(getData());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                presenter.initError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);

        stringNetWork.doIt();
    }

    @Override
    public void refresh(final AroundPastPresenter presenter) {
        lastId = 0;
        HashMap<String,String> map = new HashMap<>();
        map.put("tid","3");
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));

        StringNetWork stringNetWork = new StringNetWork(0, Api.GET_NEWS, new BaseStringListener<NewsNobannerData>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, NewsNobannerData response) {
                getData().setData(response.getData());

                if(response.getData().getNews().size()>0){
                    lastId = response.getData().getNews().get(response.getData().getNews().size()-1).getNid();
                }
                presenter.refreshed(getData());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                presenter.refreshError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);

        stringNetWork.doIt();
    }

    @Override
    public void loadMore(final AroundPastPresenter presenter) {
        HashMap<String,String> map = new HashMap<>();
        map.put("tid","3");
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));

        StringNetWork stringNetWork = new StringNetWork(0, Api.GET_NEWS, new BaseStringListener<NewsNobannerData>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, NewsNobannerData response) {
                getData().getData().getNews().addAll(response.getData().getNews());

                if(response.getData().getNews().size()>0){
                    lastId = response.getData().getNews().get(response.getData().getNews().size()-1).getNid();
                }
                presenter.loadMored(response.getData().getNews());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                presenter.loadMoreError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);

        stringNetWork.doIt();
    }
}
