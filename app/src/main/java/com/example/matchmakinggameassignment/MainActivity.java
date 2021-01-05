//package com.example.matchmakinggameassignment;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ClipData;
//import android.content.ClipDescription;
//import android.content.Intent;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Point;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.DragEvent;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
//
//    public static final String IMAGE_VIEW_TAG = "imageView_tag";
//    private ImageView imageView;
//    private ImageView imageView2;
//    private TextView _view;
//    private ViewGroup _root;
//    private int _xDelta;
//    private int _yDelta;
//    RelativeLayout.LayoutParams layoutParamsFi;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        imageView = findViewById(R.id.imageView);
//        imageView2 = findViewById(R.id.imageView2);
//
//        _root = (ViewGroup) findViewById(R.id.root);
//
////        _view = new TextView(this);
////        _view.setText("TextView!!!!!!!!");
//
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
//
////        layoutParams.leftMargin = 50;
////        layoutParams.topMargin = 50;
////        layoutParams.bottomMargin = -250;
////        layoutParams.rightMargin = -250;
////        imageView.setLayoutParams(layoutParams);
////        _view.setLayoutParams(layoutParams);
//
////        imageView2.setOnTouchListener(this);
//
//        int loc[] = new int[2];
//        imageView.getLocationOnScreen(loc);
//        int x = loc[0];
//        int y = loc[1];
//        layoutParamsFi = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
//
//        Log.i("indranil", "starting position x-" + x + " y-" + y);
//
//        imageView.setTag(IMAGE_VIEW_TAG);
//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                ClipData.Item item = new ClipData.Item((Intent) v.getTag());
//                ClipData dragData = new ClipData(
//                        (CharSequence) v.getTag(),
//                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
//                        item);
//
//                // Instantiates the drag shadow builder.
//                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageView);
//                v.startDrag(dragData,  // the data to be dragged
//                        myShadow,  // the drag shadow builder
//                        null,      // no need to use local data
//                        0          // flags (not currently used, set to 0)
//                );
//
//                return true;
//            }
//        });
//
////        _root.removeAllViews();
////        _root.addView(imageView);
//    }
//
//    public boolean onTouch(View view, MotionEvent event) {
//        final int X = (int) event.getRawX();
//        final int Y = (int) event.getRawY();
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                _xDelta = X - lParams.leftMargin;
//                _yDelta = Y - lParams.topMargin;
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.i("indranil", "action up");
////                view.setLayoutParams(layoutParamsFi);
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.i("indranil", "pointer down");
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.i("indranil", "pointer up");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
//                layoutParams.leftMargin = X - _xDelta;
//                layoutParams.topMargin = Y - _yDelta;
//                layoutParams.rightMargin = -250;
//                layoutParams.bottomMargin = -250;
//                view.setLayoutParams(layoutParams);
//
//                int loc[] = new int[2];
//                view.getLocationOnScreen(loc);
//                int x = loc[0];
//                int y = loc[1];
////                Log.i("indranil", "updated position x-" + x + " y-" + y);
//                break;
//        }
////        _root.invalidate();
//        return true;
//    }
//
//
//    private static class MyDragShadowBuilder extends View.DragShadowBuilder {
//
//        // The drag shadow image, defined as a drawable thing
//        private static Drawable shadow;
//
//        // Defines the constructor for myDragShadowBuilder
//        public MyDragShadowBuilder(View v) {
//
//            // Stores the View parameter passed to myDragShadowBuilder.
//            super(v);
//
//            // Creates a draggable image that will fill the Canvas provided by the system.
//            shadow = new ColorDrawable(Color.LTGRAY);
//        }
//
//        // Defines a callback that sends the drag shadow dimensions and touch point back to the
//        // system.
//        @Override
//        public void onProvideShadowMetrics(Point size, Point touch) {
//            // Defines local variables
//            int width, height;
//
//            // Sets the width of the shadow to half the width of the original View
//            width = getView().getWidth() / 2;
//
//            // Sets the height of the shadow to half the height of the original View
//            height = getView().getHeight() / 2;
//
//            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
//            // Canvas that the system will provide. As a result, the drag shadow will fill the
//            // Canvas.
//            shadow.setBounds(0, 0, width, height);
//
//            // Sets the size parameter's width and height values. These get back to the system
//            // through the size parameter.
//            size.set(width, height);
//
//            // Sets the touch point's position to be in the middle of the drag shadow
//            touch.set(width / 2, height / 2);
//        }
//
//        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
//        // from the dimensions passed in onProvideShadowMetrics().
//        @Override
//        public void onDrawShadow(Canvas canvas) {
//
//            // Draws the ColorDrawable in the Canvas passed in from the system.
//            shadow.draw(canvas);
//        }
//    }
//}