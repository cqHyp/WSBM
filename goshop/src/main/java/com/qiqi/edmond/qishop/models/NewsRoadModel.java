package com.qiqi.edmond.qishop.models;

import com.qiqi.edmond.qishop.interfaces.mvp.models.NewsRoadModelInterface;
import com.qiqi.edmond.qishop.models.base.BaseModel;
import com.qiqi.edmond.qishop.net.Api;
import com.qiqi.edmond.qishop.net.BaseStringListener;
import com.qiqi.edmond.qishop.net.StringNetWork;
import com.qiqi.edmond.qishop.presenters.NewsRoadPresenter;
import com.qiqi.edmond.qishop.utils.NewsNobannerData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 启豪 on 2017/5/8.
 */

public class NewsRoadModel extends BaseModel<NewsNobannerData> implements NewsRoadModelInterface {
    public static final int COUNT = 5;
    private int lastId = 0;
    @Override
    public void init(final NewsRoadPresenter presenter) {
        Map<String,String> map=new HashMap<>();
        map.put("tid","5");
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));

        StringNetWork stringNetWork=new StringNetWork(0, Api.GET_NEWS, new BaseStringListener<NewsNobannerData>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, NewsNobannerData response) {
                getData().setData(response.getData());
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
    public void refresh(final NewsRoadPresenter presenter) {
        lastId=0;
        Map<String,String> map=new HashMap<>();
        map.put("tid","5");
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));

        StringNetWork stringNetWork=new StringNetWork(0, Api.GET_NEWS, new BaseStringListener<NewsNobannerData>() {
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
    public void loadmore(final NewsRoadPresenter presenter) {
        Map<String,String> map=new HashMap<>();
        map.put("tid","5");
        map.put("lid",String.valueOf(lastId));
        map.put("count",String.valueOf(COUNT));
        StringNetWork stringNetWork=new StringNetWork(0, Api.GET_NEWS, new BaseStringListener<NewsNobannerData>() {
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
