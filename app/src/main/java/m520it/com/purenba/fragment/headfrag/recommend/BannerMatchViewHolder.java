package m520it.com.purenba.fragment.headfrag.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import m520it.com.purenba.R;

/**
 * Created by conan on 2017/4/28.
 */

public class BannerMatchViewHolder extends RecyclerView.ViewHolder {

    private ArrayList<InnerHolder> mInnerHolders;

    public ArrayList<InnerHolder> getInnerHolders() {
        return mInnerHolders;
    }

    private ViewFlipper mRecommendBannerMatchFlipper;

    public ViewFlipper getRecommendBannerMatchFlipper() {
        return mRecommendBannerMatchFlipper;
    }

    private ArrayList<BannerMatchBean> mBannerMatchBeans;
    private Context mContext;

    class InnerHolder {
        public ImageView leftTeamLogo;
        public TextView leftTimeName;
        public LinearLayout livingMatchInfo;
        public TextView QuarterTv;
        public TextView scoreTv;
        public LinearLayout notBeginMatchInfo;
        public TextView matchDescTv;
        public TextView startTimeTv;
        public ImageView rightTimeLogo;
        public TextView rightTimeName;
    }

    public BannerMatchViewHolder(View itemView, ArrayList<BannerMatchBean> bannerMatchBeans, Context context) {
        super(itemView);
//        mBannerMatchBeans = bannerMatchBeans;
//        mContext = context;
//
//        mRecommendBannerMatchFlipper = (ViewFlipper) itemView.findViewById(R.id.recommend_banner_match_flipper);
//        for (int i = 0; i < mBannerMatchBeans.size(); i++) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.recommend_banner_match_info_container, null, false);
//            InnerHolder innerHolder = new InnerHolder();
//
//            innerHolder.leftTeamLogo = (ImageView) view.findViewById(R.id.left_team_logo);
//            innerHolder.leftTimeName = (TextView) view.findViewById(R.id.left_team_name);
//            innerHolder.livingMatchInfo = (LinearLayout) view.findViewById(R.id.living_match_des_ll);
//            innerHolder.QuarterTv = (TextView) view.findViewById(R.id.quarter_text);
//            innerHolder.scoreTv = (TextView) view.findViewById(R.id.score_text);
//            innerHolder.notBeginMatchInfo = (LinearLayout) view.findViewById(R.id.not_begin_match_des_ll);
//            innerHolder.matchDescTv = (TextView) view.findViewById(R.id.match_desc_text);
//            innerHolder.startTimeTv = (TextView) view.findViewById(R.id.start_time_text);
//            innerHolder.rightTimeLogo = (ImageView) view.findViewById(R.id.right_team_logo);
//            innerHolder.rightTimeName = (TextView) view.findViewById(R.id.right_team_name);
//
//            mInnerHolders.add(innerHolder);
//
//            mRecommendBannerMatchFlipper.addView(view);
//        }
    }

}
