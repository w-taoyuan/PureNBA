package m520it.com.purenba.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;

import m520it.com.purenba.R;
import m520it.com.purenba.activity.channel.ChannelActivity;
import m520it.com.purenba.activity.channel.ChannelBean;
import m520it.com.purenba.event.TabHostEvent;
import m520it.com.purenba.fragment.FandFragment;
import m520it.com.purenba.fragment.HeadFragment;
import m520it.com.purenba.fragment.MeFragment;
import m520it.com.purenba.fragment.ScheduleFragment;
import m520it.com.purenba.view.TestFragmentTabHost;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ChannelBean> mChannels;
    private ArrayList<ChannelBean> mConcernChannels;
    private ArrayList<ChannelBean> mNoConcernChannels;

    private GoogleApiClient mClient;

    public ArrayList<ChannelBean> getConcernChannels() {
        return mConcernChannels;
    }

    public ArrayList<ChannelBean> getNoConcernChannels() {
        return mNoConcernChannels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        if(Build.VERSION.SDK_INT>=19){
        //            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
        //                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //            ImageView iv_status_bar = (ImageView) findViewById(R.id.iv_status_bar);
        //            iv_status_bar.getLayoutParams().height = TitleUtil.getStatusHeight(getApplicationContext());
        //            iv_status_bar.setBackgroundColor(getResources().getColor(R.color.colorMain));
        //        }
        //需要接受EventBus事件,必须要先注册一下
        EventBus.getDefault().register(this);
        initView();
        initDatas();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initDatas() {
        if (mChannels == null) {
            mChannels = new ArrayList<>();
            mChannels.add(new ChannelBean("NBA", R.drawable.liansai_logo_nba));
            mChannels.add(new ChannelBean("中超", R.drawable.liansai_logo_zhongchao));
            mChannels.add(new ChannelBean("英超", R.drawable.liansai_logo_yingchao));
            mChannels.add(new ChannelBean("西甲", R.drawable.liansai_logo_xijia));
            mChannels.add(new ChannelBean("欧冠", R.drawable.liansai_logo_ouguan));
            mChannels.add(new ChannelBean("亚冠", R.drawable.liansai_logo_yaguan));
            mChannels.add(new ChannelBean("意甲", R.drawable.liansai_logo_yijia));
            mChannels.add(new ChannelBean("德甲", R.drawable.liansai_logo_dejia));
            mChannels.add(new ChannelBean("法甲", R.drawable.liansai_logo_fajia));
            mChannels.add(new ChannelBean("NHL", R.drawable.liansai_logo_nhl));
            mChannels.add(new ChannelBean("CBA", R.drawable.liansai_logo_cba));
            mChannels.add(new ChannelBean("NCAA", R.drawable.liansai_logo_ncaa));
            mChannels.add(new ChannelBean("综合", R.drawable.liansai_logo_zonghe));
            mChannels.add(new ChannelBean("NFL", R.drawable.liansai_logo_nfl));
            mChannels.add(new ChannelBean("电竞", R.drawable.liansai_logo_dianjing));
            mChannels.add(new ChannelBean("世预赛南美区", R.drawable.liansai_logo_shijiebei));
            mChannels.add(new ChannelBean("世预赛亚洲区", R.drawable.liansai_logo_shijiebei));
            mChannels.add(new ChannelBean("世预赛欧洲区", R.drawable.liansai_logo_shijiebei));
            mChannels.add(new ChannelBean("小梦战报", R.drawable.liansai_logo_footballnews));
        }
        if (mConcernChannels == null) {
            mConcernChannels = new ArrayList<>();
            mConcernChannels.addAll(mChannels);
        }
        if (mNoConcernChannels == null) {
            mNoConcernChannels = new ArrayList<>();
        }
    }

    private TestFragmentTabHost mTabHost;

    private void initView() {
        mTabHost = (TestFragmentTabHost) findViewById(R.id.tabHost);
        //优点:mTabHost可以去动态的替换Fragment
        //        getSupportFragmentManager()
        mTabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.frameLayout);
        //需要Fragment    需要tab的文字(或者view)    容器ViewGroup的id
        String[] tabTitles = new String[]{"首页", "赛程", "发现", "我的"};
        Class[] fragmentClasses = new Class[]{HeadFragment.class, ScheduleFragment.class,
                FandFragment.class, MeFragment.class};
        int[] resIds = new int[]{R.drawable.tab_head,
                R.drawable.tab_va, R.drawable.tab_topic,
                R.drawable.tab_my};
        for (int i = 0; i < tabTitles.length; i++) {
            TabHost.TabSpec tab = mTabHost.newTabSpec(i + "");
            View view = View.inflate(getApplicationContext(), R.layout.item_tab, null);
            TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);
            ImageView iv_tab = (ImageView) view.findViewById(R.id.iv_tab);
            tv_tab.setText(tabTitles[i]);
            iv_tab.setImageResource(resIds[i]);
            tab.setIndicator(view);
            mTabHost.addTab(tab, fragmentClasses[i], null);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //可以考虑在这里做网络请求等获取数据的操作

            }
        });
        //        mTabHost.setCurrentTabByTag();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeChannel(Bundle bundle) {
        int action = bundle.getInt(ChannelActivity.ACTION);
        switch (action) {
            case ChannelActivity.ADD_ACTION:
                mNoConcernChannels.remove(bundle.getInt("position"));
                mConcernChannels.add((ChannelBean) bundle.getSerializable("bean"));
                break;
            case ChannelActivity.DELETE_ACTION:
                mConcernChannels.remove(bundle.getInt("position"));
                mNoConcernChannels.add((ChannelBean) bundle.getSerializable("bean"));
                break;
        }
        HeadFragment headFragment = (HeadFragment) getSupportFragmentManager().findFragmentByTag("0");
        headFragment.getMyPagerAdapter().setChannelBean(mConcernChannels);
        headFragment.getMyPagerAdapter().notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChannelIndexChange(String changeIndexs) {
        String[] split = changeIndexs.split("\\*");
        int fromIndex = Integer.parseInt(split[0]);
        int toIndex = Integer.parseInt(split[1]);
        if (fromIndex < toIndex) {
            for (int i = fromIndex; i < toIndex; i++) {
                Collections.swap(mConcernChannels, i, i + 1);
            }
        } else {
            for (int i = fromIndex; i > toIndex; i--) {
                Collections.swap(mConcernChannels, i, i - 1);
            }
        }
        HeadFragment headFragment = (HeadFragment) getSupportFragmentManager().findFragmentByTag("0");
        headFragment.getMyPagerAdapter().setChannelBean(mConcernChannels);
        headFragment.getMyPagerAdapter().notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showOrHideTabHost(TabHostEvent event) {
        if (event.isShow) {
            mTabHost.setVisibility(View.VISIBLE);
        } else {
            mTabHost.setVisibility(View.GONE);
        }
    }


}
