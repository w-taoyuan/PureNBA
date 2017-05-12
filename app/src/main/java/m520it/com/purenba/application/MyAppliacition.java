package m520it.com.purenba.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 亮 on 2017/4/23.
 */

public class MyAppliacition extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static long    mMainThreadId;

    private Map<String, String> mProtocolCacheMap = new HashMap<>();

    public Map<String, String> getProtocolCacheMap() {
        return mProtocolCacheMap;
    }

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 得到主线程的handler
     */
    public static Handler getHandler() {
        return mHandler;
    }

    /**
     * 得到主线程的线程id
     */
    public static long getMainThreadId() {
        return mMainThreadId;
    }

    @Override
    public void onCreate() {// 程序的入口方法

		/*--------------- 创建应用里面需要用到的一些共有的属性 ---------------*/
        // 1.上下文
        mContext = getApplicationContext();

        // 2.主线程handler
        mHandler = new Handler();

        // 3.主线程的id
        mMainThreadId = android.os.Process.myTid();

        /**
         * Tid: thread
         * Pid: process
         * Uid: user
         */
        super.onCreate();
    }
}
