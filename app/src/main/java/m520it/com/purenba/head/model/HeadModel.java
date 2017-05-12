package m520it.com.purenba.head.model;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public interface HeadModel {
    //加载列表
    void loadNews(String url, int position, String idurl, OnLoadHeadListListener listener);
    //刷新列表
    void sloadNews(int position, String idurl, OnLoadHeadSListListener listener);
    //加载列表详情
    void loadNewsDetail(String docid, OnLoadHeadDetailListener listener);

}
