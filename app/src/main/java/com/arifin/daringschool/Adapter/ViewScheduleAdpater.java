package com.arifin.daringschool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Model.Course;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewScheduleAdpater extends RecyclerView.Adapter<ViewScheduleAdpater.ScheduleViewHolder>{

    private List<Course> list;
    private Context context;

    public ViewScheduleAdpater(List<Course> list, Context context){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewScheduleAdpater.ScheduleViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_schedule, parent, false);
        return new ViewScheduleAdpater.ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewScheduleAdpater.ScheduleViewHolder holder, int position) {
        Course course = new Course();
        course = list.get(position);

        holder.nameAsignemnt.setText(course.getAssignmentName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView nameAsignemnt;

        public ScheduleViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameAsignemnt = itemView.findViewById(R.id.tv_name_assignment);
        }
    }
}
