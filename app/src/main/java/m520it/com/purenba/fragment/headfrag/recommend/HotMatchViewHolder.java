package m520it.com.purenba.fragment.headfrag.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import m520it.com.purenba.R;

/**
 * Created by conan on 2017/4/28.
 */

public class HotMatchViewHolder extends RecyclerView.ViewHolder {

    private TextView mHotMatchDate;
    private TextView mHotMatchTitle;
    private ImageView mJumpIntoHotMatchIv;

    public TextView getHotMatchDate() {
        return mHotMatchDate;
    }

    public TextView getHotMatchTitle() {
        return mHotMatchTitle;
    }

    public ImageView getJumpIntoHotMatchIv() {
        return mJumpIntoHotMatchIv;
    }

    public HotMatchViewHolder(View itemView) {
        super(itemView);
        mHotMatchDate = (TextView) itemView.findViewById(R.id.hot_match_date);
        mHotMatchTitle = (TextView) itemView.findViewById(R.id.hot_match_title);
        mJumpIntoHotMatchIv = (ImageView) itemView.findViewById(R.id.jump_into_hot_match_iv);
    }
}
