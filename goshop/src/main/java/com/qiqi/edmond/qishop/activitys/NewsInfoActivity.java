package com.qiqi.edmond.qishop.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qiqi.edmond.qishop.MyCache;
import com.qiqi.edmond.qishop.R;
import com.qiqi.edmond.qishop.activitys.base.BaseActivity;
import com.qiqi.edmond.qishop.adapters.ViewPagerAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.CommentAdapter;
import com.qiqi.edmond.qishop.interfaces.mvp.views.NewsInfoViewInterface;
import com.qiqi.edmond.qishop.models.CommentsModel;
import com.qiqi.edmond.qishop.presenters.CommentsPresenter;
import com.qiqi.edmond.qishop.presenters.NewsInfoPresenter;
import com.qiqi.edmond.qishop.utils.CommentData;
import com.qiqi.edmond.qishop.utils.Comments;
import com.qiqi.edmond.qishop.utils.NewsInfoData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmond on 17-4-30.
 */

public class NewsInfoActivity extends BaseActivity implements NewsInfoViewInterface{

    private TextView newsInfoTitle;
    private ViewPager viewPager;
    private ArrayList<View> views = new ArrayList<>();
    private WebView webView;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private TextView addComment;

    private View header;
    private View footer;
    private ProgressBar loadmore;
    private TextView loadtext;
    private NewsInfoPresenter presenter;
    private NewsInfoData data=new NewsInfoData();
    private CommentData commentData=new CommentData();
    private CommentsPresenter commentsPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int nid;
    private String type;
    private boolean isloadMore=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        Bundle bundle=this.getIntent().getExtras();
        nid=bundle.getInt("nid");
        type=bundle.getString("tid");

        presenter=new NewsInfoPresenter(this);
        presenter.initing(nid);

        commentsPresenter=new CommentsPresenter(this);
        commentsPresenter.initingComment(nid);

        View info = getLayoutInflater().inflate(R.layout.view_info,null);
        View comment = getLayoutInflater().inflate(R.layout.view_comment,null);

        footer=LayoutInflater.from(this).inflate(R.layout.footer,null);
        header=LayoutInflater.from(this).inflate(R.layout.header_comment,null);

        recyclerView = (RecyclerView) comment.findViewById(R.id.recyclerView);
        adapter=new CommentAdapter(getBaseContext(),commentData.getData(),footer,header);
        linearLayoutManager=new GridLayoutManager(getBaseContext(),1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (lastItemPosition==commentData.getData().size()&&isloadMore){
                    commentsPresenter.loadMoring(nid);
                }
            }
        });


        loadmore= (ProgressBar) footer.findViewById(R.id.load_progress_bar);
        loadtext= (TextView) footer.findViewById(R.id.load_text);

        newsInfoTitle= (TextView) info.findViewById(R.id.tv_newsInfotitle);

        webView = (WebView) info.findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.loadDataWithBaseURL(null,getNewContent("<html><body>dfjksdjfklsdfj施展<img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/><img src=\\\"http://img05.tooopen.com/images/20150202/sy_80219211654.jpg\\\"/></body></html>"),"text/html","charset=UTF-8", null);


        views.add(info);
        views.add(comment);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(views));

        swipeRefreshLayout= (SwipeRefreshLayout) comment.findViewById(R.id.swiperefresh_comment);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                commentsPresenter.refreshing(nid);

            }
        });
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.YELLOW,Color.RED,Color.GREEN);

        addComment= (TextView) findViewById(R.id.tv_addcomment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyCache.getAccount()!=null){
                    Intent intent=new Intent(getBaseContext(),AddCommentActivity.class);
                    Bundle addcommentbundle=new Bundle();
                    addcommentbundle.putInt("nid",nid);
                    intent.putExtras(addcommentbundle);
                    startActivityForResult(intent,1);
                }else {
                    Intent intent=new Intent(getBaseContext(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private String getNewContent(String htmltext){

        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }

        Log.d("VACK", doc.toString());
        return doc.toString();
    }

    @Override
    public void initing() {

    }

    @Override
    public void initingcomment() {

    }

    @Override
    public void inited(NewsInfoData result) {
        data.setData(result.getData());
        webView.loadDataWithBaseURL(null,getNewContent(result.getData().getContent()),"text/html","charset=UTF-8", null);
        newsInfoTitle.setText(type);

    }

    @Override
    public void initComment(CommentData result) {
        commentData.getData().clear();
        commentData.getData().addAll(result.getData());
        adapter.notifyDataSetChanged();
        if (commentData.getData().size()<CommentsModel.COUNT){
            loadmore.setVisibility(View.GONE);
            loadtext.setText("没有评论");
        }

    }

    @Override
    public void initError() {
        Toast.makeText(getBaseContext(),"好像出了点问题",Toast.LENGTH_LONG).show();
    }

    @Override
    public void refreshing() {
        isloadMore=false;
    }

    @Override
    public void refreshed(CommentData result) {
        swipeRefreshLayout.setRefreshing(false);
        commentData.getData().clear();
        commentData.getData().addAll(result.getData());
        adapter.notifyDataSetChanged();
        isloadMore=true;
    }

    @Override
    public void refreshError() {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getBaseContext(),"好像出了点问题",Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMoring() {
        isloadMore=false;
    }

    @Override
    public void loadMored(List<Comments> result) {
        commentData.getData().addAll(result);
        adapter.notifyDataSetChanged();
        if (result.size()< CommentsModel.COUNT){
            isloadMore=false;
            loadmore.setVisibility(View.GONE);
            loadtext.setText("没有更多");
        }else {
            isloadMore=true;
        }
    }

    @Override
    public void loadMoreError() {
        Toast.makeText(getBaseContext(),"好像出了点问题",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==1){
            commentsPresenter.refreshing(nid);
        }
    }
}
