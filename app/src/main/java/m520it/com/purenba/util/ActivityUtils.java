package m520it.com.purenba.util;

import android.app.Activity;
import android.content.Intent;

import m520it.com.purenba.R;

/**
 * Created by conan on 2017/4/26.
 */

public class ActivityUtils {

    public static void startActivity(Activity activity,Class clz){
        Intent intent=new Intent(activity,clz);
        activity.startActivity(intent);
        //第一个参数为启动时动画效果，第二个参数为退出时动画效果
        activity.overridePendingTransition(R.anim.target_enter, R.anim.origin_out);
    }

    public static void finishActivity(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.origin_enter,R.anim.target_out);
    }

}
