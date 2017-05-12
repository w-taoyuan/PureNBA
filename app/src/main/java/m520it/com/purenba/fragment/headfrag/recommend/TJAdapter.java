package m520it.com.purenba.fragment.headfrag.recommend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;
import m520it.com.purenba.R;
import m520it.com.purenba.activity.NBADetailActivity;

/**
 * Created by conan on 2017/4/27.
 */

public class TJAdapter extends RecyclerView.Adapter {

    private static final int TYPE_TOP_IMG = 0;
    private static final int TYPE_BANNER_MATCHES = 1;
    private static final int TYPE_AD = 2;
    private static final int TYPE_NORMAL = 3;
    private static final int TYPE_HOT_MATCH = 4;

    public TJAdapter(Context context) {
        mContext = context;
    }

    private Context mContext;

    private TopImageBean mTopImageBean;

    private HotMatchBean mHotMatchBean;

    private ArrayList<String> mIds;

    private ArrayList<BannerMatchBean> mBannerMatchBeans;

    private String mAdPicUrl;

    private ArrayList<NormalNewsInfoBean> mNormalNewsInfoBeen;

    public void setTopImageBean(TopImageBean topImageBean) {
        mTopImageBean = topImageBean;
    }

    public void setHotMatchBean(HotMatchBean hotMatchBean) {
        mHotMatchBean = hotMatchBean;
    }

    public void setIds(ArrayList<String> ids) {
        mIds = ids;
    }

    public void setBannerMatchBeans(ArrayList<BannerMatchBean> bannerMatchBeans) {
        mBannerMatchBeans = bannerMatchBeans;
    }

    public void setAdPicUrl(String adPicUrl) {
        mAdPicUrl = adPicUrl;
    }

    public void setNormalNewsInfoBeen(ArrayList<NormalNewsInfoBean> normalNewsInfoBeen) {
        mNormalNewsInfoBeen = normalNewsInfoBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType) {
            case TYPE_TOP_IMG:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_top_iv_item
                        , parent, false);
                viewHolder = new ImgViewHolder(view);
                break;
            case TYPE_BANNER_MATCHES:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_banner_match_item
                        , parent, false);
                viewHolder = new BannerMatchViewHolder(view, mBannerMatchBeans, mContext);
                break;
            case TYPE_HOT_MATCH:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_hot_match_item
                        , parent, false);
                viewHolder = new HotMatchViewHolder(view);
                break;
            case TYPE_AD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_ad_iv_item
                        , parent, false);
                viewHolder = new ADViewHolder(view);
                break;
            case TYPE_NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_normal_item
                        , parent, false);
                viewHolder = new NormalViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TOP_IMG:
//                Picasso.with(mContext).load(mTopImageBean.getImgUrl())
//                        .into(((ImgViewHolder) holder).getTopIv());
//                ((ImgViewHolder) holder).getTopText().setText(mTopImageBean.getTitle());
                break;
            case TYPE_BANNER_MATCHES:
