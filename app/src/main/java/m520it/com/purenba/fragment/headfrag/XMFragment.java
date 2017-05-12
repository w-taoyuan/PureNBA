package m520it.com.purenba.fragment.headfrag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import m520it.com.purenba.base.BaseFragment;
import m520it.com.purenba.util.UIUtils;

/**
 * Created by 亮 on 2017/4/25.
 */
public class XMFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(100);
        textView.setText(getClass().getSimpleName());
        return textView;
    }
}