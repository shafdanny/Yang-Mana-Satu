package com.shafiqdaniel.yangmanasatu;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    Button resetScoreBtn;
    TextView topScoreTextView;
    int topScore;
    AlertDialog.Builder builder;
    DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.start_button);
        aboutBtn = (Button) findViewById(R.id.about_button);
        resetScoreBtn = (Button) findViewById(R.id.reset_score_button);

        topScoreTextView = (TextView) findViewById(R.id.score_textview_main);

         dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        resetHighScore();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        builder = new AlertDialog.Builder(this);

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

        resetScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage(getString(R.string.reset_score_prompt_message))
                        .setPositiveButton(getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(getString(R.string.no), dialogClickListener)
                        .show();
            }
        });

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.score_file_key), this.MODE_PRIVATE);
        int defaultScore = 0;
        topScore = sharedPref.getInt(getString(R.string.saved_high_score), defaultScore);
        topScoreTextView.setText(getString(R.string.high_score_text) + " " + topScore);
    }

    private void resetHighScore() {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.score_file_key), this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score), 0);
        editor.commit();
        topScore = sharedPref.getInt(getString(R.string.saved_high_score), 0);
        topScoreTextView.setText(getString(R.string.high_score_text) + " " + topScore);
    }


}
