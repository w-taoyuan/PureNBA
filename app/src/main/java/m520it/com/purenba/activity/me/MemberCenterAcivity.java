package m520it.com.purenba.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import m520it.com.purenba.R;

/**
 * @author Champion
 * @created on: 2017/4/26 14:57
 * @desc ${TODD}  会员中心的界面
 */

public class MemberCenterAcivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_center);
        //设置返回点击事件
        findViewById(R.id.back_btn).setOnClickListener(this);
        //跳转登录界面
        findViewById(R.id.login_rl).setOnClickListener(this);
    }

    //关闭当前的activity
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn :
                finish();
                break;
            case R.id.login_rl :
                Intent intent = new Intent(getApplication(), ExclusiveFreeFlowAcivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}
