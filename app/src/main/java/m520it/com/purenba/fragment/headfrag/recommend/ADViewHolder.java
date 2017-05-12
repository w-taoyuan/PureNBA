package m520it.com.purenba.fragment.headfrag.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import m520it.com.purenba.R;

/**
 * Created by conan on 2017/4/28.
 */

public class ADViewHolder extends RecyclerView.ViewHolder {

    private ImageView mRecommendAdIv;

    public ImageView getRecommendAdIv() {
        return mRecommendAdIv;
    }

    public ADViewHolder(View itemView) {
        super(itemView);
        mRecommendAdIv = (ImageView) itemView.findViewById(R.id.recommend_ad_iv);
    }
}
