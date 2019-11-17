package com.example.questionsscreen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ramotion.foldingcell.FoldingCell;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    FoldingCell foldingCell;
    private long timeCountInMilliSeconds = 1 * 15000;
    private ProgressBar progressBarCircle;
    private CountDownTimer countDownTimer;
    private TextView textViewTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        Events();
        startCountDownTimer();

    }

    private void Events() {
        try {

            foldingCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        foldingCell.toggle(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void startCountDownTimer() {

        try {
            countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    try {
                        textViewTime.setText(hmsTimeFormatter(millisUntilFinished));

                        progressBarCircle.setProgress((int) (millisUntilFinished / 1000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {

                    try {
                        textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                        setProgressBarValues();
                        foldingCell.toggle(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }.start();
            countDownTimer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;
    }

    private void Init() {
        try {

            foldingCell = findViewById(R.id.cell);
            progressBarCircle = findViewById(R.id.progressBarCircle);
            textViewTime = findViewById(R.id.textViewTime);

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setProgressBarValues() {

        try {
            progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
            progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
