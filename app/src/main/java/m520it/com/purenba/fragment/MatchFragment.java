package m520it.com.purenba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import m520it.com.purenba.R;
import m520it.com.purenba.adapter.MyAdapter;
import m520it.com.purenba.bean.MatchInfoBean;
import m520it.com.purenba.config.Contan;
import m520it.com.purenba.util.HttpUtils2;

import static android.content.ContentValues.TAG;

/**
 * 作者:张弘杰
 */

public class MatchFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.match_fragment, container, false);
        return mView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * 初始化控件
         */
        initView();
        /**
         * 网络请求数据
         */
        initData(Contan.MATCH_URL);

        /**
         * 下拉刷新
         */
        initRefresh();
    }

    private void initView() {

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mManager = new LinearLayoutManager(getContext());
       mRecyclerView.setLayoutManager(mManager);


    }

    private void initData(String matchUrl) {
        HttpUtils2.getInstance(getContext()).doGet(matchUrl, new HttpUtils2.OnRespnseListerner() {
            //失败回调
            @Override
            public void Faiure() {

            }

            //成功回调  已经在主线程
            @Override
            public void Success(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    //String dataJson = jsonObject.getString("data");
                    //Log.d(TAG, "Success: "+dataJson);
                    JSONArray dataJsonArr = jsonObject.getJSONArray("data");
                    int dataJsonSize = dataJsonArr.length();
                    //创建一个以为数组 这就是结果
                    ArrayList<MatchInfoBean> result = new ArrayList<MatchInfoBean>();

                    for (int i = 0; i < dataJsonSize; i++) {
                        JSONObject dataJsonObject = (JSONObject) dataJsonArr.get(i);
                        //添加一个标题bean
                        MatchInfoBean titleBean = new MatchInfoBean();
                        titleBean.setTitle(dataJsonObject.getString("title"));
                        result.add(titleBean);
                        //添加普通的item
                        Gson gson = new Gson();
                        ArrayList<MatchInfoBean> items = gson.fromJson(dataJsonObject.getString("columns"), new TypeToken<ArrayList<MatchInfoBean>>() {
                        }.getType());
                        result.addAll(items);
                    }


                    List<MatchInfoBean> datas=new ArrayList<MatchInfoBean>();
                    MyAdapter adapter = new MyAdapter(datas,getContext());


                    for (int i = 0; i < result.size(); i++) {
                        MatchInfoBean matchInfoBean = result.get(i);
                        datas.add(matchInfoBean);
                     mRecyclerView.setAdapter(adapter);
                       // Log.d(TAG, "Success: " + result.get(i));
                    }
                    Log.d(TAG, "Success: "+datas.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void initRefresh() {
    }
}
