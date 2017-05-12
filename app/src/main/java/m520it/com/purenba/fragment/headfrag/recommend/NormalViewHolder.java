package m520it.com.purenba.fragment.headfrag.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import m520it.com.purenba.R;

/**
 * Created by conan on 2017/4/28.
 */

public class NormalViewHolder extends RecyclerView.ViewHolder {

    private TextView mNormalItemTitle;
    private TextView mSpecialTypeText;
    private TextView mVerticalDivideLine;
    private TextView mTabKeyTitle;
    private TextView mCommentNumText;
    private ImageView mNormalItemIv;

    public TextView getNormalItemTitle() {
        return mNormalItemTitle;
    }

    public TextView getSpecialTypeText() {
        return mSpecialTypeText;
    }

    public TextView getVerticalDivideLine() {
        return mVerticalDivideLine;
    }

    public TextView getTabKeyTitle() {
        return mTabKeyTitle;
    }

    public TextView getCommentNumText() {
        return mCommentNumText;
    }

    public ImageView getNormalItemIv() {
        return mNormalItemIv;
    }

    public NormalViewHolder(View itemView) {
        super(itemView);
        mNormalItemTitle = (TextView) itemView.findViewById(R.id.normal_item_title);
        mSpecialTypeText = (TextView) itemView.findViewById(R.id.special_type_text);
        mVerticalDivideLine = (TextView) itemView.findViewById(R.id.vertical_divide_line);
        mTabKeyTitle = (TextView) itemView.findViewById(R.id.tab_key_title);
        mCommentNumText = (TextView) itemView.findViewById(R.id.comment_num_text);
        mNormalItemIv = (ImageView) itemView.findViewById(R.id.normal_item_iv);
    }
}
