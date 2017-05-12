package m520it.com.purenba.head.presenter;

import android.content.Context;

import m520it.com.purenba.head.model.HeadModel;
import m520it.com.purenba.head.model.HeadModelImpl;
import m520it.com.purenba.head.model.OnLoadHeadDetailListener;
import m520it.com.purenba.head.view.NewsDetailView;


/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/21
 */
public class NewsDetailPresenterImpl implements HeadDetailPresenter, OnLoadHeadDetailListener {

    private Context mContent;
    private NewsDetailView mNewsDetailView;
    private HeadModel mNewsModel;

    public NewsDetailPresenterImpl(Context mContent, NewsDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new HeadModelImpl();
    }


    @Override
    public void onSuccess(Object newsDetailBean) {

    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }

    @Override
    public void loadNewsDetail(String docId) {

    }
}
