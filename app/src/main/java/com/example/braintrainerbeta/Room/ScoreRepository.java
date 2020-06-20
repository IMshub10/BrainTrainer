package com.example.braintrainerbeta.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScoreRepository {
    private  ScoreDao scoreDao;
    public ScoreRepository(Application application){
        ScoreDatabase scoreDatabase = ScoreDatabase.getInstance(application);
        scoreDao = scoreDatabase.scoreDao();
    }
      public void insert(Score score) {
        new Insert(scoreDao).execute(score);
    }

    public void delete(Score score) {
        new Delete(scoreDao).execute(score);
    }

    public void update(Score score) {
        new Update(scoreDao).execute(score);
    }

    public List<Score> getAllScores() {
        return scoreDao.getAllScores();
    }

    private static class Insert extends AsyncTask<Score, Void, Void> {
        private ScoreDao scoreDao;

        private Insert(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(Score... scores) {
            scoreDao.insert(scores[0]);
            return null;
        }

    }

    private static class Update extends AsyncTask<Score, Void, Void> {
        private ScoreDao scoreDao;

        private Update(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(Score... scores) {
            scoreDao.update(scores[0]);
            return null;
        }

    }

    private static class Delete extends AsyncTask<Score, Void, Void> {
        private ScoreDao scoreDao;

        private Delete(ScoreDao scoreDao) {
            this.scoreDao = scoreDao;
        }

        @Override
        protected Void doInBackground(Score... scores) {
            scoreDao.delete(scores[0]);
            return null;
        }

    }

}
