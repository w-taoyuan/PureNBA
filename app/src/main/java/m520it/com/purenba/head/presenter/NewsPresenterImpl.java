package m520it.com.purenba.head.presenter;


import java.util.List;

import m520it.com.purenba.head.model.HeadModel;
import m520it.com.purenba.head.model.HeadModelImpl;
import m520it.com.purenba.head.model.OnLoadHeadListListener;
import m520it.com.purenba.head.model.OnLoadHeadSListListener;
import m520it.com.purenba.head.view.NewsView;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public class NewsPresenterImpl implements NewsPresenter, OnLoadHeadListListener, OnLoadHeadSListListener {

    private static final String TAG = "NewsPresenterImpl";

    private NewsView mNewsView;
    private HeadModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView) {
        this.mNewsView = newsView;
        this.mNewsModel = new HeadModelImpl();
    }
    //加载操作
    @Override
    public void loadNews(String getidurl,int position, String url) {
        mNewsModel.loadNews(getidurl,position,url,this);

    }

    //刷新操作
    @Override
    public void sloadNews(int position, String url) {
        mNewsModel.sloadNews(position,url,this);
    }


    @Override
    public void onSuccess(List<String> list) {
        mNewsView.hideProgress();
        mNewsView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }

    @Override
    public void oSnSuccess(List<String> list) {
   //加载成功，
        mNewsView.addNews(list);
    }

    @Override
    public void oSnFailure(String msg, Exception e) {
//显示加载失败
        mNewsView.showfloor();
    }
}
