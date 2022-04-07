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

public class ScheduleHomeTeacherAdapter extends RecyclerView.Adapter<ScheduleHomeTeacherAdapter.ScheduleHolder>{

    private List<Assignment> list;
    private Context context;


    public ScheduleHomeTeacherAdapter(Context context, List<Assignment> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_schedule, parent, false);
        return new ScheduleHomeTeacherAdapter.ScheduleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScheduleHolder holder, int position) {
        Assignment course = list.get(position);

        holder.assigmentName.setText(course.getmNameAssignment());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {
        TextView assigmentName;

        public ScheduleHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            assigmentName = itemView.findViewById(R.id.tv_name_assignment);

        }
    }
}
