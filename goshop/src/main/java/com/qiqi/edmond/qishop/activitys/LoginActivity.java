package com.qiqi.edmond.qishop.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.qiqi.edmond.qishop.MyCache;
import com.qiqi.edmond.qishop.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 启豪 on 2017/5/9.
 */

public class LoginActivity extends ActionBarActivity {
    private EditText accountEdit;
    private EditText pswEdit;
    private Button loginBtn;

    private TextView registertextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NIMClient.init(getApplicationContext(), null, null);
        setContentView(R.layout.login_activity);
        findViews();
    }
    private void findViews() {
        accountEdit = (EditText) findViewById(R.id.account_edit);
        pswEdit = (EditText) findViewById(R.id.token_edit);
        loginBtn = (Button) findViewById(R.id.login);
        registertextview= (TextView) findViewById(R.id.register);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        registertextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void login() {
        LoginInfo info = new LoginInfo(accountEdit.getText().toString().toLowerCase(), md5(pswEdit.getText().toString())); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {
                        MyCache.setAccount(accountEdit.getText().toString().toLowerCase());
                        NimUIKit.setAccount(accountEdit.getText().toString().toLowerCase());
                        Log.i("id", "onSuccess: "+loginInfo.getAccount());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(int i) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                    // overwrite methods
                };
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);
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
