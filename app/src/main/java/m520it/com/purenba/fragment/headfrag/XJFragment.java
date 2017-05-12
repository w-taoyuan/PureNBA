package m520it.com.purenba.fragment.headfrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import m520it.com.purenba.R;
import m520it.com.purenba.activity.NBADetailActivity;
import m520it.com.purenba.base.BaseFragment;
import m520it.com.purenba.config.Contan;
import m520it.com.purenba.head.HeadAdapter;
import m520it.com.purenba.head.presenter.NewsPresenterImpl;
import m520it.com.purenba.head.view.NewsView;
import m520it.com.purenba.head.view.OnRecyclerViewItemClickListener;
import m520it.com.purenba.util.LogUtils;
import m520it.com.purenba.util.UIUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by 亮 on 2017/4/25.
 */

public class XJFragment extends BaseFragment implements NewsView {


    private ImageView mDefelut;
    private RelativeLayout mReflash;
    private RecyclerView mRecyclerView;
    private NewsPresenterImpl mNewsPresenter;
    private HeadAdapter mAdapter;
    private ArrayList<String> mData;
    private int pageIndex = 0;
    private LinearLayoutManager mLayoutManager;
    private PullToRefreshView mPullToRefreshView;
    private int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenterImpl(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(UIUtils.getContext(), R.layout.headfragment, null);
        mNewsPresenter.loadNews(Contan.URL_XJ_ID, 0, Contan.URL_XJ_LIST);
        mDefelut = (ImageView) view.findViewById(R.id.defelut_img);
        mReflash = (RelativeLayout) view.findViewById(R.id.reflash);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.head_recyc);
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        mNewsPresenter.loadNews(Contan.URL_NBA_ID, 0, Contan.URL_NBA_LIST);
                    }
                }, 2000);
            }
        });
        mLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setItemAnimator(new ScaleInLeftAnimator());

//        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                .color(Color.RED)
//                .size(8)
//                .build());
        mAdapter = new HeadAdapter(UIUtils.getContext());
        mAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (mData.size() <= 0) {
                    return;
                }
                if (position != 0) {
                    Intent intent = new Intent(getActivity(), NBADetailActivity.class);
                    String str = mData.get(position);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(str);
                        String type = jsonObject.getString("type");
                        JSONObject info = jsonObject.getJSONObject("info");

                        if (type.equals("80")){
                            String newsAppId = info.getString("newsAppId");
                            intent.putExtra("newAppId",newsAppId);
                            startActivity(intent );
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                }

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
//        mRecyclerView.addOnI
        return view;
    }

    //加载更多代码
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                LogUtils.d(TAG, "loading more data");
                mNewsPresenter.sloadNews(index, Contan.URL_NBA_LIST);
            }
        }
    };


    @Override
    public void showProgress() {
        mReflash.setVisibility(View.VISIBLE);
    }

    @Override
    public void addNews(List<String> newsList) {
        mAdapter.isShowFooter(true);
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(newsList);

        mAdapter.setmDate(mData);

        //如果没有更多数据了,则隐藏footer布局
        if (newsList == null || newsList.size() == 0) {
            mAdapter.isShowFooter(true);
        }
        mAdapter.notifyDataSetChanged();
        index+=20;

    }

    @Override
    public void hideProgress() {
        mReflash.setVisibility(View.GONE);
    }

    @Override
    public void showLoadFailMsg() {
        mDefelut.setVisibility(View.VISIBLE);
        mDefelut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsPresenter.loadNews(Contan.URL_NBA_ID, 0, Contan.URL_NBA_LIST);
            }
        });
        mReflash.setVisibility(View.GONE);
    }

    @Override
    public void showfloor() {
        mAdapter.isShowFooter(true);
    }
}
