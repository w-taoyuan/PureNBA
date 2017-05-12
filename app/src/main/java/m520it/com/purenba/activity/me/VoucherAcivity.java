package m520it.com.purenba.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import m520it.com.purenba.R;

/**
 * @author Champion
 * @created on: 2017/4/26 22:53
 * @desc ${TODD} 代金卷界面
 */

public class VoucherAcivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        findViewById(R.id.back_btn).setOnClickListener(this);
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
