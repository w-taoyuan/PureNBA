package m520it.com.purenba.head.model;

import java.util.List;

/**
 * Description : 列表加载回调
 * Author : AstroGypsophila
 * Github  : https://github.com/AstroGypsophila
 * Date   : 2016/8/28
 */
public interface OnLoadHeadSListListener {

    void oSnSuccess(List<String> list);

    void oSnFailure(String msg, Exception e);
}
