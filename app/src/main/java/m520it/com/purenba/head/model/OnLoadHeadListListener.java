package m520it.com.purenba.head.model;

import java.util.List;

/**
 * Description : 列表加载回调
 * Author : AstroGypsophila
 * Github  : https://github.com/AstroGypsophila
 * Date   : 2016/8/28
 */
public interface OnLoadHeadListListener {

    void onSuccess(List<String> list);

    void onFailure(String msg, Exception e);
}
