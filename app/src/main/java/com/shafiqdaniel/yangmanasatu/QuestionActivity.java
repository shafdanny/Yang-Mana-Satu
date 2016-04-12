package com.shafiqdaniel.yangmanasatu;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shafiqdaniel.yangmanasatu.model.FlagDrawable;

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
    Activity thisActivity;
    ImageView imageView;
    FlagDrawable flagDrawable;

    int timerMax;
    int score;

    String[] option;
    String correctAnswer;


    Button.OnClickListener answerButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView = (TextView) findViewById(v.getId());
            String text = (String) textView.getText();
            if(text.equals(correctAnswer)) {
                incrementScore();
                saveHighScore();
                refreshQuestion();
            } else {
                countDownTimer.cancel();
                popupRestart();
            }
        }
    };

    private void saveHighScore() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.score_file_key), this.MODE_PRIVATE);
        int savedHighScore = sharedPref.getInt(getString(R.string.saved_high_score), 0);

        if(score > savedHighScore) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.saved_high_score), score);
            editor.commit();
        }

    }

    public void refreshQuestion() {
        countDownTimer.cancel();
        initAnswerButton();
        int newTimerMax = timerMax - (score*50);
        System.out.println(">> TIMER MAX: " + newTimerMax);
        countDownTimer = createCountDownTimer(newTimerMax);
        progressBar.setMax(newTimerMax);
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

        imageView = (ImageView) findViewById(R.id.image_view_qa);

        int savedScore = 0;
        thisActivity = this;
        flagDrawable = new FlagDrawable(this);

        option = getResources().getStringArray(R.array.states_name);
        initAnswerButton();

        timerMax = getResources().getInteger(R.integer.timerMax);

        scoreTextView = (TextView) findViewById(R.id.score_textview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        score = savedScore;
        scoreTextView.setText(Integer.toString(score));
        progressBar.setProgress(timerMax);
    }

    private void initAnswerButton() {
        // Pick a random flag, and set it as correct answer
        Random randFlag = new Random();
        String selectedFlagName = option[randFlag.nextInt(option.length)];
        Drawable correctFlag = flagDrawable.getFlag(selectedFlagName);
        imageView.setImageDrawable(correctFlag);
        correctAnswer = selectedFlagName;

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

    public CountDownTimer createCountDownTimer(int max) {
        return new CountDownTimer(max, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) millisUntilFinished);
            }

            @Override
            public void onFinish() {
                popupRestart();
            }
        };
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        countDownTimer = createCountDownTimer(timerMax);

        final LinearLayout layout = (LinearLayout)findViewById(R.id.question_answer_linear_layout);

        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width  = layout.getMeasuredWidth();
                int height = layout.getMeasuredHeight();
                countDownTimer.start();

            }
        });

        countDownTimer.start();
    }

    private void popupRestart() {
        Intent popupIntent = new Intent(QuestionActivity.this, RestartPopupActivity.class);
        Bundle b = new Bundle();
        b.putInt("score", score);
        b.putString("answer", correctAnswer);
        popupIntent.putExtras(b);
        startActivity(popupIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }
}
