package m520it.com.purenba.util;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description : OkHttp网络连接封装工具类
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/17
 */
public class OkHttpUtils {
        private static OkHttpClient sOkHttpClient;
        private final Handler mHandler;

        private OkHttpUtils(Context context){
            sOkHttpClient = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    //...
                    .build();
            mHandler = new Handler(context.getMainLooper());
        }

        private static volatile OkHttpUtils sHttpHelper;

    public static OkHttpUtils getInstance(Context context){
        if(sHttpHelper==null){
            synchronized (OkHttpUtils.class){
                sHttpHelper = new OkHttpUtils(context);
            }
        }
        return sHttpHelper;
    }
    public interface OnHttpGetStringResultListener {
        void onFail(IOException e);
        void onSuccess(String result);
    }

    public void requestGETStringResult(String url, final OnHttpGetStringResultListener listener){
//        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        sOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.e(getClass().getSimpleName() +" xmg", "onFailure: "+"错误:"+e.getMessage());
                //回调
//                监听器的失败方法
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String result = response.body().string();
                    //....解析数据
//                    监听器的解析数据方法
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess(result);
                        }
                    });

                }else{
                    onFailure(call,new IOException("联网错误"));
                }
            }
        });
    }

}
