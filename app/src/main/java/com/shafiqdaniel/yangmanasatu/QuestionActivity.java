package com.shafiqdaniel.yangmanasatu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by shafiq on 10/04/16.
 */
public class QuestionActivity extends AppCompatActivity {

    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    TextView scoreTextView;

    int timerMax;
    int score;

    String[] option = {"Selangor", "Kedah", "Kelantan", "Johor", "Pahang" };
    String next = "Selangor";

    Button.OnClickListener answerButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView = (TextView) findViewById(v.getId());
            String text = (String) textView.getText();
            if(text.equals(next)){
                System.out.println("Selangor clicked");

                incrementScore();

            }
        }
    };

    private void incrementScore() {
        score++;
        scoreTextView.setText(Integer.toString(score));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_answer);

        initAnswerButton();

        timerMax = getResources().getInteger(R.integer.timerMax);

        scoreTextView = (TextView) findViewById(R.id.score_textview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        score = 0;
        scoreTextView.setText(Integer.toString(score));
        progressBar.setProgress(timerMax);

    }

    private void initAnswerButton() {
        Button ansBtn1 = (Button) findViewById(R.id.choice1);
        Button ansBtn2 = (Button) findViewById(R.id.choice2);
        Button ansBtn3 = (Button) findViewById(R.id.choice3);
        Button ansBtn4 = (Button) findViewById(R.id.choice4);

        String[] pickedAnswerOptions = pickNRandom(option, 4);

        ansBtn1.setText(pickedAnswerOptions[0]);
        ansBtn2.setText(pickedAnswerOptions[1]);
        ansBtn3.setText(pickedAnswerOptions[2]);
        ansBtn4.setText(pickedAnswerOptions[3]);

        ansBtn1.setOnClickListener(answerButtonListener);
        ansBtn2.setOnClickListener(answerButtonListener);
        ansBtn3.setOnClickListener(answerButtonListener);
        ansBtn4.setOnClickListener(answerButtonListener);
    }

    public static String[] pickNRandom(String[] array, int n) {

        List<String> list = new ArrayList<String>(array.length);
        for (String i : array)
            list.add(i);
        Collections.shuffle(list);

        String[] answer = new String[n];
        for (int i = 0; i < n; i++)
            answer[i] = list.get(i);
        Arrays.sort(answer);

        return answer;
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
        startActivity(popupIntent);
    }
}
