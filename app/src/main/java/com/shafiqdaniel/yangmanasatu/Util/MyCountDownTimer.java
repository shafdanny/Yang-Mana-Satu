package com.shafiqdaniel.yangmanasatu.Util;

import android.os.CountDownTimer;
import android.widget.ProgressBar;

/**
 * Created by shafiq on 10/04/16.
 */
public class MyCountDownTimer extends CountDownTimer {

    ProgressBar progressBar;


    public MyCountDownTimer(long millisInFuture, long countDownInterval, ProgressBar progressBar) {
        super(millisInFuture, countDownInterval);
        this.progressBar = progressBar;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //int progress = (int) (millisUntilFinished/100);
        //System.out.println(">> Millis " + millisUntilFinished);
        //System.out.println(">> Progress " + progress);
        progressBar.setProgress((int) millisUntilFinished);
    }

    @Override
    public void onFinish() {

    }
}
