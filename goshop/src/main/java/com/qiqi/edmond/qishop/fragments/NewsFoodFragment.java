package com.qiqi.edmond.qishop.fragments;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qiqi.edmond.qishop.R;
import com.qiqi.edmond.qishop.activitys.NewsInfoActivity;
import com.qiqi.edmond.qishop.adapters.recyclerview.NewsFoodAdapter;
import com.qiqi.edmond.qishop.interfaces.mvp.views.NewsFoodViewInterface;
import com.qiqi.edmond.qishop.models.NewsFoodModel;
import com.qiqi.edmond.qishop.presenters.NewsFoodPresenter;
import com.qiqi.edmond.qishop.utils.News;
import com.qiqi.edmond.qishop.utils.NewsNobannerData;

import java.util.List;

/**
 * Created by 启豪 on 2017/5/7.
 */

public class NewsFoodFragment extends Fragment implements NewsFoodViewInterface,NewsFoodAdapter.OnItemClickListener {

    private View view;
    private View footer;
    private ProgressBar loadmore;
    private TextView loadtext;

    private RecyclerView recyclerView;
    private Activity activity;

    private NewsFoodAdapter adapter;
    private NewsNobannerData data=new NewsNobannerData();

    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsFoodPresenter presenter;

    private boolean isLoadMore = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_news_food,container,false);
        activity=getActivity();
        presenter=new NewsFoodPresenter(this);

        footer=LayoutInflater.from(activity).inflate(R.layout.footer,null);
        loadmore= (ProgressBar) footer.findViewById(R.id.load_progress_bar);
        loadtext= (TextView) footer.findViewById(R.id.load_text);

        adapter=new NewsFoodAdapter(activity,data.getData().getNews(),footer);
        adapter.setOnItemClickListener(this);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(activity,1));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastItemPosition=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (lastItemPosition==data.getData().getNews().size()&&isLoadMore){
                    presenter.loadMoring();
                }
            }
        });

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshing();
            }
        });
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE);

        presenter.initing();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter=null;
    }

    @Override
    public void initing() {

    }

    @Override
    public void inited(NewsNobannerData result) {
        data.getData().getNews().clear();
        data.getData().getNews().addAll(result.getData().getNews());
        adapter.notifyDataSetChanged();
        isLoadMore=true;
    }

    @Override
    public void initError() {
        Toast.makeText(activity,"好像出了点问题",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshing() {
        isLoadMore=false;
    }

    @Override
    public void refreshed(NewsNobannerData result) {
        swipeRefreshLayout.setRefreshing(false);
        data.getData().getNews().clear();
        data.getData().getNews().addAll(result.getData().getNews());
        adapter.notifyDataSetChanged();
        isLoadMore=true;
    }

    @Override
    public void refreshError() {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(activity,"好像出了点问题",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMoring() {
        isLoadMore=false;
    }

    @Override
    public void loadMored(List<News> result) {
        Log.d("","lo");
        data.getData().getNews().addAll(result);
        adapter.notifyDataSetChanged();
        if (result.size()< NewsFoodModel.COUNT){
            isLoadMore=false;
            loadmore.setVisibility(View.GONE);
            loadtext.setText("没有更多");
        }else {
            isLoadMore=true;
        }
    }

    @Override
    public void loadMoreError() {
        Toast.makeText(activity,"好像出了点问题",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemClick(View view, int position, int nid) {
        Intent intent=new Intent(activity, NewsInfoActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("nid",nid);
        bundle.putString("tid","时尚");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
