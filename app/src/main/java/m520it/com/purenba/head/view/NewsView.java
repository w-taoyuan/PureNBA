package m520it.com.purenba.head.view;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public interface NewsView {

    void showProgress();

    void addNews(List<String> newsList);

    void hideProgress();

    void showLoadFailMsg();
    void showfloor();
}
