package m520it.com.purenba.activity.channel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import m520it.com.purenba.R;

/**
 * Created by conan on 2017/4/25.
 */

public class ConcernChannelRvAdapter extends RvAdapter {

    private static final long LONG_CLICK_TIME = 500;
    private ImageView liansaiLogo;
    private TextView liansaiName;

    public ConcernChannelRvAdapter(List<ChannelBean> datas) {
        super(datas);
    }

    @Override
    protected void setViewData(View itemView, ChannelBean bean, int position) {
        liansaiLogo = (ImageView) itemView.findViewById(R.id.liansai_logo);
        liansaiName = (TextView) itemView.findViewById(R.id.liansai_name);
        liansaiLogo.setImageResource(bean.getImgId());
        liansaiName.setText(bean.getTitle());
//        itemView.setOnTouchListener(new View.OnTouchListener() {
//
//            private float mStartY;
//            private float mStartX;
//            private long mTimeOffset;
//            private long mEndTime;
//            private long mStartTime;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int itemWidth = v.getWidth();
//                int itemHeight = v.getHeight();
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        mStartTime = System.currentTimeMillis();
//                        mStartX = event.getRawX();
//                        mStartY = event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        //移动item
//                        float moveX = event.getRawX();
//                        float moveY = event.getRawY();
//                        float offsetX = moveX - mStartX;
//                        float offsetY = moveY - mStartY;
//                        int left = (int) (v.getLeft() + offsetX);
//                        int top = (int) (v.getTop() + offsetY);
//                        int right = (int) (v.getRight() + offsetX);
//                        int bottom = (int) (v.getBottom() + offsetY);
////                        if (left < 0) {
////                            left = 0;
////                            right = left + itemWidth;
////                        }
////                        if (right > screenWidth) {
////                            right = screenWidth;
////                            left = right - itemWidth;
////                        }
////                        if (top < 0) {
////                            top = 0;
////                            bottom = top + itemHeight;
////                        }
////                        if (bottom > screenHeight) {
////
////                            bottom = screenHeight;
////                            top = bottom - itemHeight;
////                        }
//                        v.layout(left, top, right, bottom);
//                        mStartX = (int) event.getRawX();
//                        mStartY = (int) event.getRawY();
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        mEndTime = System.currentTimeMillis();
//                        mTimeOffset = mEndTime - mStartTime;
//                        if (mTimeOffset > LONG_CLICK_TIME) {
//
//                            return true;
//                        }
//                        break;
//                    case MotionEvent.ACTION_CANCEL:
//
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public int getViewId() {
        return R.layout.concern_channel_item;
    }
}
