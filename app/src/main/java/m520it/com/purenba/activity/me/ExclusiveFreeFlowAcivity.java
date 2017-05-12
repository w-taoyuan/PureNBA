package m520it.com.purenba.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import m520it.com.purenba.R;

/**
 * @author Champion
 * @created on: 2017/4/27 9:41
 * @desc ${TODD}  腾讯专属免流界面
 */

public class ExclusiveFreeFlowAcivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_exclusive_free_flow);
        initView();
    }

    private void initView() {
        findViewById(R.id.back_btn).setOnClickListener(this);
        findViewById(R.id.show_dialog).setOnClickListener(this);
    }

    //关闭当前的activity
    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
