package m520it.com.purenba.util;

import android.content.Context;

/**
 * @author 小码哥Android
 * @time 2017/3/10  17:18
 * @desc ${TODD}
 */
public class TitleUtil {
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
