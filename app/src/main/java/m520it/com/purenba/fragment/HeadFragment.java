package m520it.com.purenba.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;


import m520it.com.purenba.R;
import m520it.com.purenba.activity.MainActivity;
import m520it.com.purenba.activity.channel.ChannelActivity;
import m520it.com.purenba.adapter.MyPagerAdapter;
import m520it.com.purenba.base.BaseFragment;

/**
 * Created by 亮 on 2017/4/24.
 */

public class HeadFragment extends BaseFragment {

    private ImageView addSub;
    public static final String CONCERN_CHANNELS_TAG = "concern_channels_tag";
    public static final String NO_CONCERN_CHANNELS_TAG = "no_concern_channels_tag";

    private MyPagerAdapter mMyPagerAdapter;
    private MainActivity mMainActivity;

    public MyPagerAdapter getMyPagerAdapter() {
        return mMyPagerAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //注册EventBus监听频道的变化
//        org.greenrobot.eventbus.EventBus.getDefault().register(this);

        //获取布局对象并设置数据
        View view = initView(inflater, container);
        return view;
    }

    @NonNull
    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.head_frag, container, false);
        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        if (addSub == null) {
            addSub = (ImageView) view.findViewById(R.id.add_sub);
        }
        mMainActivity = (MainActivity) getActivity();
        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mMainActivity, ChannelActivity.class);
                intent.putExtra(CONCERN_CHANNELS_TAG, mMainActivity.getConcernChannels());
                intent.putExtra(NO_CONCERN_CHANNELS_TAG, mMainActivity.getNoConcernChannels());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.target_enter, R.anim.origin_out);
            }
        });
        mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mMyPagerAdapter.setChannelBean(mMainActivity.getConcernChannels());
        pager.setAdapter(mMyPagerAdapter);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册EventBus监听
//        org.greenrobot.eventbus.EventBus.getDefault().unregister(this);
    }

}
