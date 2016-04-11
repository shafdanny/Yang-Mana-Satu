package com.shafiqdaniel.yangmanasatu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    Button aboutBtn;
    TextView topScoreTextView;
    int topScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.start_button);
        aboutBtn = (Button) findViewById(R.id.about_button);
        topScoreTextView = (TextView) findViewById(R.id.score_textview_main);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(activityChangeIntent);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(activityChangeIntent);
            }
        });

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.score_file_key), this.MODE_PRIVATE);
        int defaultScore = 0;
        topScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultScore);
        topScoreTextView.append("" + topScore);
    }


}
