package m520it.com.purenba.fragment.headfrag.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import m520it.com.purenba.R;
import m520it.com.purenba.base.BaseFragment;
import m520it.com.purenba.config.Contan;
import m520it.com.purenba.util.JsonYuanUitl;
import m520it.com.purenba.util.OkHttpUtils;
import m520it.com.purenba.util.StringUtils;

/**
 * Created by 亮 on 2017/4/25.
 */
public class TJFragment extends BaseFragment {

    private PullLoadMoreRecyclerView recommendRecyclerView;
    private ImageView emptyNetworkIv;
    private OkHttpUtils mOkHttpUtils;

    private ArrayList<String> mAllIds;

    private int mCurrentPage = 0;
    private TJAdapter mTjAdapter;
    private ArrayList<NormalNewsInfoBean> mNormalNewsInfoBeans;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater, container);
        initDatas();
        initEvent();
        return view;
    }

    private void initEvent() {
        recommendRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                initDatas();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @NonNull
    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_recomend_layout, container, false);
        recommendRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.recommend_recycler_view);
        recommendRecyclerView.setLinearLayout();
        recommendRecyclerView.addItemDecoration(new MyDecoration(getContext(),MyDecoration.VERTICAL_LIST));
        mTjAdapter = new TJAdapter(getContext());
        recommendRecyclerView.setAdapter(mTjAdapter);
        emptyNetworkIv = (ImageView) view.findViewById(R.id.empty_network_iv);
        return view;
    }

    @Override
    public void onDestroy() {
        //将初始页面页码重置
        mCurrentPage = 0;
        mOkHttpUtils = null;
        super.onDestroy();
    }

    private void initDatas() {
        if (mOkHttpUtils == null) {
            mOkHttpUtils = OkHttpUtils.getInstance(getContext());
        }
        if (mAllIds == null) {
            mAllIds = new ArrayList<>();
        }
        requestIndexData();
    }

    private void requestIndexData() {
        mOkHttpUtils.requestGETStringResult(Contan.URL_RECOMMEND_INDEX,
                new OkHttpUtils.OnHttpGetStringResultListener() {
                    @Override
                    public void onFail(IOException e) {
                        recommendRecyclerView.setVisibility(View.INVISIBLE);
                        emptyNetworkIv.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess(String result) {
                        mAllIds = JsonYuanUitl.getIndexId(result);
                        mTjAdapter.setIds(mAllIds);
                        String listUrl = Contan.URL_RECOMMEND_LIST +
                                StringUtils.getIds(mAllIds, mCurrentPage) + Contan.DEVICE_INFO;
                        requestListData(listUrl);
                    }
                });
    }

    private void requestListData(String listUrl) {
        Log.v("conan", "TJFragment---requestListData--- " + Contan.URL_RECOMMEND_INDEX);
        Log.v("conan", "TJFragment---requestListData--- " + listUrl);
        mOkHttpUtils.requestGETStringResult(listUrl, new OkHttpUtils.OnHttpGetStringResultListener() {
            @Override
            public void onFail(IOException e) {
                recommendRecyclerView.setVisibility(View.INVISIBLE);
                emptyNetworkIv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(String result) {
                //解析列表详情页面
                parseJson(result);
                recommendRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }

    private void parseJson(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            mNormalNewsInfoBeans = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                String id = mAllIds.get(i);
                if(id.startsWith("10__")) {
                    continue;
                }
                JSONObject object = dataObject.getJSONObject(id);
                if (id.startsWith("12")) {
                    //获取顶部 item 的数据
                    getTopIvData(object);
                } else if (id.startsWith("21")) {
                    //获取中间轮播的比赛信息
                    getBannerMatchesInfo(object);
                } else if (id.startsWith("11")) {
                    //获取广告信息
                    getAdUrl(object);
                } else if (id.startsWith("80") || id.startsWith("19")) {
                    //获取其他普通item的信息
                    getNormalItemInfos(object);
                } else if (id.startsWith("17")) {
                    //获取热门比赛信息
                    getHotMatchInfos(object);
                }
            }
            mTjAdapter.setNormalNewsInfoBeen(mNormalNewsInfoBeans);
            mTjAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getTopIvData(JSONObject dataObject) throws JSONException {
        String imgUrl = dataObject.getJSONObject("info").getString("pic");
        String title = dataObject.getJSONObject("info").getString("title");
        TopImageBean topImageBean = new TopImageBean();
        topImageBean.setImgUrl(imgUrl);
        topImageBean.setTitle(title);
        mTjAdapter.setTopImageBean(topImageBean);
    }

    public void getBannerMatchesInfo(JSONObject dataObject) {
        try {
            JSONArray matchInfos = dataObject.getJSONObject("info").getJSONArray("matches");
            ArrayList<BannerMatchBean> bannerMatchBeans = new ArrayList<>();
            for (int i = 0; i < matchInfos.length(); i++) {
                JSONObject matchInfo = matchInfos.getJSONObject(i).getJSONObject("matchInfo");
                BannerMatchBean bannerMatchBean = new BannerMatchBean();
                bannerMatchBean.setMatchType(matchInfo.getString("matchType"));
                bannerMatchBean.setLeftName(matchInfo.getString("leftName"));
                bannerMatchBean.setRightName(matchInfo.getString("rightName"));
                bannerMatchBean.setLeftBadge(matchInfo.getString("leftBadge"));
                bannerMatchBean.setRightBadge(matchInfo.getString("rightBadge"));
                bannerMatchBean.setStartTime(matchInfo.getString("startTime"));
                bannerMatchBean.setMatchDesc(matchInfo.getString("matchDesc"));
                bannerMatchBean.setLeftGoal(matchInfo.getString("leftGoal"));
                bannerMatchBean.setRightGoal(matchInfo.getString("rightGoal"));
                bannerMatchBean.setQuarter(matchInfo.getString("quarter"));
                bannerMatchBean.setQuarterTime(matchInfo.getString("quarterTime"));
                bannerMatchBeans.add(bannerMatchBean);
            }
            mTjAdapter.setBannerMatchBeans(bannerMatchBeans);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getAdUrl(JSONObject dataObject) throws JSONException {
        String adImgUrl = dataObject.getJSONObject("info")
                .getString("pic");
        mTjAdapter.setAdPicUrl(adImgUrl);
    }

    private void getNormalItemInfos(JSONObject dataObject) {
        try {
            JSONObject normalNewsInfo = dataObject.getJSONObject("info");
            NormalNewsInfoBean normalNewsInfoBean = new NormalNewsInfoBean();
            normalNewsInfoBean.setCommentsNum(normalNewsInfo.optString("commentsNum"));
            normalNewsInfoBean.setImgurl(normalNewsInfo.optString("imgurl"));
            normalNewsInfoBean.setAtype(normalNewsInfo.optString("atype"));
            normalNewsInfoBean.setTag_key(normalNewsInfo.optString("tag_key"));
            normalNewsInfoBean.setTitle(normalNewsInfo.optString("title"));
            normalNewsInfoBean.setNewsAppId(normalNewsInfo.optString("newsAppId"));
            mNormalNewsInfoBeans.add(normalNewsInfoBean);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("conan", "TJFragment---getNormalItemInfos 出异常了");
        }
    }

    private void getHotMatchInfos(JSONObject object) {
        try {
            JSONObject marqueeObject = object.getJSONObject("info").getJSONArray("marquee").getJSONObject(0);
            HotMatchBean hotMatchBean = new HotMatchBean();
            hotMatchBean.setTitle(marqueeObject.getString("title"));
            hotMatchBean.setSecondTitle(marqueeObject.getString("secondTitle"));
            mTjAdapter.setHotMatchBean(hotMatchBean);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
