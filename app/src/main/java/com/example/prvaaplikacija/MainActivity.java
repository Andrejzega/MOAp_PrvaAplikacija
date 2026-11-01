package com.example.prvaaplikacija;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private boolean useCenteredSnackbar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showToast(View view) {
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        if (!useCenteredSnackbar) {
            Snackbar snackbar = Snackbar.make(view, getString(R.string.snackbar_message), Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundTintList(null);
            snackbarView.setBackgroundResource(R.drawable.custom_toast_bg);
            snackbarView.setPadding(16, 0, 16, 0);

            ViewGroup.LayoutParams lp = snackbarView.getLayoutParams();
            if (lp instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) lp;
                params.gravity = Gravity.CENTER;
                params.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                snackbarView.setLayoutParams(params);
            } else if (lp instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) lp;
                params.gravity = Gravity.CENTER;
                params.width = CoordinatorLayout.LayoutParams.WRAP_CONTENT;
                snackbarView.setLayoutParams(params);
            }

            TextView textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
            if (textView != null) {
                textView.setText(getString(R.string.snackbar_message));
                textView.setTextColor(Color.parseColor("#1F1F1F"));
                textView.setTextSize(14);
                textView.setPadding(0, 8, 0, 8);
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_android_green, 0, 0, 0);
                textView.setCompoundDrawablePadding(12);
            }
            snackbar.show();
        }
        useCenteredSnackbar = !useCenteredSnackbar;
    }
}
