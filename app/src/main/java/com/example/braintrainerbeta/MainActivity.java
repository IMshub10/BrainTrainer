package com.example.braintrainerbeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.braintrainerbeta.Room.Score;
import com.example.braintrainerbeta.Room.ScoreViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button goButtton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button buttonPlayAgain;
    TextView sumTextview;
    TextView resultTextView;
    TextView scoreTextView;
    TextView scoreTextView2;
    TextView timerTextView;
    List<Integer> answers = new ArrayList<>();

    int locCorrect;
    int score = 0;
    int noOfQuestions = 0;
    String sign = null;
    NumberPicker numberPicker;
    private  ScoreViewModel scoreViewModel;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("Timer",0);
        sumTextview = findViewById(R.id.sumTextView);
        goButtton = findViewById(R.id.start);
        numberPicker = findViewById(R.id.number_picker);
        resultTextView = findViewById(R.id.result);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView2 = findViewById(R.id.scoreTextView2);
        timerTextView = findViewById(R.id.timerTextView);

        goButtton.setVisibility(View.VISIBLE);
        sumTextview.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonPlayAgain = findViewById(R.id.playAgainButton);
        scoreViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ScoreViewModel.class);
        String[] nums = {"30s","40s","50s","60s","70s","80s","90s","100s","110s","120s"};
        int index = numberPicker.getValue();
        String val = nums[index];
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(nums.length-1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDisplayedValues(nums);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }

    @SuppressLint("SetTextI18n")
    public void chooseAns(View view) {
        if (Integer.toString(locCorrect).equals(view.getTag().toString())) {
            resultTextView.setText("  Correct \uD83D\uDE00");
            //ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
            //  toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT,150);
            score++;
        } else {
            resultTextView.setText(" Wrong \uD83D\uDE13");
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(50);
        }
        noOfQuestions++;
        scoreTextView.setText(score + "/" + noOfQuestions);
        scoreTextView2.setText(score + "/" + noOfQuestions);
        question();
    }

    @SuppressLint("SetTextI18n")
    public void playAgain(View view) {
        int numberpicked= numberPicker.getValue();
        switch (numberpicked){
            case  0:
                sharedPreferences.edit().putInt("Timer",30).apply();
                break;
            case  1:
                sharedPreferences.edit().putInt("Timer",40).apply();
                break;
            case  2:
                sharedPreferences.edit().putInt("Timer",50).apply();
                break;
            case  3:
                sharedPreferences.edit().putInt("Timer",60).apply();
                break;
            case  4:
                sharedPreferences.edit().putInt("Timer",70).apply();
                break;
            case  5:
                sharedPreferences.edit().putInt("Timer",80).apply();
                break;
            case  6:
                sharedPreferences.edit().putInt("Timer",90).apply();
                break;
            case  7:
                sharedPreferences.edit().putInt("Timer",100).apply();
                break;
            case  8:
                sharedPreferences.edit().putInt("Timer",110).apply();
                break;
            case  9:
                sharedPreferences.edit().putInt("Timer",120).apply();
                break;
            default:
                sharedPreferences.edit().putInt("Timer",31).apply();
                break;
        }
        score = 0;
        noOfQuestions = 0;
        timerTextView.setText("30");
        scoreTextView.setText(score + "/" + noOfQuestions);
        scoreTextView2.setText(score + "/" + noOfQuestions);
        buttonPlayAgain.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        sumTextview.setVisibility(View.VISIBLE);
        scoreTextView2.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.VISIBLE);
        numberPicker.setVisibility(View.INVISIBLE);
        resultTextView.setText(null);
        question();
        int putTime = sharedPreferences.getInt("Timer",30)*1000 +300;
        new CountDownTimer(putTime, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000));
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                buttonPlayAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0");
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.GONE);
                sumTextview.setVisibility(View.GONE);
                scoreTextView2.setVisibility(View.VISIBLE);
                scoreTextView.setVisibility(View.GONE);
                numberPicker.setVisibility(View.VISIBLE);
                String id = UUID.randomUUID().toString();
                String date = new SimpleDateFormat("dd MM yy hh:mm a", Locale.US).format(new Date());
                if (noOfQuestions ==0){
                    noOfQuestions =1;
                }
                float i = score;
                float j =noOfQuestions;
                int timer = sharedPreferences.getInt("Timer",30);
                int minScore= timer/3;
                if (i/j<0.5 || i<minScore){
                    resultTextView.setText(" Better Luck Next Time  \uD83D\uDE13");
                }else {
                    resultTextView.setText(" You Are Good !!  \uD83D\uDE00");
                }
                scoreViewModel.insert(new Score(id,
                        i/j,
                        score,
                        noOfQuestions,
                        date,
                        sharedPreferences.getInt("Timer",30)));
            }
        }.start();
    }

    public void start(View view) {


        numberPicker.setVisibility(View.INVISIBLE);
        goButtton.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        sumTextview.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        scoreTextView2.setVisibility(View.GONE);
        timerTextView.setVisibility(View.VISIBLE);
        playAgain(timerTextView);
    }

    public void question() {
        answers.clear();
        Random random = new Random();
        int a = random.nextInt(45) +1;
        int b = random.nextInt(45) +1;

        Random rand = new Random();
        int ran = rand.nextInt(4);
        switch (ran) {
            case 0:
                sign = "+";
                break;
            case 1:
                sign = "-";
                break;
            case 2:
                sign = "*";
                break;
            case 3:
                sign = "/";
                break;
        }
        String setText = a + sign + b;
        sumTextview.setText(setText);
        locCorrect = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == locCorrect) {
                switch (ran) {
                    case 0:
                        answers.add(a + b);
                        break;
                    case 1:
                        answers.add(a - b);
                        break;
                    case 2:
                        answers.add(a * b);
                        break;
                    case 3:
                        answers.add(a / b);
                        break;
                }
            } else {
                int wrongAns = random.nextInt(1000);
                int x = 0;
                switch (ran) {
                    case 0:
                        x = a + b;
                        break;
                    case 1:
                        x = a - b;
                        break;
                    case 2:
                        x = a * b;
                        break;
                    case 3:
                        x = a / b;
                        break;
                }
                while (wrongAns == x) {
                    wrongAns = random.nextInt(1000);
                }
                answers.add(wrongAns);
            }
        }


        button0.setText(String.valueOf(answers.get(0)));

        button1.setText(String.valueOf(answers.get(1)));

        button2.setText(String.valueOf(answers.get(2)));

        button3.setText(String.valueOf(answers.get(3)));

    }

}
