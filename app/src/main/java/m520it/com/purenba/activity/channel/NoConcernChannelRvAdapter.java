package m520it.com.purenba.activity.channel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import m520it.com.purenba.R;


/**
 * Created by conan on 2017/4/25.
 */

public class NoConcernChannelRvAdapter extends RvAdapter {

    private ImageView noLiansaiLogo;
    private TextView noLiansaiName;

    public NoConcernChannelRvAdapter(List<ChannelBean> datas) {
        super(datas);
    }

    @Override
    protected void setViewData(View itemView, ChannelBean bean, int position) {
        noLiansaiLogo = (ImageView) itemView.findViewById(R.id.no_liansai_logo);
        noLiansaiName = (TextView) itemView.findViewById(R.id.no_liansai_name);
        noLiansaiLogo.setImageResource(bean.getImgId());
        noLiansaiName.setText(bean.getTitle());
    }

    @Override
    public int getViewId() {
        return R.layout.no_concern_channel_item;
    }
}
