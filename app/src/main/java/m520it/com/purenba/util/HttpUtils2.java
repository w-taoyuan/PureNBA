package m520it.com.purenba.util;

import android.content.Context;
import android.os.Handler;


import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;



/**
 * 作者:张弘杰
 */

public class HttpUtils2 {

    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private final Handler mHandler;

    private HttpUtils2(Context context){
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(context.getMainLooper());
    }

    private static HttpUtils2 utils;

    public static HttpUtils2 getInstance(Context context){
        if(utils == null) {
            synchronized (HttpUtils2.class){
                utils = new HttpUtils2(context);
            }
        }
        return utils;
    }

   public void doGet(String url, final OnRespnseListerner listerner){
        mRequest = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listerner.Faiure();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String s =  response.body().string();
                /**
                 * 把数据回调到主线程
                 */
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listerner.Success(s);
                    }
                });

            }


        });
    }


    public interface OnRespnseListerner{
        void Faiure();
        void Success(String s);
    }
}
