package com.example.braintrainerbeta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrainerbeta.Room.Score;

public class ScoreAdapter  extends ListAdapter<Score, ScoreAdapter.ScoreHolder> {

    private Context context;

    protected ScoreAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }
    private static  final DiffUtil.ItemCallback<Score> DIFF_CALLBACK = new DiffUtil.ItemCallback<Score>() {
        @Override
        public boolean areItemsTheSame(@NonNull Score oldItem, @NonNull Score newItem) {
            return oldItem.getKey().equals(newItem.getKey());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Score oldItem, @NonNull Score newItem) {
            return false;
        }
    };


    @NonNull
    @Override
    public ScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.score_item,parent,false);
        return new ScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreHolder holder, int position) {
        Score current_score = getItem(position);
        String ps = String.valueOf(position+1) +":-";
        holder.id.setText(ps);
        String date=current_score.getDate();
        String day= date.substring(0,2);
        String month = date.substring(3,5);
        String year = date.substring(6,8);
        Log.i("day",day);
        Log.i("month",month);
        Log.i("year",year);
        String datex = date.substring(10);
        Log.i("datex",datex);
        String finalDate = day +"/"+month +"/" +year +"  "+ datex;
        holder.date.setText(finalDate);
        String s = (String.valueOf(current_score.getPercentage()*100))+"%";
        holder.percent.setText(s);
        String c =current_score.getScore()+"/"+current_score.getNoOfQuestions();
        holder.score.setText(c);
        holder.timer.setText(String.valueOf(current_score.getTimer())+"s");
    }

    class  ScoreHolder extends RecyclerView.ViewHolder {

        TextView date,score,percent,id,timer;
        public ScoreHolder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.id);
            date = itemView.findViewById(R.id.date);
            score = itemView.findViewById(R.id.score);
            percent = itemView.findViewById(R.id.percent);
            timer = itemView.findViewById(R.id.timer);
        }
    }
}
