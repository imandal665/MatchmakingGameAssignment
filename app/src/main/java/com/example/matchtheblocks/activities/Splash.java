package com.example.matchtheblocks.activities;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.matchtheblocks.R;

public class Splash extends Activity {

    private LottieAnimationView animationView;
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContentView = findViewById(R.id.splash_parent);
        animationView = findViewById(R.id.splash_animation);
        hide();

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                MediaPlayer
                        backgroundMusic = MediaPlayer.create(Splash.this, R.raw.splash_loading);
                backgroundMusic.setVolume(0f, 0.8f);
                backgroundMusic.setLooping(false);
//                backgroundMusic.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                Intent myIntent = new Intent(Splash.this, InGameActivity.class);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(Splash.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(myIntent, options.toBundle());
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                startActivity(new Intent(Splash.this, InGameActivity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animationView.playAnimation();

//        new CountDownTimer(3000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                startActivity(new Intent(Splash.this, InGameActivity.class));
//            }
//        };
    }

    private void hide() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }
}