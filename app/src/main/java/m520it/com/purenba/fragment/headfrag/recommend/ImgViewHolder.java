package m520it.com.purenba.fragment.headfrag.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import m520it.com.purenba.R;

/**
 * Created by conan on 2017/4/28.
 */

public class ImgViewHolder extends RecyclerView.ViewHolder {

    private ImageView mTopIvItem;
    private TextView mTopText;

    public ImageView getTopIv() {
        return mTopIvItem;
    }

    public TextView getTopText() {
        return mTopText;
    }

    public ImgViewHolder(View itemView) {
        super(itemView);
        mTopIvItem = (ImageView) itemView.findViewById(R.id.top_iv_item);
        mTopText = (TextView) itemView.findViewById(R.id.top_text);
    }

}
