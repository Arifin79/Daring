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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    private List<Assignment> list;
    private Context context;

    public ScheduleAdapter(Context context, List<Assignment> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ScheduleAdapter.ScheduleHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleAdapter.ScheduleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScheduleAdapter.ScheduleHolder holder, int position) {
        Assignment course = list.get(position);

        holder.assigmentName.setText(course.getmNameAssignment());
        holder.assigmentDate.setText(course.getmAssignmentDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {
        TextView assigmentName;
        TextView assigmentDate;

        public ScheduleHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            assigmentName = itemView.findViewById(R.id.tv_name_assignment_score);
            assigmentDate = itemView.findViewById(R.id.tv_assignment_date);
        }
    }
}
