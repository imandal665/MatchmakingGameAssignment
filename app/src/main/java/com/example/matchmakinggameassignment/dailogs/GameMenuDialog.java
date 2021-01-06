package com.example.matchmakinggameassignment.dailogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.matchmakinggameassignment.R;

public class GameMenuDialog extends Dialog {
    public GameMenuDialog(@NonNull Context context) {
        super(context);
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
    }
}
