package com.example.matchmakinggameassignment.activities;


import android.animation.ValueAnimator;
import android.app.Activity;

import android.content.ClipData;

//import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.matchmakinggameassignment.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class InGameActivity extends Activity {
    public static final String TAG = "MainActivity2";
    TextView imgM, imgL, imgR, imgCMP1, imgCMP2, imgCMP3, gameRestartingInfo, startGameTextView;
    LottieAnimationView lottiewView1, lottiewView2, lottiewView3;
    CardView cardConfirm1, cardConfirm2, cardConfirm3, cardMatch1, cardMatch2, cardMatch3, homeAction, pauseMenuCardView, resumeOption, exitOption;

    boolean dragging = false;
    int draggingNumber;
    int onDragEndedNumber;
    int matchedNumber = 0;
    View draggingView;
    private ArrayList<Integer> randomNumberList;
    private ArrayList<Integer> suffledNumberList;
    private ArrayList<Integer> completedNumber;
    private ArrayList<View> viewsToMatch;
    private TextToSpeech textToSpeechSystem;
    private MediaPlayer backgroundMusic;
    private View mContentView;
    private android.widget.LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bindingViews();
        hide();
        viewsToMatch = new ArrayList<>();

        viewsToMatch.add(cardMatch1);
        viewsToMatch.add(cardMatch2);
        viewsToMatch.add(cardMatch3);

        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        backgroundMusic.setVolume(0f, 0.2f);
        backgroundMusic.setLooping(true);
//        backgroundMusic.start();


        callPauseMenu();

        setUpNumbers();


        setingUpAllListners();


    }


    private void hide() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    private void callPauseMenu() {
        if (startGameTextView.getText().toString().equals("Resume"))
            pauseMenuCardView.animate().translationY(0);
        cardMatch1.setEnabled(false);
        cardMatch2.setEnabled(false);
        cardMatch3.setEnabled(false);
        homeAction.setEnabled(false);
    }

    private void setingUpAllListners() {

        resumeOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMenuCardView.animate().translationY(1200);
                cardMatch1.setEnabled(true);
                cardMatch2.setEnabled(true);
                cardMatch3.setEnabled(true);
                homeAction.setEnabled(true);
            }
        });
        exitOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        cardConfirm1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm1, 0);
                return true;
            }
        });
        cardConfirm2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm2, 1);
                return true;
            }
        });
        cardConfirm3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardConfirm3, 2);
                return true;
            }
        });

        cardMatch1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch1, 0);
                return true;
            }
        });

        cardMatch1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch1, imgL);
            }
        });

        cardMatch2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch2, 1);
                return true;
            }
        });

        cardMatch2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch2, imgM);
            }
        });

        cardMatch3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                InGameActivity.this.onDrag(v, event, cardMatch3, 2);
                return true;
            }
        });

        cardMatch3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return InGameActivity.this.onTouch(event, cardMatch3, imgR);
            }
        });

        homeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameTextView.setText("Resume");
                callPauseMenu();
            }
        });
    }

    private void bindingViews() {
        mContentView = findViewById(R.id.parent_game_view);
        pauseMenuCardView = findViewById(R.id.pause_menu_card_view);
        resumeOption = findViewById(R.id.start_resume_Option);
        exitOption = findViewById(R.id.exit_option);
        startGameTextView = findViewById(R.id.start_game_text_view);
        imgM = findViewById(R.id.imageView);
        imgL = findViewById(R.id.imageView2);
        imgR = findViewById(R.id.imageView3);
        imgCMP1 = findViewById(R.id.compare_image_1);
        imgCMP2 = findViewById(R.id.compare_image_2);
        imgCMP3 = findViewById(R.id.compare_image_3);

        cardConfirm1 = findViewById(R.id.card_confirm_1);
        cardConfirm2 = findViewById(R.id.card_confirm_2);
        cardConfirm3 = findViewById(R.id.card_confirm_3);

        cardMatch1 = findViewById(R.id.card_match_1);
        cardMatch2 = findViewById(R.id.card_match_2);
        cardMatch3 = findViewById(R.id.card_match_3);

        lottiewView1 = findViewById(R.id.lottie_view_1);
        lottiewView2 = findViewById(R.id.lottie_view_2);
        lottiewView3 = findViewById(R.id.lottie_view_3);

        homeAction = findViewById(R.id.home_action);

        gameRestartingInfo = findViewById(R.id.game_restarting_info);

    }

    private void setUpNumbers() {
        randomNumberList = new ArrayList<>();
        suffledNumberList = new ArrayList<>();
        completedNumber = new ArrayList<>();

        while (randomNumberList.size() < 3) {
            int newNumber = (int) (Math.random() * 20 + 0);
            if (!randomNumberList.contains(newNumber)) {
                randomNumberList.add(newNumber);
                suffledNumberList.add(newNumber);

            }
        }
        imgCMP1.setText(String.valueOf(randomNumberList.get(0)));
        imgCMP2.setText(String.valueOf(randomNumberList.get(1)));
        imgCMP3.setText(String.valueOf(randomNumberList.get(2)));


        Collections.shuffle(suffledNumberList);


        imgL.setText(String.valueOf(suffledNumberList.get(0)));
        imgM.setText(String.valueOf(suffledNumberList.get(1)));
        imgR.setText(String.valueOf(suffledNumberList.get(2)));


        cardMatch1.setAlpha(1);
        cardMatch2.setAlpha(1);
        cardMatch3.setAlpha(1);

    }

    private boolean onTouch(MotionEvent event, CardView cardView, TextView tv) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(cardView);
            int number = Integer.parseInt(tv.getText().toString());
            if (!completedNumber.contains(number))
                cardView.startDrag(data, shadowBuilder, cardView, View.DRAG_FLAG_OPAQUE);
            return true;
        } else {
            return false;
        }
    }

    private void onDrag(View v, DragEvent event, CardView cardView, int position) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                Log.d(TAG, "DragEvent.ACTION_DRAG_STARTED");

                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                if (!dragging) {
                    dragging = true;
                    draggingView = cardView;
                    draggingNumber = suffledNumberList.get(position);
                    cardView.setVisibility(View.INVISIBLE);
                    if (textToSpeechSystem != null)
                        textToSpeechSystem.stop();
                    textToSpeechSystem = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status == TextToSpeech.SUCCESS) {
                                String textToSay = String.valueOf(draggingNumber);
                                textToSpeechSystem.speak(textToSay, TextToSpeech.QUEUE_FLUSH, null, null);
                            }
                        }
                    });
                } else {
                    onDragEndedNumber = randomNumberList.get(position);
                    Log.d(TAG, " onDragEndedNumber- " + onDragEndedNumber);
                }
                Log.d(TAG, "DragEvent.ACTION_DRAG_ENTERED");
                Log.d(TAG, "dragging - " + draggingNumber);
                int x_cord = (int) event.getX();
                int y_cord = (int) event.getY();

                break;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "DragEvent.ACTION_DRAG_EXITED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                layoutParams.leftMargin = x_cord;
                layoutParams.topMargin = y_cord;
                v.setLayoutParams(layoutParams);
                break;

            case DragEvent.ACTION_DRAG_LOCATION:
