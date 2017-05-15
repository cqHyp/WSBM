package com.qiqi.edmond.qishop.adapters.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiqi.edmond.qishop.R;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseFooterAdapter;
import com.qiqi.edmond.qishop.adapters.recyclerview.base.BaseNormalAdapter;
import com.qiqi.edmond.qishop.utils.ShoppingCartInfo;
import com.qiqi.edmond.qishop.views.decoration.LinearLayoutItemDecoration;
import com.qiqi.xznview.view.CircleImageView;

import java.util.List;

/**
 * Created by edmond on 17-2-23.
 */

public class ShoppingCartAdapter extends BaseNormalAdapter<ShoppingCartInfo> {

    public interface OnItemClickListener{
        void itemClick(View view,int position);
        void editClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;
    private ShoppingCartItemAdapter.OnItemClickListener onSonItemClickListener;

    public ShoppingCartAdapter(Context context, List<ShoppingCartInfo> data) {
        super(context, data);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public BaseItem initItem(ViewGroup parent) {
        View view = getInflater().inflate(R.layout.item_shopping_cart, parent, false);
        return new MyItem(view);
    }

    @Override
    public void bindItem(BaseAdapter.BaseItem item, final int position) {
        ShoppingCartItemAdapter adapter = new ShoppingCartItemAdapter(getContext(),getData().get(position).getShoppingCartItemInfoList(),position);
        adapter.setOnItemClickListener(onSonItemClickListener);

        ((MyItem)item).nameTextView.setText(getData().get(position).getName());

        Glide.with(getContext()).load(getData().get(position).getIconUrl())
                .centerCrop()
                .crossFade()
                .dontAnimate()
                .placeholder(com.qiqi.xznview.R.mipmap.ic_launcher)
                .into(((MyItem)item).iconImageView);

        if (onItemClickListener!=null){
            ((MyItem)item).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.itemClick(v,position);
                }
            });
            ((MyItem)item).editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.editClick(v,position);
                }
            });

        }
    }

    class MyItem extends BaseItem{

        private CircleImageView iconImageView;
        private TextView nameTextView;
        private Button editBtn;
        public MyItem(View itemView) {
            super(itemView);
        }

        @Override
        public void initViews(View view) {

            iconImageView = (CircleImageView) view.findViewById(R.id.icon);
            nameTextView = (TextView) view.findViewById(R.id.name);
            editBtn = (Button) view.findViewById(R.id.call);
        }
    }
}
