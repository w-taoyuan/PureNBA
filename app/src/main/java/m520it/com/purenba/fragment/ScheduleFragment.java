package m520it.com.purenba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import m520it.com.purenba.R;
import m520it.com.purenba.base.BaseFragment;

/**
 * Created by 亮 on 2017/4/24.
 */

public class ScheduleFragment extends BaseFragment implements View.OnClickListener {
    private TextView mMatchTv;  //赛事
    private TextView mAllTv;    //所有
    private TextView mFollow;   //关注
    ViewPager mViewPager;       //页面容器的ViewPager
    ImageView imageviewOvertab; //下面


    List<Fragment> fragmentList; //页面集合


    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab = -1;

    private MatchFragment mMatchFragment;
    private AllFragment mAllFragment;
    private FollowFragment mFollowFragment;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        /**
         *找到控制viewPager文本
         */
        mMatchTv = (TextView) mView.findViewById(R.id.match_tv);
        mAllTv = (TextView) mView.findViewById(R.id.all_tv);
        mFollow = (TextView) mView.findViewById(R.id.follow_tv);
        mMatchTv.setTextColor(getContext().getResources().getColor(R.color.bg_news_text_selector));

        mViewPager = (ViewPager) mView.findViewById(R.id.home_viewpager);

        /**
         *设置监听
         */

        mMatchTv.setOnClickListener(this);
        mAllTv.setOnClickListener(this);
        mFollow.setOnClickListener(this);

        mMatchFragment = new MatchFragment();
        mAllFragment = new AllFragment();
        mFollowFragment = new FollowFragment();
        /**
         * 装fragment的集合
         */
        fragmentList = new ArrayList<>();
        fragmentList.add(mMatchFragment);
        fragmentList.add(mAllFragment);
        fragmentList.add(mFollowFragment);


        screenWidth = getResources().getDisplayMetrics().widthPixels;

        //  mMatchTv.measure(0, 0);
        imageviewOvertab = (ImageView) mView.findViewById(R.id.imgv_overtab);
        //  RelativeLayout.LayoutParams imageParams=new RelativeLayout.LayoutParams(screenWidth/4, mMatchTv.getMeasuredHeight());
        //    imageParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //   imageviewOvertab.setLayoutParams(imageParams);
        MyFrageStatePagerAdapter adapter = new MyFrageStatePagerAdapter(getFragmentManager());

        mViewPager.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {

        mMatchTv.setTextColor(getContext().getResources().getColor(R.color.colordefault));
        mAllTv.setTextColor(getContext().getResources().getColor(R.color.colordefault));
        mFollow.setTextColor(getContext().getResources().getColor(R.color.colordefault));
        switch (v.getId()) {
            case R.id.match_tv:
                mMatchTv.setTextColor(getContext().getResources().getColor(R.color.bg_news_text_selector));
                changeView(0);
                break;
            case R.id.all_tv:
               // mAllTv.setTextColor(Color.BLUE);
                mAllTv.setTextColor(getContext().getResources().getColor(R.color.bg_news_text_selector));
                changeView(1);
                break;
            case R.id.follow_tv:
               // mFollow.setTextColor(Color.BLUE);
                mFollow.setTextColor(getContext().getResources().getColor(R.color.bg_news_text_selector));
                changeView(2);
                break;

        }
    }

    //手动设置ViewPager要显示的视图
    private void changeView(int desTab) {
        mViewPager.setCurrentItem(desTab, true);
    }

    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //            获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == currenttab) {
                return;
            }
             imageMove(mViewPager.getCurrentItem());
            currenttab = mViewPager.getCurrentItem();
        }


        /**
         * 移动覆盖层
         *
         * @param moveToTab 目标Tab，也就是要移动到的导航选项按钮的位置
         *                  第一个导航按钮对应0，第二个对应1，以此类推
         */
        private void imageMove(int moveToTab) {
            int startPosition = 0;
            int movetoPosition = 0;

            startPosition = currenttab * (screenWidth / 4);
            movetoPosition = moveToTab * (screenWidth / 4);
            //平移动画
            TranslateAnimation translateAnimation = new TranslateAnimation(startPosition, movetoPosition, 0, 0);
            translateAnimation.setFillAfter(true);
            translateAnimation.setDuration(200);
            imageviewOvertab.startAnimation(translateAnimation);
        }
    }
}