//                Log.d(msg, "DragEvent.ACTION_DRAG_LOCATION");
//                x_cord = (int) event.getX();
//                y_cord = (int) event.getY();
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                dragging = false;
                Log.d(TAG, "DragEvent.ACTION_DRAG_ENDED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                cardView.setVisibility(View.VISIBLE);
                // Do nothing
                break;

            case DragEvent.ACTION_DROP:
                Log.d(TAG, "ACTION_DROP");


                if (draggingView != null && !viewsToMatch.contains(cardView)) {

                    int num = randomNumberList.get(position);
                    if (draggingNumber == num) {
                        Log.d(TAG, "on DROP event dragging no- " + draggingNumber + " num -" + num);
                        draggingView.setAlpha(0);
                        completedNumber.add(draggingNumber);
                        playTaskAchievedSoundEffect(true);
                        switch (position) {
                            case 0:

                                imgCMP1.setTextColor(getResources().getColor(R.color.white));
                                imgCMP1.setText("\u2713");
                                lottiewView1.playAnimation();

                                break;
                            case 1:

                                imgCMP2.setTextColor(getResources().getColor(R.color.white));
                                imgCMP2.setText("\u2713");
                                lottiewView2.playAnimation();

                                break;

                            case 2:

                                imgCMP3.setTextColor(getResources().getColor(R.color.white));
                                imgCMP3.setText("\u2713");
                                lottiewView3.playAnimation();
                                break;

                            default:
                                break;

                        }

                        cardView.setVisibility(View.INVISIBLE);
                        matchedNumber += 1;
                        if (matchedNumber == 3) {
                            matchedNumber = 0;
                            gameRestartingInfo.setVisibility(View.VISIBLE);
                            restartGame();
                        }

                    } else {
                        playTaskAchievedSoundEffect(false);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void restartGame() {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                gameRestartingInfo.setText("Restarting in " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                gameRestartingInfo.setVisibility(View.GONE);
                setUpNumbers();
            }

        }.start();
    }

    private void playTaskAchievedSoundEffect(boolean success) {

        MediaPlayer mp;
        if (success)
//            mp = MediaPlayer.create(this, R.raw.comleted);
            mp = MediaPlayer.create(this, R.raw.correct);
        else {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
            mp = MediaPlayer.create(this, R.raw.error);
        }
        mp.start();
    }

    @Override
    public void onBackPressed() {
        callPauseMenu();
//        super.onBackPressed();
    }
}