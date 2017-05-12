package m520it.com.purenba.activity.channel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by conan on 2017/4/25.
 */

public abstract class RvAdapter extends RecyclerView.Adapter {

    protected List<ChannelBean> mDatas;

    private OnItemClickListener mOnItemClickListener;

    public RvAdapter(List<ChannelBean> datas){
        mDatas = datas;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setDatas(List<ChannelBean> datas) {
        mDatas = datas;
    }

    public void addDatas(ChannelBean bean) {
        mDatas.add(bean);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getViewId(), parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        setViewData(holder.itemView,mDatas.get(position),position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position,mDatas.get(position));
                }
                mDatas.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 给item设置数据
     *  @param itemView
     * @param bean
     * @param position
     */
    protected abstract void setViewData(View itemView, ChannelBean bean, int position);

    /**
     * @return 获取itemView的id
     */
    public abstract int getViewId();

    class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
