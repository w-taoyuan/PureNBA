package m520it.com.purenba.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {
    private static final String SPNAME = "cache";

    /**
     * 获取Sp
     * @param context
     * @return
     */
    public  static SharedPreferences getSp(Context context){
        SharedPreferences sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        return sp;
    }

     //向Sp中存boolean值
   public static void setSpBoolean(Context context, String key, boolean flag){
       SharedPreferences sp = getSp(context);
       SharedPreferences.Editor edit = sp.edit();
       edit.putBoolean(key,flag).apply();
   }
    //得到Sp中存的boolean值
    public static boolean getSpBoolean(Context context, String key){
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key,false);
    }
    //获得保持在Sp中的String
    public static String getSpString(Context context, String key) {
        SharedPreferences sp = getSp(context);
        return sp.getString(key,"");
    }
    //保持一个String到sp
    public static void setSpString(Context context, String key, String value){
        SharedPreferences sp = getSp(context);
        sp.edit().putString(key,value).apply();
    }

    //获得保持在Sp中的String
    public static int getSpInt(Context context, String key) {
        SharedPreferences sp = getSp(context);
        return sp.getInt(key,0);
    }
    //保持一个String到sp
    public static void setSpInt(Context context, String key, int value){
        SharedPreferences sp = getSp(context);
        sp.edit().putInt(key,value).apply();
    }

    public static long getSpLong(Context context, String key) {
        SharedPreferences sp = getSp(context);
        return sp.getLong(key,0);
    }

    //保持一个String到sp
    public static void setSpLong(Context context, String key, long value){
        SharedPreferences sp = getSp(context);
        sp.edit().putLong(key,value).apply();
    }

}