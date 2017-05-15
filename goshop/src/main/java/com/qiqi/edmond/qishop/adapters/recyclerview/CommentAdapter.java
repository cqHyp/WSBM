package com.qiqi.edmond.qishop.adapters.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiqi.edmond.qishop.R;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseFooterAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseHeaderAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseHeaderFooterAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseNormalAdapter;
import com.qiqi.edmond.qishop.utils.Comments;
import com.qiqi.xznview.view.CircleImageView;

import java.util.List;

/**
 * Created by 启豪 on 2017/5/2.
 */

public class CommentAdapter extends BaseHeaderFooterAdapter<Comments> {
    private View footer;
    private View header;

    public CommentAdapter(Context context, List<Comments> data,View footer,View header) {
        super(context, data);
        this.footer=footer;
        this.header=header;
    }

    @Override
    public Footer initFooter(ViewGroup parent) {
        return new CommentFooter(footer);
    }

    @Override
    public Header initHeader(ViewGroup parent) {
        return new CommentHeader(header);
    }

    @Override
    public void bindHeader(BaseAdapter.BaseItem item) {

    }

    @Override
    public void bindFooter(BaseAdapter.BaseItem item) {

    }

    @Override
    public BaseItem initItem(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.item_comment_news,parent,false);
        return new MyItem(view);
    }

    @Override
    public void bindItem(BaseItem item, int position) {
        ((MyItem)item).nameTextView.setText(getData().get(position-1).getNickname());
        ((MyItem)item).ptimeTextView.setText(getData().get(position-1).getPtime());
        ((MyItem)item).contentTextView.setText(getData().get(position-1).getContent());
        Glide.with(getContext()).load(getData().get(position-1).getImage())
                .centerCrop()
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(((MyItem)item).face);
    }



//
//    public CommentAdapter(Context context, List<Comments> data,View footer,View header) {
//        super(context, data);
//        this.footer=footer;
//        this.header=header;
//    }
//
//    @Override
//    public BaseItem initItem(ViewGroup parent) {
//        View view=getInflater().inflate(R.layout.item_comment_news,parent,false);
//        return new MyItem(view);
//    }
//
//    @Override
//    public void bindItem(BaseItem item, int position) {
//                ((MyItem)item).nameTextView.setText(getData().get(position-1).getNickname());
//        ((MyItem)item).ptimeTextView.setText(getData().get(position-1).getPtime());
//        ((MyItem)item).contentTextView.setText(getData().get(position-1).getContent());
//        Glide.with(getContext()).load(getData().get(position-1).getImage())
//                .centerCrop()
//                .crossFade()
//                .placeholder(R.mipmap.ic_launcher)
//                .into(((MyItem)item).face);
//    }
//
//    @Override
//    public Footer initFooter(ViewGroup parent) {
//        return new CommentFooter(footer);
//    }
//
//    @Override
//    public Header initHeader(ViewGroup parent) {
//        return new CommentHeader(header);
//    }
//
//    @Override
//    public void bindHeader(BaseAdapter.BaseItem item) {
//
//    }
//
//    @Override
//    public void bindFooter(BaseAdapter.BaseItem item) {
//
//    }
//
//
////    @Override
////    public void onBindViewHolder(BaseAdapter.BaseItem item,final int position) {
////        ((MyItem)item).nameTextView.setText(getData().get(position-1).getNickname());
////        ((MyItem)item).ptimeTextView.setText(getData().get(position-1).getPtime());
////        ((MyItem)item).contentTextView.setText(getData().get(position-1).getContent());
////        Glide.with(getContext()).load(getData().get(position-1).getImage())
////                .centerCrop()
////                .crossFade()
////                .placeholder(R.mipmap.ic_launcher)
////                .into(((MyItem)item).face);
////    }
//
    class CommentFooter extends Footer{
        public CommentFooter(View itemView){
            super(itemView);

        }
        @Override
        public void initViews(View view) {

        }
    }
//
    class CommentHeader extends Header{
        public CommentHeader(View itemView){
            super(itemView);

        }
        @Override
        public void initViews(View view) {

        }
    }
//
    class MyItem extends BaseItem{

        CircleImageView face;
        TextView nameTextView;
        TextView ptimeTextView;
        TextView contentTextView;
        MyItem(View itemView) {
            super(itemView);
        }

        @Override
        public void initViews(View view) {
            face= (CircleImageView) view.findViewById(R.id.civ_comment_img);
            nameTextView= (TextView) view.findViewById(R.id.tv_comment_name);
            ptimeTextView= (TextView) view.findViewById(R.id.tv_comment_time);
            contentTextView= (TextView) view.findViewById(R.id.tv_comment);
        }
    }
}
