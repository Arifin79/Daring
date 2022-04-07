package com.arifin.daringschool.Activity.UiTeacher.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Activity.UiTeacher.Model.Assignment;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScoreTeacherAdapter extends RecyclerView.Adapter<ScoreTeacherAdapter.AssigmentScoreHolder>{

    private List<Assignment> list;
    private Context context;

    public ScoreTeacherAdapter(Context context, List<Assignment> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ScoreTeacherAdapter.AssigmentScoreHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreTeacherAdapter.AssigmentScoreHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScoreTeacherAdapter.AssigmentScoreHolder holder, int position) {
        Assignment course = list.get(position);

        holder.assigmentName.setText(course.getmNameAssignment());
        holder.assigmentScore.setText(course.getmScore());
        holder.assigmentDate.setText(course.getmAssignmentDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AssigmentScoreHolder extends RecyclerView.ViewHolder {
        TextView assigmentName;
        TextView assigmentScore;
        TextView assigmentDate;

        public AssigmentScoreHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            assigmentName = itemView.findViewById(R.id.tv_name_assignment_score);
            assigmentScore = itemView.findViewById(R.id.tv_score_assignment);
            assigmentDate = itemView.findViewById(R.id.tv_assignment_date);
        }
    }
}
