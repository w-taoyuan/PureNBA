package m520it.com.purenba.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import m520it.com.purenba.R;
import m520it.com.purenba.activity.channel.ChannelActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView mTencentLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initAnimation();
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
        mTencentLogo = (ImageView) findViewById(R.id.tencent_logo);
    }

    private void initAnimation() {
        ObjectAnimator aa = ObjectAnimator.ofFloat(mTencentLogo,"alpha",1,0);
        aa.setDuration(2000);
        aa.start();
        aa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
