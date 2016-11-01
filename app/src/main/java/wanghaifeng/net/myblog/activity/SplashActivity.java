package wanghaifeng.net.myblog.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import wanghaifeng.net.myblog.R;

public class SplashActivity extends AppCompatActivity {

    RelativeLayout splash_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_rl = (RelativeLayout) findViewById(R.id.splash_rl);

        startAnim();
    }

    /**
     * 闪屏动画
     */
    private void startAnim() {
        //缩放动画
        ScaleAnimation sa=new ScaleAnimation(2,1,2,1);
        sa.setDuration(1000);
        sa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画结束后
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpToNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splash_rl.startAnimation(sa);
    }

    /**
     * 跳转下一个页面
     */
    private void jumpToNextPage(){
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        finish();
    }
}
