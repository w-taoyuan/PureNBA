package m520it.com.purenba.util;

import java.util.Map;

/**
 * 创建者     王维波
 * 创建时间   2016/6/2
 * 更新描述   ${TODO}
 */
public class HttpUtils {
    /**
     * 传递get参数对应的map集合,返回拼接之后的字符串信息
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
}
