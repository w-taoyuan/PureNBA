package m520it.com.purenba.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import m520it.com.purenba.R;
import m520it.com.purenba.bean.ListinfoBean;
import m520it.com.purenba.bean.MatchBean;

/**
 * 作者:张弘杰
 */

public class AllAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<ListinfoBean> mDatas;

    public AllAdapter(Context context, ArrayList<ListinfoBean> result) {
        this.mContext = context;
        this.mDatas = result;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    class ViewHolder {
        private TextView heng_image1;
        private TextView heng_image2;

        private TextView allHead;
        private TextView headText2;

        private ImageView headImage;
        private TextView itemText1;
        private ImageView headIv1;
        private TextView itemText2;
        private TextView time;
        private TextView timeText;
        private ImageView leadIcon;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mholder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.all_item2, null);
            mholder = new ViewHolder();
                /*得到各个控件的对象*/
            mholder.allHead = (TextView) convertView.findViewById(R.id.all_head);
            mholder.headText2 = (TextView) convertView.findViewById(R.id.head_text2);
            //固定图片

            mholder.headImage = (ImageView) convertView.findViewById(R.id.head_image);
            mholder.itemText1 = (TextView) convertView.findViewById(R.id.item_text1);
            mholder.headIv1 = (ImageView) convertView.findViewById(R.id.head_iv1);
            mholder.itemText2 = (TextView) convertView.findViewById(R.id.item_text2);
            mholder.time = (TextView) convertView.findViewById(R.id.time);
            mholder.timeText = (TextView) convertView.findViewById(R.id.time_text);
            mholder.leadIcon = (ImageView) convertView.findViewById(R.id.lead_icon);

            mholder.heng_image1 = (TextView) convertView.findViewById(R.id.heng_image1);
            mholder.heng_image2 = (TextView) convertView.findViewById(R.id.heng_image2);

            convertView.setTag(mholder); //绑定ViewHolder对象
        } else {
            mholder = (ViewHolder) convertView.getTag(); //取出ViewHolder对象
        }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        ListinfoBean dataBean = mDatas.get(position);

        //类型  英超
        String title = dataBean.getTitle();
        mholder.allHead.setText(title);

        //腾讯体育
        String liveSource = dataBean.getLiveSource();
        mholder.headText2.setText(liveSource);


        MatchBean matchInfo = dataBean.getMatchInfo();
        if (matchInfo != null) {

        //上面图片地址
        String leftBadge = matchInfo.getLeftBadge();

            Glide.with(mContext).load(leftBadge).into(mholder.headImage).getRequest();
            //左球队名字
            String leftName = matchInfo.getLeftName();
            mholder.itemText1.setText(leftName);

            //下面图片地址
        String rightBadge = matchInfo.getRightBadge();

            Glide.with(mContext).load(rightBadge).into(mholder.headIv1).getRequest();
            //右球队名字
            String rightName = matchInfo.getRightName();
            mholder.itemText2.setText(rightName);
            //比赛时间

            String startTime = matchInfo.getStartTime();
            String s1 = startTime.substring(10,startTime.length());

            mholder.time.setText(s1);

            //第多少轮
            String matchDesc = matchInfo.getMatchDesc();
            mholder.timeText.setText(matchDesc);

            //比分
            String leftGoal = matchInfo.getLeftGoal();
            mholder.heng_image1.setText(leftGoal);
            String rightGoal = matchInfo.getRightGoal();
            mholder.heng_image2.setText(rightGoal);

            //小图标
            String rightBadge1 = matchInfo.getRightBadge();
            Glide.with(mContext).load(rightBadge1).into( mholder.leadIcon).getRequest();

        }

        return convertView;

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
