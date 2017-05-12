package m520it.com.purenba.head.model;

/**
 * Description : 列表详情加载回调
 * Author : AstroGypsophila
 * Github  : https://github.com/AstroGypsophila
 * Date   : 2016/8/28
 */
public interface OnLoadHeadDetailListener{

    void onSuccess(Object newsDetailBean);

    void onFailure(String msg, Exception e);
}
