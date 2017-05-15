package com.qiqi.edmond.qishop.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.qiqi.edmond.qishop.MyCache;
import com.qiqi.edmond.qishop.R;
import com.qiqi.edmond.qishop.activitys.LoginActivity;
import com.qiqi.edmond.qishop.adapters.recyclerview.ShoppingCartAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.ShoppingCartItemAdapter;
import com.qiqi.edmond.qishop.interfaces.mvp.views.ShoppingCartViewInterface;
import com.qiqi.edmond.qishop.presenters.ShoppingCartPresenter;
import com.qiqi.edmond.qishop.utils.ShoppingCartData;
import com.qiqi.edmond.qishop.utils.ShoppingCartInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmond on 17-1-3.
 */

public class KnowFragment extends Fragment implements ShoppingCartViewInterface,ShoppingCartAdapter.OnItemClickListener{
    private View view;
    private RecyclerView recyclerView;
    private ShoppingCartAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageButton checkButton;

    private Activity activity;
    private List<ShoppingCartInfo> data = new ArrayList<>();

    private ShoppingCartPresenter presenter;

    private boolean isSelect = false;

    // data
    private String receiverid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_know,container,false);
        activity = getActivity();

        initData();
        presenter = new ShoppingCartPresenter(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

        adapter=new ShoppingCartAdapter(activity,data);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

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
        presenter = null;
    }



    @Override
    public void initing() {

    }

    @Override
    public void inited(ShoppingCartData result) {
        data.clear();
        data.addAll(result.getShoppingCartInfos());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initError() {
        Toast.makeText(activity,"好像出了点问题",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshing() {

    }

    @Override
    public void refreshed(ShoppingCartData result) {
        swipeRefreshLayout.setRefreshing(false);
        isSelect = false;
        data.clear();
        data.addAll(result.getShoppingCartInfos());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshError() {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(activity,"好像出了点问题",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemClick(View view, int position) {

    }

    private void initData() {
        if (MyCache.getAccount()!=null){
            receiverid = MyCache.getAccount().equals("2016") ? "1010" : "2016";
        }

    }
    @Override
    public void editClick(View view, int position) {
        //联系客服
        if (MyCache.getAccount()!=null){
            NimUIKit.startChatting(activity,receiverid, SessionTypeEnum.P2P,null);
        }else {
            Intent intent=new Intent(activity, LoginActivity.class);
            startActivity(intent);
        }

    }
}
