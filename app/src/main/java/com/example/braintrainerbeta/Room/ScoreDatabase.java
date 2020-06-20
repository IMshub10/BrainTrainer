package com.example.braintrainerbeta.Room;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Score.class}, version = 2, exportSchema = false)
public abstract class ScoreDatabase extends RoomDatabase {

    private static ScoreDatabase instance;
    public abstract ScoreDao scoreDao();

    public  static  synchronized ScoreDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ScoreDatabase.class,"score_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private  static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbTask(instance).execute();
        }
    };

    private  static class  PopulateDbTask extends AsyncTask<Void,Void,Void> {

        private  ScoreDao scoreDao;
        private PopulateDbTask(ScoreDatabase database){
            scoreDao = database.scoreDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}

