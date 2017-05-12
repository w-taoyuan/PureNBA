package m520it.com.purenba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import m520it.com.purenba.R;
import m520it.com.purenba.bean.ListinfoBean;
import m520it.com.purenba.config.Contan;


/**
 * 作者:张弘杰
 */

public class AllFragment extends Fragment {

    private View mView;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.all_fragment, container, false);
        initView();
        /**
         * 网络请求数据
         */
        initData(Contan.ALL_URL);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * 初始化控件
         */

    }


    private void initView() {

        mListView = (ListView) mView.findViewById(R.id.all_listView);

    }

    private void initData(String allUrl) {
        HttpUtils2.getInstance(getContext()).doGet(allUrl, new HttpUtils2.OnRespnseListerner() {
        //失败回调
        @Override
        public void Faiure() {
            Log.i("hahhahahahh", "失败");

        }

        //成功回调  已经在主线程
        @Override
        public void Success(String s) {
            Log.i("hahhahahahh", s + "");
            try {
                JSONObject json = new JSONObject(s);
                JSONObject data = json.getJSONObject("data");

                String curMatchDay = data.getString("curMatchDay");
                String lastMatchDay = data.getString("lastMatchDay");
                String nextMatchDay = data.getString("nextMatchDay");

                //Log.d("hahhahahahh", "curMatchDay="+curMatchDay+"lastMatchDay="+lastMatchDay);
                JSONObject matches = data.getJSONObject("matches");

                JSONObject s1 = matches.getJSONObject("100000");
                JSONObject s2 = matches.getJSONObject("100002");
                JSONObject s3 = matches.getJSONObject("100006");
                JSONObject s4 = matches.getJSONObject("129");
                JSONObject s5 = matches.getJSONObject("208");
                JSONObject s6 = matches.getJSONObject("23");
                JSONObject s7 = matches.getJSONObject("8");

                String title1 = s1.getString("title");
                String title2 = s2.getString("title");
                String title3 = s3.getString("title");
                String title4 = s4.getString("title");
                String title5 = s5.getString("title");
                String title6 = s6.getString("title");
                String title7 = s7.getString("title");
                //  Log.i("hahhahahahh",title1+title2+title3+title4+title5+title6+title7);

                //                    int length = s1.length();

                //创建一个以为数组 这就是结果
                ArrayList<ListinfoBean> result = new ArrayList<>();

                ListinfoBean listinfoBean = new ListinfoBean();
                listinfoBean.setCurMatchDay(curMatchDay);
                listinfoBean.setLastMatchDay(lastMatchDay);
                listinfoBean.setNextMatchDay(nextMatchDay);
                result.add(listinfoBean);
                listinfoBean.setTitle(title1);
                //添加一个标题bean

                listinfoBean.setTitle(title1);
                listinfoBean.setTitle(title2);
                listinfoBean.setTitle(title3);
                listinfoBean.setTitle(title4);
                listinfoBean.setTitle(title5);
                listinfoBean.setTitle(title6);
                listinfoBean.setTitle(title7);

                //添加普通的item
                Gson gson = new Gson();
                ArrayList<ListinfoBean> items1 = gson.fromJson(s1.getString("list"), new TypeToken<ArrayList<ListinfoBean>>() {
                }.getType());
                ArrayList<ListinfoBean> items2 = gson.fromJson(s2.getString("list"), new TypeToken<ArrayList<ListinfoBean>>() {
                }.getType());
                ArrayList<ListinfoBean> items3 = gson.fromJson(s3.getString("list"), new TypeToken<ArrayList<ListinfoBean>>() {
                }.getType());
                ArrayList<ListinfoBean> items4 = gson.fromJson(s4.getString("list"), new TypeToken<ArrayList<ListinfoBean>>() {
                }.getType());
                ArrayList<ListinfoBean> items5 = gson.fromJson(s5.getString("list"), new TypeToken<ArrayList<ListinfoBean>>() {
                }.getType());
                ArrayList<ListinfoBean> items6 = gson.fromJson(s6.getString("list"), new TypeToken<ArrayList<ListinfoBean>>() {
                }.getType());
                ArrayList<ListinfoBean> items7 = gson.fromJson(s7.getString("list"), new TypeToken<ArrayList<ListinfoBean>>() {
                }.getType());
                result.addAll(items1);
                result.addAll(items2);
                result.addAll(items3);
                result.addAll(items4);
                result.addAll(items5);
                result.addAll(items6);
                result.addAll(items7);
                Log.i("hahhahahahh", "result:========= " + result.toString() + "----------" + result.size());

                AllAdapter myAdapter = new AllAdapter(getActivity(), result);
                mListView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    });
}

        }

