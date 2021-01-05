package com.example.matchmakinggameassignment;


import android.app.Activity;

import android.content.ClipData;
import android.content.ClipDescription;

//import android.support.v7.app.ActionBarActivity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity2 extends Activity {

    TextView imgM, imgL, imgR, imgCMP1, imgCMP2, imgCMP3, gameRestartingInfo;

    CardView cardConfirm1, cardConfirm2, cardConfirm3, cardMatch1, cardMatch2, cardMatch3;
    String msg = "drag";
    boolean dragging = false;
    int draggingNumber;
    int onDragEndedNumber;
    int matchedNumber = 0;
    View draggingView;
    private ArrayList<Integer> randomNumberList;
    private ArrayList<Integer> suffledNumberList;
    private ArrayList<View> viewsToMatch;
    //    private android.widget.RelativeLayout.LayoutParams layoutParams;
    private android.widget.LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        gameRestartingInfo = findViewById(R.id.game_restarting_info);

        viewsToMatch = new ArrayList<>();
//        viewsToMatch.add(imgL);
//        viewsToMatch.add(imgM);
//        viewsToMatch.add(imgR);

        viewsToMatch.add(cardMatch1);
        viewsToMatch.add(cardMatch2);
        viewsToMatch.add(cardMatch3);

        setUpNumbers();

//        imgL.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return MainActivity2.this.onLongClick(v,imgL);
//            }
//        });

        cardConfirm1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
//                MainActivity2.this.onDrag(v, event, imgCMP1,0);
                MainActivity2.this.onDrag(v, event, cardConfirm1, 0);
                return true;
            }
        });
        cardConfirm2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
//                MainActivity2.this.onDrag(v, event, imgCMP2);
                MainActivity2.this.onDrag(v, event, cardConfirm2, 1);
                return true;
            }
        });
        cardConfirm3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
//                MainActivity2.this.onDrag(v, event, imgCMP3);
                MainActivity2.this.onDrag(v, event, cardConfirm3, 2);
                return true;
            }
        });

        cardMatch1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
//                MainActivity2.this.onDrag(v, event, imgL);
                MainActivity2.this.onDrag(v, event, cardMatch1, 0);
                return true;
            }
        });

        cardMatch1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MainActivity2.this.onTouch(event, cardMatch1);
            }
        });


//        imgM.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return MainActivity2.this.onLongClick(v,imgM);
//            }
//        });

        cardMatch2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
//                MainActivity2.this.onDrag(v, event, cardMatch2);
                MainActivity2.this.onDrag(v, event, cardMatch2, 1);
                return true;
            }
        });

        cardMatch2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MainActivity2.this.onTouch(event, cardMatch2);
            }
        });


//        imgR.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return MainActivity2.this.onLongClick(v,imgR);
//            }
//        });

        cardMatch3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
//                MainActivity2.this.onDrag(v, event, imgR);
                MainActivity2.this.onDrag(v, event, cardMatch3, 2);
                return true;
            }
        });

        cardMatch3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return MainActivity2.this.onTouch(event, cardMatch3);
            }
        });
    }

    private void setUpNumbers() {
        randomNumberList = new ArrayList<>();
        suffledNumberList = new ArrayList<>();

        while (randomNumberList.size() < 3) {
            int newNumber = (int) (Math.random() * 20 + 0);
            if (!randomNumberList.contains(newNumber)){
                randomNumberList.add(newNumber);
                suffledNumberList.add(newNumber);

            }
        }
        imgCMP1.setText(String.valueOf(randomNumberList.get(0)));
        imgCMP2.setText(String.valueOf(randomNumberList.get(1)));
        imgCMP3.setText(String.valueOf(randomNumberList.get(2)));

//        suffledNumberList = new ArrayList<>();
        Collections.shuffle(suffledNumberList);
//        suffledNumberList = randomNumberList;

        imgL.setText(String.valueOf(suffledNumberList.get(0)));
        imgM.setText(String.valueOf(suffledNumberList.get(1)));
        imgR.setText(String.valueOf(suffledNumberList.get(2)));

//        imgL.setAlpha(1);
//        imgM.setAlpha(1);
//        imgR.setAlpha(1);

        cardMatch1.setAlpha(1);
        cardMatch2.setAlpha(1);
        cardMatch3.setAlpha(1);

        Log.d(msg, "random numbers - "+randomNumberList);
        Log.d(msg, "suffled numbers - "+suffledNumberList);
    }

    private boolean onTouch(MotionEvent event, CardView cardView) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(cardView);
            cardView.startDrag(data, shadowBuilder, cardView, View.DRAG_FLAG_OPAQUE);
            return true;
        } else {
            return false;
        }
    }

    private void onDrag(View v, DragEvent event, CardView cardView, int position) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
//                layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                if (!dragging) {
                    dragging = true;
                    draggingView = cardView;
//                    draggingNumber = Integer.parseInt(textView.getText().toString());

                    draggingNumber = suffledNumberList.get(position);
                    cardView.setVisibility(View.INVISIBLE);
                } else {
//                    int number = 100;
//                    try {
//                        number = Integer.parseInt(textView.getText().toString());
//                    } catch (Exception e) {
//                    }
//                    if (number != 100)
//                        onDragEndedNumber = number;
                    onDragEndedNumber = randomNumberList.get(position);
                    Log.d(msg, " onDragEndedNumber- " + onDragEndedNumber);
                }
//                imageView.setAlpha(0);
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                Log.d(msg, "dragging - " + draggingNumber);
                int x_cord = (int) event.getX();
                int y_cord = (int) event.getY();

                break;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
                layoutParams.leftMargin = x_cord;
                layoutParams.topMargin = y_cord;
                v.setLayoutParams(layoutParams);
//                        img.setVisibility(View.VISIBLE);
                break;

            case DragEvent.ACTION_DRAG_LOCATION:
//                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
//                x_cord = (int) event.getX();
//                y_cord = (int) event.getY();
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                dragging = false;
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                x_cord = (int) event.getX();
                y_cord = (int) event.getY();
//                Log.d(msg, "cord x-" + x_cord + " cord y-" + y_cord);
//                imgCM.post(() -> {
//                    // Values should no longer be 0
//                    int[] point = new int[2];
//                    imgCM.getLocationOnScreen(point); // or getLocationInWindow(point)
//                    int x = point[0];
//                    int y = point[1];
//                    Log.d(msg, "imgCM   cord x-" + x + " cord y-" + y);
//                });
//                textView.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
//                imageView.setAlpha(1);
                // Do nothing
                break;

            case DragEvent.ACTION_DROP:
                Log.d(msg, "ACTION_DROP event");

//                if (draggingView != null && !viewsToMatch.contains(textView)) {
                if (draggingView != null && !viewsToMatch.contains(cardView)) {
//                    int num = Integer.parseInt(textView.getText().toString());
                    int num = randomNumberList.get(position);
                    if (draggingNumber == num) {
                        Log.d(msg, "on DROP event dragging no- " + draggingNumber + " num -" + num);
                        draggingView.setAlpha(0);
//                        textView.setText("Done");
                        cardView.setVisibility(View.INVISIBLE);
                        matchedNumber += 1;
                        if (matchedNumber == 3) {
                            matchedNumber = 0;
                            gameRestartingInfo.setVisibility(View.VISIBLE);
                            restartGame();
                        }

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

}