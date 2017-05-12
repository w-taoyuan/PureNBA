package m520it.com.purenba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import m520it.com.purenba.R;
import m520it.com.purenba.bean.MatchInfoBean;
import m520it.com.purenba.util.OnRecyclerViewItemClickListener;

/**
 * 作者:张弘杰
 */

public class MyAdapter extends RecyclerView.Adapter {
    private static final int TITLE = 0;//篮球,足球,其它
    private static final int NEIRONG = 1;//内容

    List<MatchInfoBean> mData;
    Context c;


    public MyAdapter(List<MatchInfoBean> data, Context context) {
        this.mData = data;
        this.c = context;

    }

    @Override
    public int getItemViewType(int position) {
        String title = mData.get(position).getTitle();
        if (TextUtils.isEmpty(title)) {
            return NEIRONG;
        }
        return TITLE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        //准备Holder

        RecyclerView.ViewHolder holder;
        int itemViewType = getItemViewType(position);
        if (itemViewType == NEIRONG) {
            holder = new MyViewHolder(LayoutInflater.from(
                    viewGroup.getContext()).inflate(R.layout.match_item, viewGroup,
                    false));
            return holder;
        } else if (itemViewType == TITLE) {
            holder = new MyViewHolder(LayoutInflater.from(
                    viewGroup.getContext()).inflate(R.layout.item_title, viewGroup,
                    false));
            return holder;

        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);


        String icon = mData.get(position).getIcon();
        if (itemViewType == NEIRONG) {
            //title为空时就展示 下面类容
            // ((MyViewHolder) holder).mHeadTitle.setVisibility(View.GONE);
            Glide.with(c).load(icon).into(((MyViewHolder) holder).mHeadImage).getRequest();
            //类型
            String name = mData.get(position).getName();
            ((MyViewHolder) holder).mHeadText.setText(name);
            //直播场数
            String matchNum = mData.get(position).getMatchDesc();
            ((MyViewHolder) holder).mHeadText2.setText(matchNum);


        } else if (itemViewType == TITLE) {
            String title = mData.get(position).getTitle();
            ((MyViewHolder) holder).mHeadTitle.setText(title);


        }


    }

    @Override
    public int getItemCount() {
        Log.d("it520it", "getItemCount: " + mData.size());

        return mData != null ? mData.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mHeadTitle;

        private ImageView mHeadImage;
        private TextView mHeadText;
        private TextView mHeadText2;
        private ImageView mHeadImage2;


        public MyViewHolder(View itemView) {
            super(itemView);
            mHeadImage = (ImageView) itemView.findViewById(R.id.head_image);

            mHeadText = (TextView) itemView.findViewById(R.id.head_text);

            mHeadText2 = (TextView) itemView.findViewById(R.id.head_text2);
            mHeadImage2 = (ImageView) itemView.findViewById(R.id.head_image2);

            mHeadTitle = (TextView) itemView.findViewById(R.id.head_title);


        }

    }

    private OnRecyclerViewItemClickListener clickListener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
