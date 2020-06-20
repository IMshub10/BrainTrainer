package com.example.braintrainerbeta.Room;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "score_table")
public class Score {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private  String key;

    private float percentage;

    private int score;

    private int noOfQuestions;

    private String date;

    private int timer;

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getScore() {
        return score;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public String getDate() {
        return date;
    }

    public int getTimer() {
        return timer;
    }


    public Score(@NonNull String key, float percentage, int score, int noOfQuestions, String date, int timer) {
        this.key = key;
        this.percentage = percentage;
        this.score = score;
        this.noOfQuestions = noOfQuestions;
        this.date = date;
        this.timer = timer;
    }
}
