package com.qiqi.edmond.qishop.models;

import android.util.Log;

import com.qiqi.edmond.qishop.interfaces.mvp.models.HomeModelInterface;
import com.qiqi.edmond.qishop.models.base.BaseModel;
import com.qiqi.edmond.qishop.net.Api;
import com.qiqi.edmond.qishop.net.BaseStringListener;
import com.qiqi.edmond.qishop.net.StringNetWork;
import com.qiqi.edmond.qishop.presenters.HomePresenter;
import com.qiqi.edmond.qishop.utils.HomeData;
import com.qiqi.edmond.qishop.utils.HomeInfo;
import com.qiqi.edmond.qishop.utils.HomeInfoMore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by edmond on 17-1-15.
 */

public class HomeModel extends BaseModel<HomeData> implements HomeModelInterface {

    public static final int COUNT = 5;
    private int lastId = 0;

    @Override
    public void init(final HomePresenter presenter) {
        HomeData homeData=new HomeData();
        List<HomeInfo> list=new ArrayList<>();
        HomeInfo info1=new HomeInfo();
        HomeInfo info2=new HomeInfo();
        HomeInfo info3=new HomeInfo();

        info1.setInfoId(1);
        info1.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494411351285&di=ffdcafd70eaf54ee556857655df2bc9a&imgtype=0&src=http%3A%2F%2Fimg2.cache.netease.com%2Fphoto%2F0001%2F2014-05-21%2F9SOMB8G700AN0001.jpg");
        info1.setTitle("普京同江泽民会面");
        info1.setInfoStr("");

        info2.setInfoId(2);
        info2.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494411517457&di=ea98e531566355a7ad227a483c2deb64&imgtype=0&src=http%3A%2F%2Fi2.letvimg.com%2Flc12_yunzhuanma%2F201704%2F15%2F20%2F47%2Fcabb250eb89904ecb56b41c917bcc562_v2_NDYzNDgyMjMy%2Fthumb%2F2_640_360.jpg");
        info2.setTitle("小学安全教育");
        info2.setInfoStr("南县南洲镇第五完全小学安全教育实践");

        info3.setInfoId(3);
        info3.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494417101341&di=d4999526b92d5b409e9f47c799ae1f7f&imgtype=0&src=http%3A%2F%2Fcqtimes.cn%2FUploads%2FPicture%2F2016-10-20%2F9458118d-a2dc-46ed-b48b-01ba21c8acc1.jpg");
        info3.setTitle("新闻联播");
        info3.setInfoStr("2017年5月10日");


        list.add(info1);
        list.add(info2);
        list.add(info3);
        homeData.setInfoList(list);

        presenter.inited(homeData);

//        HashMap<String,String> map = new HashMap<>();
//        map.put("uid","1");
//        map.put("token","");
//
//        StringNetWork stringNetWork = new StringNetWork(0, Api.HOME_INIT, new BaseStringListener<HomeData>() {
//            @Override
//            public void onStart(int what) {
//
//            }
//
//            @Override
//            public void onSucceed(int what, HomeData response) {
//                Log.d("TAG++++++", String.valueOf(response.getBannerList().size()));
//                getData().setBannerList(response.getBannerList());
//                getData().setInfoList(response.getInfoList());
//                getData().setContentUImageUrl(response.getContentUImageUrl());
//                presenter.inited(getData());
//            }
//
//            @Override
//            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
//                presenter.initError();
//            }
//
//            @Override
//            public void onFinish(int what) {
//
//            }
//        },map);

//        stringNetWork.doIt();
    }

    @Override
    public void refresh(final HomePresenter presenter) {
        HashMap<String,String> map = new HashMap<>();
        map.put("uid","1");
        map.put("token","");

        StringNetWork stringNetWork = new StringNetWork(0, Api.HOME_INIT, new BaseStringListener<HomeData>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, HomeData response) {
                Log.d("TAG++++++", String.valueOf(response.getBannerList().size()));
                getData().setBannerList(response.getBannerList());
                getData().setInfoList(response.getInfoList());
                getData().setContentUImageUrl(response.getContentUImageUrl());
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

//        stringNetWork.doIt();
    }

    @Override
    public void loadMore(final HomePresenter presenter) {
        HashMap<String,String> map = new HashMap<>();
        map.put("uid","1");
        map.put("token","");
        map.put("lastId","1");

        StringNetWork stringNetWork = new StringNetWork(0, Api.HOME_MORE, new BaseStringListener<HomeInfoMore>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, HomeInfoMore response) {
                getData().getInfoList().addAll(response.getInfoList());
                presenter.loadMored(response.getInfoList());
            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                presenter.loadMoreError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);

//        stringNetWork.doIt();
    }
}
