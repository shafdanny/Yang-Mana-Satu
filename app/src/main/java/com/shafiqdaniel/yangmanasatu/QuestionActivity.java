package com.shafiqdaniel.yangmanasatu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.shafiqdaniel.yangmanasatu.Util.MyCountDownTimer;

/**
 * Created by shafiq on 10/04/16.
 */
public class QuestionActivity extends AppCompatActivity {

    ProgressBar progressBar;
    MyCountDownTimer countDownTimer;
    int timerMax;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_answer);

        timerMax = getResources().getInteger(R.integer.timerMax);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setProgress(timerMax);
        countDownTimer = new MyCountDownTimer(timerMax, 1, progressBar);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        countDownTimer.start();
    }
}
