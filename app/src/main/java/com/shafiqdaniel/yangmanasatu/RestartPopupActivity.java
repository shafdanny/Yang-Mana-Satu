package com.shafiqdaniel.yangmanasatu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by shafiq on 10/04/16.
 */
public class RestartPopupActivity extends AppCompatActivity {

    TextView popupTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restart);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        popupTextView = (TextView) findViewById(R.id.popup_text);

        int score = getIntent().getExtras().getInt("score");
        popupTextView.append(" Your score: " + score);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backIntent = new Intent(RestartPopupActivity.this, MainActivity.class);
        backIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(backIntent);
        finish();
    }
}
