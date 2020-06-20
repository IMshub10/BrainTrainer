package com.example.braintrainerbeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.braintrainerbeta.Room.Score;
import com.example.braintrainerbeta.Room.ScoreViewModel;

import java.util.List;

public class StartActivity extends AppCompatActivity {

    TextView max_score1;
    private  ScoreViewModel scoreViewModel;
    RecyclerView recyclerView ;
    private  ScoreAdapter scoreAdapter;
    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        max_score1= findViewById(R.id.max_score1);
        play = findViewById(R.id.play);

        recyclerView= findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setItemPrefetchEnabled(true);
        scoreViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ScoreViewModel.class);
        scoreAdapter = new ScoreAdapter(this);
        recyclerView.setAdapter(scoreAdapter);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private   class  getScore extends AsyncTask<Void,Void,List<Score>>{
        @Override
        protected List<Score> doInBackground(Void... voids) {
            return scoreViewModel.getAllScores();
        }
        @Override
        protected void onPostExecute(List<Score> scores) {
            super.onPostExecute(scores);
            if (scores.size()>0){
                String s = (String.valueOf(scores.get(0).getPercentage()*100))+"%";
                scoreAdapter.submitList(scores);
                if (scores.size()==0){
                    max_score1.setText("Play And Know How Smart You Are");
                }else {
                    max_score1.setText("Your All Time Scores!!");
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        new getScore().execute();
    }
}