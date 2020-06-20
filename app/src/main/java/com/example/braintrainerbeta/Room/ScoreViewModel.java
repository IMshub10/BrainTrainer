package com.example.braintrainerbeta.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ScoreViewModel extends AndroidViewModel {
    private ScoreRepository scoreRepository;

    public ScoreViewModel(@NonNull Application application) {
        super(application);
        scoreRepository = new ScoreRepository(application);
    }

    public void insert (Score score) { scoreRepository.insert(score); }
    public void update (Score score) { scoreRepository.update(score); }
    public void delete (Score score) { scoreRepository.delete(score); }

    public List<Score> getAllScores() {
        return scoreRepository.getAllScores();
    }
}
