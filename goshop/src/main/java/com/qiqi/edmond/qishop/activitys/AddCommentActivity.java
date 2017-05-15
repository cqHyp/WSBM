package com.qiqi.edmond.qishop.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qiqi.edmond.qishop.MyCache;
import com.qiqi.edmond.qishop.R;
import com.qiqi.edmond.qishop.activitys.base.BaseActivity;
import com.qiqi.edmond.qishop.interfaces.mvp.views.AddCommentViewInterface;
import com.qiqi.edmond.qishop.presenters.AddCommentPresenter;
import com.qiqi.edmond.qishop.utils.AddCommentResult;

/**
 * Created by 启豪 on 2017/5/3.
 */

public class AddCommentActivity extends BaseActivity implements AddCommentViewInterface {

    private TextView cancel;
    private TextView addComment;
    private EditText content;

    private int nid;
    private String uid;

    private AddCommentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcomment);

        uid=MyCache.getAccount();
        Bundle bundle=getIntent().getExtras();
        nid=bundle.getInt("nid");

        cancel= (TextView) findViewById(R.id.cancel);
        addComment= (TextView) findViewById(R.id.tv_addcomment);
        content= (EditText) findViewById(R.id.content);

        presenter=new AddCommentPresenter(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.click(nid,uid,content.getText().toString());
            }
        });

    }

    @Override
    public void click() {

    }

    @Override
    public void clicked(AddCommentResult result) {
        if (result.getCode()==200){
            setResult(1);
            finish();

        }else {
            Toast.makeText(this,"评论失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void clickError() {
        Toast.makeText(this,"网络好像有点问题",Toast.LENGTH_SHORT).show();
    }
}
