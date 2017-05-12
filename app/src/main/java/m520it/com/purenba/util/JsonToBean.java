package m520it.com.purenba.util;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Created by 亮 on 2017/4/1.
 */

public class JsonToBean {
    private static Gson mGson = null;
    public static <T>T jsonToBean(String result,Class<T> clazz){
        T bean = null;
        if (mGson==null){
            mGson = new Gson();
        }
        if (!TextUtils.isEmpty(result)){
            bean = mGson.fromJson(result, clazz);
        }
        return bean;
    }
}
