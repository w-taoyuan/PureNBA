package m520it.com.purenba.activity.channel;

import android.graphics.Color;
import android.os.Bundle;
import android.support.test.espresso.core.deps.guava.eventbus.EventBus;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import m520it.com.purenba.R;
import m520it.com.purenba.fragment.HeadFragment;
import m520it.com.purenba.util.ActivityUtils;

public class ChannelActivity extends AppCompatActivity {

    private ArrayList<ChannelBean> mConcernChannels;
    private ArrayList<ChannelBean> mNoConcernChannels;

    private RecyclerView mHasConcernedRv;
    private RecyclerView mNoConcernedRv;
    private ConcernChannelRvAdapter mConcernChannelRvAdapter;
    private NoConcernChannelRvAdapter mNoConcernChannelRvAdapter;

    private ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
        setContentView(R.layout.activity_channel);
        initView();
        initDragListener();
    }

    private void initDragListener() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            /**
             * 用于设置是否处理拖拽事件和滑动事件，以及拖拽和滑动操作的方向，比如如果是列表类型的RecyclerView，
             * 拖拽只有UP、DOWN两个方向，而如果是网格类型的则有UP、DOWN、LEFT、RIGHT四个方向
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //dragFlags 是拖拽标志，swipeFlags是滑动标志
                int dragFlags;
                //把swipeFlags 设置为0，表示不处理滑动操作
                int swipeFlags = 0;
                // 如果我们设置了非0的dragFlags ，那么当我们长按item的时候就会进入拖拽并在拖拽过程中不断回调onMove()方法，
                // 我们就在这个方法里获取当前拖拽的item和已经被拖拽到所处位置的item的ViewHolder，有了这2个ViewHolder，
                // 我们就可以交换他们的数据集并调用Adapter的notifyItemMoved方法来刷新item
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mConcernChannels, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mConcernChannels, i, i - 1);
                    }
                }
                mConcernChannelRvAdapter.notifyItemMoved(fromPosition, toPosition);

                //发送顺序改变的信息
                org.greenrobot.eventbus.EventBus.getDefault().post(fromPosition + "*" + toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * 当item被选中的时候调用
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 当item被松开的时候调用
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }
        });
        itemTouchHelper.attachToRecyclerView(mHasConcernedRv);
    }


    public static final String ACTION = "action";
    public static final int DELETE_ACTION = 0;
    public static final int ADD_ACTION = 1;

    private void initView() {

        //后退键
        mBackBtn = (ImageView) findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(ChannelActivity.this);
            }
        });

        //已关注和未关注的 recyclerview 的初始化
        mHasConcernedRv = (RecyclerView) findViewById(R.id.has_concerned_rv);
        mHasConcernedRv.setNestedScrollingEnabled(false);
        mConcernChannelRvAdapter = new ConcernChannelRvAdapter(mConcernChannels);
        mHasConcernedRv.setAdapter(mConcernChannelRvAdapter);
        mConcernChannelRvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position,ChannelBean channelBean) {
                mNoConcernChannelRvAdapter.addDatas(channelBean);
                mNoConcernChannelRvAdapter.notifyDataSetChanged();
                Bundle bundle = new Bundle();
                bundle.putInt(ACTION,DELETE_ACTION);
                bundle.putInt("position",position);
                bundle.putSerializable("bean",channelBean);
                org.greenrobot.eventbus.EventBus.getDefault().post(bundle);
            }
        });
        mNoConcernedRv = (RecyclerView) findViewById(R.id.no_concerned_rv);
        mNoConcernedRv.setNestedScrollingEnabled(false);
        mNoConcernChannelRvAdapter = new NoConcernChannelRvAdapter(mNoConcernChannels);
        mNoConcernedRv.setAdapter(mNoConcernChannelRvAdapter);
        mNoConcernChannelRvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position,ChannelBean channelBean) {
                mConcernChannelRvAdapter.addDatas(channelBean);
                mConcernChannelRvAdapter.notifyDataSetChanged();
                Bundle bundle = new Bundle();
                bundle.putInt(ACTION,ADD_ACTION);
                bundle.putInt("position",position);
                bundle.putSerializable("bean",channelBean);
                org.greenrobot.eventbus.EventBus.getDefault().post(bundle);
            }
        });
        mHasConcernedRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        mNoConcernedRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
    }

    private void initDatas() {
        mConcernChannels = (ArrayList<ChannelBean>) getIntent()
                .getSerializableExtra(HeadFragment.CONCERN_CHANNELS_TAG);
        mNoConcernChannels = (ArrayList<ChannelBean>) getIntent()
                .getSerializableExtra(HeadFragment.NO_CONCERN_CHANNELS_TAG);
    }


}
