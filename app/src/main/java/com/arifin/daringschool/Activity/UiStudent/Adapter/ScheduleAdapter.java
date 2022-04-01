package com.arifin.daringschool.Activity.UiStudent.Adapter;

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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    private List<Course> list;
    private Context context;

    public ScheduleAdapter(List<Course> list, Context context){
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
        Course course = new Course();
        course = list.get(position);

        holder.nameStudent.setText(course.getNameStudent());
        holder.classStudent.setText(course.getClasses());
        holder.subjectSchedule.setText(course.getAssignmentName());
        holder.dateSchedule.setText(course.getAssignmentDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {
        TextView nameStudent;
        TextView classStudent;
        TextView subjectSchedule;
        TextView dateSchedule;

        public ScheduleHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameStudent = itemView.findViewById(R.id.tv_nama_siswa_schedule);
            classStudent = itemView.findViewById(R.id.tv_kelas_schedule);
            subjectSchedule = itemView.findViewById(R.id.tv_matapelajaran);
            dateSchedule = itemView.findViewById(R.id.tv_tanggal_schedule);
        }
    }
}
