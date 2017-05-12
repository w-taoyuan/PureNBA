package m520it.com.purenba.head.model;

import java.io.IOException;
import java.util.ArrayList;

import m520it.com.purenba.util.JsonYuanUitl;
import m520it.com.purenba.util.OkHttpUtils;
import m520it.com.purenba.util.UIUtils;

/**
 * Description : 新闻业务处理类
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class HeadModelImpl implements HeadModel {
    ArrayList<String> allList = null;

    /*加载列表*/
    @Override
    public void loadNews(final String url, final int position, final String idurl, final OnLoadHeadListListener listener) {
        OkHttpUtils.getInstance(UIUtils.getContext()).requestGETStringResult(url, new OkHttpUtils.OnHttpGetStringResultListener() {
            @Override
            public void onFail(IOException e) {
                listener.onFailure("load news list failure", e);
            }

            //id列表
            @Override
            public void onSuccess(String result) {
                ArrayList<String> list = JsonYuanUitl.getId(result, "list");
                allList = list;
                JsonYuanUitl.getItemdate(position, list, idurl, new JsonYuanUitl.OnHttpGetArrayListResultListener() {
                    @Override
                    public void onFailre(IOException e) {
                        listener.onFailure("加载失败", e);
                    }

                    @Override
                    public void onSuccess(ArrayList<String> result) {
                        listener.onSuccess(result);
                    }
                });

            }
        });
    }

//上拉刷新
    @Override
    public void sloadNews(int position, String idurl, final OnLoadHeadSListListener listener) {
        JsonYuanUitl.getItemdate(position, allList, idurl, new JsonYuanUitl.OnHttpGetArrayListResultListener() {
            @Override
            public void onFailre(IOException e) {
                listener.oSnFailure("加载失败", e);
            }

            @Override
            public void onSuccess(ArrayList<String> result) {
                listener.oSnSuccess(result);
            }
        });
    }

    /*加载列表详情*/
    @Override
    public void loadNewsDetail(String docid, OnLoadHeadDetailListener listener) {

    }
}
