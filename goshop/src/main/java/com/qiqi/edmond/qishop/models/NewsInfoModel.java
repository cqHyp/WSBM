package com.qiqi.edmond.qishop.models;




import com.qiqi.edmond.qishop.interfaces.mvp.models.NewsInfoModelInterface;
import com.qiqi.edmond.qishop.models.base.BaseModel;
import com.qiqi.edmond.qishop.net.Api;
import com.qiqi.edmond.qishop.net.BaseStringListener;
import com.qiqi.edmond.qishop.net.StringNetWork;
import com.qiqi.edmond.qishop.presenters.NewsInfoPresenter;

import com.qiqi.edmond.qishop.utils.CommentData;
import com.qiqi.edmond.qishop.utils.NewsInfoData;

import java.util.HashMap;


/**
 * Created by 启豪 on 2017/5/2.
 */

public class NewsInfoModel extends BaseModel<NewsInfoData> implements NewsInfoModelInterface {

    @Override
    public void init(final NewsInfoPresenter newsInfoPresenter,int nid) {
        HashMap<String,String> map=new HashMap<>();
        map.put("nid",String.valueOf(nid));
        StringNetWork stringNetWork=new StringNetWork(0, Api.GET_NEWSINFO, new BaseStringListener<NewsInfoData>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, NewsInfoData response) {
                getData().setData(response.getData());
                getData().setCode(response.getCode());
                getData().setMessage(response.getMessage());
                newsInfoPresenter.inited(getData());

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                newsInfoPresenter.initError();
            }

            @Override
            public void onFinish(int what) {

            }
        },map);
        stringNetWork.doIt();


    }


    @Override
    public void refresh(NewsInfoPresenter newsInfoPresenter) {

    }
}
