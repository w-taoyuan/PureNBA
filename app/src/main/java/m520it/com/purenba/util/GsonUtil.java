package m520it.com.purenba.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 作者:张弘杰
 */

public class GsonUtil {
    /**
     *
     * @param s 网络请求得到的要解析的字符数据
     * @param classType 要解析到那个bean里面
     * @param <T>
     * @return  一个解析后的Bean数据
     */

    public static <T> T getGson(String s, Class<T> classType){
        if(TextUtils.isEmpty(s) || classType == null) {
            return null;
        }
        Gson gson=new Gson();
        T t = gson.fromJson(s,classType);
        return t;
    }

    /**
     *
     * @param s 网络请求得到的要解析的字符数据
     * @param classType 要解析到那个bean里面
     * @param <T>
     * @return 一个解析后的List<bean>数据
     */

    public static <T> List<T> getList(String s, Class<T> classType){
        Gson gson=new Gson();
        Type type = new TypeToken<List<T>>(){}.getType();
        List<T> t = gson.fromJson(s,type);
        return t;
    }
}
