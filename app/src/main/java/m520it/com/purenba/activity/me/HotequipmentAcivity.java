package m520it.com.purenba.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import m520it.com.purenba.R;

/**
 * @author Champion
 * @created on: 2017/4/26 22:37
 * @desc ${TODD} 装备热卖界面
 */

public class HotequipmentAcivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout);
        initView();
    }

    private void initView() {
//        findViewById(R.id.close_btn).setOnClickListener(this);
    }

    //关闭当前的activity
    @Override
    public void onClick(View v) {
        finish();
    }
}
