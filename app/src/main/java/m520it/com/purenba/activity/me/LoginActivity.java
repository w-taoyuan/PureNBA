package m520it.com.purenba.activity.me;

import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;

import org.greenrobot.eventbus.EventBus;

import java.text.CollationElementIterator;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import m520it.com.purenba.R;

import static android.R.id.message;

/**
 * @author Champion
 * @created on: 2017/4/26 14:57
 * @desc ${TODD}  QQ与微信登陆界面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener ,Handler.Callback{

    private static final int MSG_ACTION_CCALLBACK = 1;
    private static final int MSG_TOAST = 2;
    private static final int MSG_CANCEL_NOTIFY = 3;
    private CollationElementIterator mThirdLoginResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Mob平台开始授权
        ShareSDK.initSDK(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.Sina_quick).setOnClickListener(this);
        findViewById(R.id.close_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Sina_quick :  //调用第三方新浪账号登陆
                thirdSinaLogin();
                finish();
                break;

            case R.id.close_btn :
                finish();           //关闭当前的界面
                break;
        }
    }

    /** 新浪微博授权、获取用户信息页面 */
    private void thirdSinaLogin() {
        //初始化新浪平台SinaWeibo.NAME
        Platform sinaWeibo = ShareSDK.getPlatform(LoginActivity.this, SinaWeibo.NAME);
        /*//判断此平台是否授权成功
        if(sinaWeibo.isAuthValid()){
            sinaWeibo.removeAccount(true);
        }*/
        sinaWeibo.SSOSetting(true);
        //设置监听
        sinaWeibo.setPlatformActionListener(LoginActivity.this);
        //获取登陆用户的信息，如果没有授权，会先授权，然后获取用户信息
        sinaWeibo.authorize();
    }

    /** 新浪微博授权成功回调页面 */
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
        /** res是返回的数据，例如showUser(null),返回用户信息，对其解析就行
         *   http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
         *
         */
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg,this);
        Log.i("gs", "action =" + action);

    }
    /** 取消授权 */
    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }
    /** 授权失败 */
    @Override
    public void onError(Platform platform, int action, Throwable t) {
        t.printStackTrace();
        t.getMessage();
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_TOAST: {
                String text = String.valueOf(msg.obj);
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_ACTION_CCALLBACK: {
                switch (msg.arg1) {
                    case 1: {
                        // 成功, successful notification
                        //授权成功后,获取用户信息，要自己解析，看看oncomplete里面的注释
                        //ShareSDK只保存以下这几个通用值
                        Platform pf = ShareSDK.getPlatform(getApplicationContext(),SinaWeibo.NAME);
                        Log.e("sharesdk use_id", pf.getDb().getUserId()); //获取用户id
                        Log.e("sharesdk use_name", pf.getDb().getUserName());//获取用户名称
                        Log.e("sharesdk use_icon", pf.getDb().getUserIcon());//获取用户头像
                        Toast.makeText(getApplicationContext(),"授权成功"+"\n"+"用户id:" +
                                pf.getDb().getUserId() + "\n" + "获取用户名称" + pf.getDb().getUserName() +
                                "\n" + "获取用户头像" + pf.getDb().getUserIcon(),Toast.LENGTH_LONG);
                        //发布消息
                        EventBus.getDefault().post(pf);
//                        mThirdLoginResult.setText("授权成功"+"\n"+"用户id:" + pf.getDb().getUserId() + "\n" + "获取用户名称" + pf.getDb().getUserName() + "\n" + "获取用户头像" + pf.getDb().getUserIcon());
                        //mPf.author()这个方法每一次都会调用授权，出现授权界面
                        //如果要删除授权信息，重新授权
                        //mPf.getDb().removeAccount();
                        //调用后，用户就得重新授权，否则下一次就不用授权
                    }
                    break;
                    case 2: {
                        Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_LONG);
                    }
                    break;
                    case 3: {
                        // 取消, cancel notification
                        Toast.makeText(getApplicationContext(),"取消授权",Toast.LENGTH_LONG);
//                        mThirdLoginResult.setText("取消授权");
                    }
                    break;
                }
            }
            break;
            case MSG_CANCEL_NOTIFY: {
                NotificationManager nm = (NotificationManager) msg.obj;
                if (nm != null) {
                    nm.cancel(msg.arg1);
                }
            }
            break;
        }
        return false;
    }

    //关闭当前的activity
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
