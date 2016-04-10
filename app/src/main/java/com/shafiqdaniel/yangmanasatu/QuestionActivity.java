package com.shafiqdaniel.yangmanasatu;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by shafiq on 10/04/16.
 */
public class QuestionActivity extends AppCompatActivity {

    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    int timerMax;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_answer);

        timerMax = getResources().getInteger(R.integer.timerMax);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setProgress(timerMax);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        countDownTimer = new CountDownTimer(timerMax, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) millisUntilFinished);
            }

            @Override
            public void onFinish() {
                popupRestart();
            }
        };
        countDownTimer.start();
    }

    private void popupRestart() {
        Intent popupIntent = new Intent(QuestionActivity.this, RestartPopupActivity.class);
        popupIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(popupIntent);
    }
}
