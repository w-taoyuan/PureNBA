package m520it.com.purenba.head;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import m520it.com.purenba.R;
import m520it.com.purenba.head.view.OnRecyclerViewItemClickListener;
import m520it.com.purenba.util.LogUtils;
import m520it.com.purenba.util.UIUtils;

import static m520it.com.purenba.R.id.nba_head_img2;
import static m520it.com.purenba.R.id.view;

/**
 * Created by 亮 on 2017/4/25.
 */

public class HeadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int FRUE = 3;
    private Context mContext;
    private List<String> mData;
    private boolean mShowFooter = true;
    private int ONE = 0;
    private int TWO = 1;
    private int SWEE = 2;
    private int FIVE = 4;
    public HeadAdapter(Context context) {
        this.mContext = context;
    }

    public void setmDate(List<String> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        String str = mData.get(position);
        JSONObject jsonObject = null;
        int o = 0;
        try {
            jsonObject = new JSONObject(str);
            String type = jsonObject.getString("type");
            if (type.equals("18")) {
                o = ONE;
            } else if (type.equals("80")) {
                o = TWO;
            } else if (type.equals("11")) {
                o = SWEE;
            } else if (type.equals("20")) {
                o = FRUE;
            } else
                o = FIVE;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return o;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == ONE) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.nbahead, parent, false);
            holder = new ItemViewHolder(view);
        } else if (viewType == TWO) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.nba_item2, parent, false);
            holder = new ItemViewHolder(view);
        } else if (viewType == SWEE) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.nba_ggao, parent, false);
            holder = new ItemViewHolder(view);
        } else if (viewType == FRUE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.kong, null);
            holder = new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.kong, null);
            holder = new ItemViewHolder(view);
        }
        return holder;
    }

    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener clickListener) {
        this.mOnRecyclerViewItemClickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerViewItemClickListener != null) {
                    mOnRecyclerViewItemClickListener.onItemClick(v, position);
                }
            }
        });
        String str = mData.get(position);
        int itemViewType = getItemViewType(position);

        try {
            JSONObject jsonObject = new JSONObject(str);
            String type = jsonObject.getString("type");
            Log.e("nihao", type);
            switch (type) {
                case "80":
                    JSONObject info = jsonObject.getJSONObject("info");
                    String title = info.getString("title");


                    ((ItemViewHolder) holder).mTitle.setText(title);

                    String tag_key = info.getString("tag_key");
                    ((ItemViewHolder) holder).mHead_item_tv2.setText(tag_key);
                    String commentsNum = info.getString("commentsNum");
                    ((ItemViewHolder) holder).mHead_item_tv3.setText(commentsNum + "评");
                    String imgurl = info.getString("imgurl");
                    Log.e("nihao", imgurl);

                    String atype = info.getString("atype");
                    if ("23".equals(atype)) {
                        ((ItemViewHolder) holder).mSpicel_text.setText("视频集");

                    } else {
                        ((ItemViewHolder) holder).mSpicel_text.setVisibility(View.GONE);
                        ((ItemViewHolder) holder).mView.setVisibility(View.GONE);
                    }
                    Picasso.with(UIUtils.getContext()).load(imgurl).into(((ItemViewHolder) holder).mImge);


                    break;
                case "11":
                    JSONObject info2 = jsonObject.getJSONObject("info");
                    String pic = info2.getString("pic");
                    Picasso.with(UIUtils.getContext()).load(pic).into(((ItemViewHolder) holder).mImgee);
                    break;
                case "18":
                    JSONObject info3 = jsonObject.getJSONObject("info");
                    JSONArray matches = info3.getJSONArray("matches");

                    JSONObject matchInfo = matches.getJSONObject(0).getJSONObject("matchInfo");
                    String quarter = matchInfo.getString("quarter");//第几节
                    String startTime = matchInfo.getString("startTime");//开始时间
                    String leftGoal = matchInfo.getString("leftGoal");
                    String rightGoal = matchInfo.getString("rightGoal");
                    if (!quarter.equals("")){
                        ((ItemViewHolder) holder).mNnba_head_text4.setText(quarter);
                        ((ItemViewHolder) holder).mNnba_head_text4.setTextColor(Color.WHITE);
                        ((ItemViewHolder) holder).mNnba_head_text4.setBackground(UIUtils.getContext().getResources().getDrawable(R.drawable.backgrand_red));
                        ((ItemViewHolder) holder).mNnba_head_text3.setText(leftGoal+":"+rightGoal);
                        ((ItemViewHolder) holder).mNnba_head_text3.setTextSize(18);
                        ((ItemViewHolder) holder).mNnba_head_text3.setTextColor(Color.RED);
                    }else{
                        String substring = startTime.substring(11, 16);
                        ((ItemViewHolder) holder).mNnba_head_text3.setText("明"+substring);
                    }

                    JSONObject matchInfo2 = matches.getJSONObject(1).getJSONObject("matchInfo");

                    String quarter1 = matchInfo2.getString("quarter");//第几节
                    String startTime1 = matchInfo2.getString("startTime");//开始时间
                    String leftGoal1 = matchInfo2.getString("leftGoal");
                    String rightGoal1 = matchInfo2.getString("rightGoal");
                    if (!quarter1.equals("")){
                        ((ItemViewHolder) holder).mNnba_headr_text4.setText(quarter1);
                        ((ItemViewHolder) holder).mNnba_headr_text4.setTextColor(Color.WHITE);
                        ((ItemViewHolder) holder).mNnba_headr_text4.setBackground(UIUtils.getContext().getResources().getDrawable(R.drawable.backgrand_red));
                        ((ItemViewHolder) holder).mNnba_headr_text3.setText(leftGoal1+":"+rightGoal1);
                        ((ItemViewHolder) holder).mNnba_headr_text3.setTextSize(18);
                    }else{
                        String substring1 = startTime1.substring(11, 16);
                        ((ItemViewHolder) holder).mNnba_headr_text3.setText("明"+substring1);
                    }

                    String leftBadge = matchInfo.getString("leftBadge");
                    LogUtils.e("wocao", leftBadge);
                    Picasso.with(UIUtils.getContext()).load(leftBadge).into(((ItemViewHolder) holder).mNba_head_img1);

                    String rightBadge = matchInfo.getString("rightBadge");
                    Picasso.with(UIUtils.getContext()).load(rightBadge).into(((ItemViewHolder) holder).mNba_head_img2);

                    String leftBadge2 = matchInfo2.getString("leftBadge");
                    Picasso.with(UIUtils.getContext()).load(leftBadge2).into(((ItemViewHolder) holder).mNnba_headr_img1);

                    String rightBadge2 = matchInfo2.getString("rightBadge");
                    Picasso.with(UIUtils.getContext()).load(rightBadge2).into(((ItemViewHolder) holder).mNnba_headr_img2);

                    String leftName = matchInfo.getString("leftName");
                    String leftScore = matchInfo.getString("leftScore");
                    ((ItemViewHolder) holder).mNba_head_textlift.setText(leftName + "(" + leftScore + ")");

                    String rightName = matchInfo.getString("rightName");
                    String rightScore = matchInfo.getString("rightScore");
                    ((ItemViewHolder) holder).mTextright.setText(rightName + "(" + rightScore + ")");

                    String leftName1 = matchInfo2.getString("leftName");
                    String leftScore1 = matchInfo2.getString("leftScore");
                    ((ItemViewHolder) holder).mNnba_headr_textlift.setText(leftName1 + "(" + leftScore1 + ")");

                    String rightName1 = matchInfo2.getString("rightName");
                    String rightScore1 = matchInfo2.getString("rightScore");
                    ((ItemViewHolder) holder).mNnba_headr_textright.setText(rightName1 + "(" + rightScore1 + ")");
                    break;
                case "20":
                    int oldPosition = holder.getOldPosition();
                    LogUtils.e("nimei", oldPosition + "");
                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
//        int begin = mShowFooter ? 1 : 0;
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;
        private final TextView mHead_item_tv2;
        private final TextView mHead_item_tv3;
        private final TextView mTextright;
        private final ImageView mImge;
        private final ImageView mNba_head_img1;
        private final ImageView mNba_head_img2;
        private final TextView mNba_head_textlift;
        private final ImageView mNnba_headr_img1;
        private final TextView mNnba_headr_textlift;
        private final ImageView mNnba_headr_img2;
        private final TextView mNnba_headr_textright;
        private final RelativeLayout mRe;
        private final ImageView mImgee;
        private final TextView mSpicel_text;
        private final View mView;
        private final TextView mNnba_head_text3;
        private final TextView mNnba_head_text4;
        private final TextView mNnba_headr_text3;
        private final TextView mNnba_headr_text4;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.title);
            mHead_item_tv2 = (TextView) v.findViewById(R.id.head_item_tv2);//tag_key
            mHead_item_tv3 = (TextView) v.findViewById(R.id.head_item_tv3);//commentsNum
            mTextright = (TextView) v.findViewById(R.id.nba_head_textright);//commentsNum
            mNba_head_textlift = (TextView) v.findViewById(R.id.nba_head_textlift);
            mNnba_headr_textlift = (TextView) v.findViewById(R.id.nba_headr_textlift);
            mNnba_headr_textright = (TextView) v.findViewById(R.id.nba_headr_textright);
            mNnba_head_text3 = (TextView) v.findViewById(R.id.nba_head_text3);
            mNnba_head_text4 = (TextView) v.findViewById(R.id.nba_head_text4);
            mNnba_headr_text3 = (TextView) v.findViewById(R.id.nba_headr_text3);
            mNnba_headr_text4 = (TextView) v.findViewById(R.id.nba_headr_text4);
            mSpicel_text = (TextView) v.findViewById(R.id.spicel_text);
            mView = (View) v.findViewById(view);
            mImge = (ImageView) v.findViewById(R.id.imge);

            mImgee = (ImageView) v.findViewById(R.id.imgee);
            mRe = (RelativeLayout) v.findViewById(R.id.re);
            mNba_head_img1 = (ImageView) v.findViewById(R.id.nba_head_img1);
            mNnba_headr_img1 = (ImageView) v.findViewById(R.id.nba_headr_img1);
            mNnba_headr_img2 = (ImageView) v.findViewById(R.id.nba_headr_img2);
            mNba_head_img2 = (ImageView) v.findViewById(nba_head_img2);
        }

    }

}
