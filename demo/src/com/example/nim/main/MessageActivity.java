package com.example.nim.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nim.MyCache;
import com.example.nim.R;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;
import java.util.List;

public class MessageActivity extends ActionBarActivity implements View.OnClickListener{
    // view
    private Button textBtn;
    private Button imageBtn;
    private TextView text;
    private ImageView image;
    private Button logoutBtn;
    private Button p2pBtn;

    // data
    private String receiverid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        setTitle(MyCache.getAccount());
        findViews();
        initData();
        setListener();
        NIMClient.getService(MsgServiceObserve.class)
                .observeReceiveMessage(incomingMessageObserver, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NIMClient.getService(MsgServiceObserve.class)
                .observeReceiveMessage(incomingMessageObserver, false);
    }

    Observer<List<IMMessage>> incomingMessageObserver =
            new Observer<List<IMMessage>>() {
                @Override
                public void onEvent(List<IMMessage> messages) {
                    // 处理新收到的消息，为了上传处理方便，SDK保证参数messages全部来自同一个聊天对象。
                    for (IMMessage message : messages) {
                        if (message.getMsgType() == MsgTypeEnum.image) {
                            setImage(message);
                        } else {
                            text.setText(message.getContent());
                        }
                    }
                }
            };

    private void findViews() {
        textBtn = (Button) findViewById(R.id.text_btn);
        imageBtn = (Button) findViewById(R.id.image_btn);
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
        logoutBtn = (Button) findViewById(R.id.logout);
        p2pBtn = (Button) findViewById(R.id.P2P);
    }

    private void initData() {
        receiverid = MyCache.getAccount().equals("tjc") ? "cqh" : "tjc";
    }

    private void setListener() {
        textBtn.setOnClickListener(this);
        imageBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        p2pBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_btn:
                sendText();
                break;
            case R.id.image_btn:
                sendImage();
                break;
            case R.id.logout:
                logout();
                break;
            case R.id.P2P:
                startP2PSession();
                break;
            default:
                break;
        }
    }

    /**
     * 发送文本按钮响应事件
     */
    private void sendText() {
        String content = receiverid.equals("tjc") ? "Hello Han tjc!" : "Hello Li cqh!";
        // 创建文本消息
        IMMessage message = MessageBuilder.createTextMessage(
                receiverid, // 聊天对象的ID，如果是单聊，为用户账号，如果是群聊，为群组ID
                SessionTypeEnum.P2P, // 聊天类型，单聊或群组
                content // 文本内容
        );
        NIMClient.getService(MsgService.class).sendMessage(message, false);
    }

    /**
     * 发送图片按钮响应事件
     */
    private void sendImage() {
        // 为了方便起见，将文件路径直接硬编码了，开发者请勿模仿。
        File file = new File("/sdcard/Download/test_image.jpg");
        // 创建图片消息
        IMMessage message = MessageBuilder.createImageMessage(
                receiverid, // 聊天对象的ID，如果是单聊，为用户账号，如果是群聊，为群组ID
                SessionTypeEnum.P2P, // 聊天类型，单聊或群组
                file, // 图片文件对象
                null // 文件显示名字，如果第三方APP不关注，可以为null
        );
        NIMClient.getService(MsgService.class).sendMessage(message, false);
    }

    /**
     * 注销按钮响应事件
     */
    private void logout() {
        NIMClient.getService(AuthService.class).logout();
        MyCache.clear();
        startActivity(new Intent(MessageActivity.this, LoginActivity.class));
    }

    private void setImage(IMMessage message) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        String path = ((ImageAttachment) message.getAttachment()).getThumbPath();
        if(path == null) return;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        image.setImageBitmap(bitmap);
    }

    /**
     * 调用uikit p2p会话接口
     */
    private void startP2PSession() {
        NimUIKit.startChatting(this, receiverid, SessionTypeEnum.P2P, null);
    }
}
