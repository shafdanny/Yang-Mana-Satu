package com.shafiqdaniel.yangmanasatu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shafiq on 10/04/16.
 */
public class RestartPopupActivity extends AppCompatActivity {

    TextView popupTextView;
    TextView correctAnswerTextView;
    Button restartBtn;
    Button exitBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restart);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.5));

        popupTextView = (TextView) findViewById(R.id.popup_score_text);
        correctAnswerTextView = (TextView) findViewById(R.id.popup_correct_answer_text);
        restartBtn = (Button) findViewById(R.id.restart_button);
        exitBtn = (Button) findViewById(R.id.exit_button);

        int score = getIntent().getExtras().getInt("score");
        String answer = getIntent().getExtras().getString("answer");
        popupTextView.append("Markah anda: " + score);
        correctAnswerTextView.setText("Jawapan: " + answer);

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restart = new Intent(RestartPopupActivity.this, QuestionActivity.class);
                restart.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(restart);
                finish();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit = new Intent(RestartPopupActivity.this, MainActivity.class);
                exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exit);
                finish();
            }
        });
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
