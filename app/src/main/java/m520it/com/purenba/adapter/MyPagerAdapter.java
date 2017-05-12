package m520it.com.purenba.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import m520it.com.purenba.activity.channel.ChannelBean;
import m520it.com.purenba.fragmentfactory.FragFactroy;

/**
 * Created by 亮 on 2017/4/25.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<ChannelBean> mChannelBeans;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setChannelBean(ArrayList<ChannelBean> beans) {
        mChannelBeans = beans;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return FragFactroy.createFragment(position, "");
        }
        return FragFactroy.createFragment(position, mChannelBeans.get(position - 1).getTitle());
    }

    @Override
    public int getCount() {
        return mChannelBeans == null ? 1 : mChannelBeans.size() + 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "推荐";
        }
        return mChannelBeans.get(position - 1).getTitle();
    }
}
