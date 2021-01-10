package com.example.matchmakinggameassignment.dailogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.matchmakinggameassignment.R;

public class GameMenuDialog extends Dialog {

    private CardView exitOption, startOption;
    private Context context;

    public GameMenuDialog(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.context = context;
    }


    public GameMenuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected GameMenuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu_dialog_layout);
        exitOption = findViewById(R.id.exit_option);
        startOption = findViewById(R.id.start__resume_Option);

        exitOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) context;
                activity.finishAffinity();
            }
        });
    }
}
