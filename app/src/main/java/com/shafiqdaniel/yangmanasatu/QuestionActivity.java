package com.shafiqdaniel.yangmanasatu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.res.TypedArrayUtils;
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
import java.util.Random;

/**
 * Created by shafiq on 10/04/16.
 */
public class QuestionActivity extends AppCompatActivity {

    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    TextView scoreTextView;

    int timerMax;
    static int score;

    String[] option = {"Selangor", "Kedah", "Kelantan", "Johor", "Pahang", "Sabah", "Sarawak" };
    String correctAnswer = "Selangor";

    Button.OnClickListener answerButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView = (TextView) findViewById(v.getId());
            String text = (String) textView.getText();
            if(text.equals(correctAnswer)) {
                incrementScore();
                refreshQuestion();
            }
        }
    };

    public void refreshQuestion() {
        countDownTimer.cancel();
        initAnswerButton();
        countDownTimer.start();
    }


    private void incrementScore() {
        score++;
        scoreTextView.setText(Integer.toString(score));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_answer);
        int savedScore = 0;
        if(getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            savedScore = extras.getInt("currentScore");
            System.out.println(">> bundle score: " + savedScore);
        }
        initAnswerButton();

        timerMax = getResources().getInteger(R.integer.timerMax);

        scoreTextView = (TextView) findViewById(R.id.score_textview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        score = savedScore;
        scoreTextView.setText(Integer.toString(score));
        progressBar.setProgress(timerMax);

    }

    private void initAnswerButton() {
        Button[] ansBtn = new Button[4];
        ansBtn[0] = (Button) findViewById(R.id.choice1);
        ansBtn[1] = (Button) findViewById(R.id.choice2);
        ansBtn[2] = (Button) findViewById(R.id.choice3);
        ansBtn[3] = (Button) findViewById(R.id.choice4);

        String[] pickedAnswerOptions = pickNRandom(option, 4);

        ansBtn[0].setText(pickedAnswerOptions[0]);
        ansBtn[1].setText(pickedAnswerOptions[1]);
        ansBtn[2].setText(pickedAnswerOptions[2]);
        ansBtn[3].setText(pickedAnswerOptions[3]);

        Random r = new Random();
        int rand = r.nextInt(ansBtn.length);

        //System.out.println(">>RAND " + rand);

        // Now set a random button with the correct answer
        ansBtn[rand].setText(correctAnswer);

        ansBtn[0].setOnClickListener(answerButtonListener);
        ansBtn[1].setOnClickListener(answerButtonListener);
        ansBtn[2].setOnClickListener(answerButtonListener);
        ansBtn[3].setOnClickListener(answerButtonListener);
    }


    public String[] pickNRandom(String[] array, int n) {


        List<String> list = new ArrayList<String>(array.length);
        for (String i : array)
            list.add(i);
        Collections.shuffle(list);
        list.remove(correctAnswer);

        String[] answer = new String[n];
        for (int i = 0; i < n; i++)
            answer[i] = list.get(i);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }
}
