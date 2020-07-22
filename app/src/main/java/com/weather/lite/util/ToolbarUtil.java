package com.weather.lite.util;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.weather.lite.R;

import java.util.Objects;

public class ToolbarUtil {

    public static void setCenter(AppCompatActivity activity, Toolbar toolbar) {
        setCenter(activity, toolbar,
                activity.getResources().getColor(R.color.colorPrimary),
                activity.getResources().getColor(R.color.colorTextPrimary));
    }

    public static void setCenter(AppCompatActivity activity, Toolbar toolbar, int bkg, int textColor) {
        String title = "title";
        final CharSequence originalTitle = toolbar.getTitle();
        activity.setSupportActionBar(toolbar);
        Objects.requireNonNull(activity.getSupportActionBar()).setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(bkg);
        toolbar.setTitleTextColor(textColor);
        toolbar.setTitle(title);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (title.contentEquals(textView.getText())) {
                    textView.setGravity(Gravity.CENTER);
                    textView.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                    textView.getLayoutParams().height = LinearLayout.LayoutParams.MATCH_PARENT;
                    textView.setPadding(0, 0, 160, 0);
                }
            }
            toolbar.setTitle(originalTitle);
        }
    }

}