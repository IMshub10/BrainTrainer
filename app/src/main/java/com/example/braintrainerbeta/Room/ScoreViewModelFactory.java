package com.example.braintrainerbeta.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

public class ScoreViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    public ScoreViewModelFactory(@NonNull Application application) {
        super(application);
    }
}
