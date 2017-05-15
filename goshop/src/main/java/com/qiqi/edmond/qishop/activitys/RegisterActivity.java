package com.qiqi.edmond.qishop.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qiqi.edmond.qishop.R;
import com.qiqi.edmond.qishop.activitys.base.BaseActivity;
import com.qiqi.edmond.qishop.interfaces.mvp.views.RegisterViewInterface;
import com.qiqi.edmond.qishop.presenters.RegisterPresenter;
import com.qiqi.edmond.qishop.utils.RegisterResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 启豪 on 2017/5/10.
 */

public class RegisterActivity extends BaseActivity implements RegisterViewInterface {

    private EditText account;
    private EditText token;
    private EditText tokenSecond;
    private Button registerbtn;

    RegisterPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        account= (EditText) findViewById(R.id.account_edit);
        token= (EditText) findViewById(R.id.token_edit);
        tokenSecond= (EditText) findViewById(R.id.tokensecond_edit);
        registerbtn= (Button) findViewById(R.id.register);
        presenter=new RegisterPresenter(this);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (token.getText().toString().equals(tokenSecond.getText().toString())){
                    presenter.click(account.getText().toString(),md5(token.getText().toString()));
                }else {
                    Toast.makeText(getBaseContext(),"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void click() {

    }

    @Override
    public void clicked(RegisterResult result) {
        Log.i("TAG", "clicked: userinfo"+result.getMessage());
    }

    @Override
    public void clickError() {

    }
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
