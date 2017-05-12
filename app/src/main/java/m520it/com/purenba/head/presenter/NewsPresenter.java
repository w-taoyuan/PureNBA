package m520it.com.purenba.head.presenter;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public interface NewsPresenter {
//列表
    void loadNews(String getidurl, int position, String url);
    void sloadNews(int position, String url);

}