//                ViewFlipper flipper = ((BannerMatchViewHolder) holder).getRecommendBannerMatchFlipper();
//                ArrayList<BannerMatchViewHolder.InnerHolder> innerHolders = ((BannerMatchViewHolder) holder).getInnerHolders();
//                for (int i = 0; i < mBannerMatchBeans.size(); i++) {
//                    BannerMatchBean bannerMatchBean = mBannerMatchBeans.get(i);
//                    BannerMatchViewHolder.InnerHolder innerHolder = innerHolders.get(i);
//
//                    Picasso.with(mContext).load(bannerMatchBean.getLeftBadge()).into(innerHolder.leftTeamLogo);
//                    Picasso.with(mContext).load(bannerMatchBean.getRightBadge()).into(innerHolder.rightTimeLogo);
//                    innerHolder.leftTimeName.setText(bannerMatchBean.getLeftName());
//                    innerHolder.rightTimeName.setText(bannerMatchBean.getRightName());
//
//                    String matchType = bannerMatchBean.getMatchType();
//                    if ("4".equals(matchType)) {
//                        //正在进行的比赛
//                        innerHolder.livingMatchInfo.setVisibility(View.VISIBLE);
//                        innerHolder.notBeginMatchInfo.setVisibility(View.INVISIBLE);
//                        innerHolder.QuarterTv.setText(bannerMatchBean.getQuarter());
//                        innerHolder.scoreTv.setText(bannerMatchBean.getLeftGoal() + " : " +
//                                bannerMatchBean.getRightGoal());
//                    } else if ("1".equals(matchType)) {
//                        //还未开始的比赛
//                        innerHolder.livingMatchInfo.setVisibility(View.INVISIBLE);
//                        innerHolder.notBeginMatchInfo.setVisibility(View.VISIBLE);
//                        innerHolder.matchDescTv.setText(bannerMatchBean.getMatchDesc());
//                        innerHolder.startTimeTv.setText(bannerMatchBean.getStartTime());
//                    }
//                }
//                flipper.startFlipping();
                break;
            case TYPE_HOT_MATCH:
                ((HotMatchViewHolder) holder).getHotMatchDate().setText(mHotMatchBean.getTitle());
                ((HotMatchViewHolder) holder).getHotMatchTitle().setText(mHotMatchBean.getSecondTitle());
                break;
            case TYPE_AD:
                Picasso.with(mContext).load(mAdPicUrl).into(((ADViewHolder) holder).getRecommendAdIv());
                break;
            case TYPE_NORMAL:
                final NormalNewsInfoBean normalNewsInfoBean;
                if (position < 5) {
                    normalNewsInfoBean = mNormalNewsInfoBeen.get(position - 3);
                } else {
                    normalNewsInfoBean = mNormalNewsInfoBeen.get(position - 5);
                }
                //设置标题
                ((NormalViewHolder) holder).getNormalItemTitle()
                        .setText(normalNewsInfoBean.getTitle());
                //设置特殊类型的文字
                setSpecialText((NormalViewHolder) holder, position, normalNewsInfoBean);
                ((NormalViewHolder) holder).getCommentNumText()
                        .setText(normalNewsInfoBean.getCommentsNum() + "评");
                Picasso.with(mContext).load(normalNewsInfoBean.getImgurl())
                        .into(((NormalViewHolder) holder).getNormalItemIv());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, NBADetailActivity.class);
                        intent.putExtra("newsAppId",normalNewsInfoBean.getNewsAppId());
                        mContext.startActivity(intent);
                    }
                });
                break;
        }
    }

    private void setSpecialText(NormalViewHolder holder, int position, NormalNewsInfoBean normalNewsInfoBean) {
        TextView specialTypeText = holder.getSpecialTypeText();
        String atype = normalNewsInfoBean.getAtype();
        specialTypeText.setVisibility(View.VISIBLE);
        holder.getVerticalDivideLine().setVisibility(View.VISIBLE);
        if (mIds.get(position).startsWith("19")) {
            specialTypeText.setText("竞彩");
        } else if (mIds.get(position).startsWith("80")) {
            if ("11".equals(atype)) {
                specialTypeText.setText("专题");
            } else if ("23".equals(atype)) {
                specialTypeText.setText("视频集");
            }
        } else {
            specialTypeText.setVisibility(View.GONE);
            holder.getVerticalDivideLine().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_TOP_IMG;
            case 1:
                return TYPE_BANNER_MATCHES;
            case 2:
                return TYPE_HOT_MATCH;
            case 5:
                return TYPE_AD;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        int count = 1;
        if (mTopImageBean != null)
            count++;
        if (mHotMatchBean != null)
            count++;
        if (!TextUtils.isEmpty(mAdPicUrl))
            count++;
        if (mBannerMatchBeans != null)
            count++;
        if (mNormalNewsInfoBeen != null)
            count += mNormalNewsInfoBeen.size();
        return count;
    }
}
