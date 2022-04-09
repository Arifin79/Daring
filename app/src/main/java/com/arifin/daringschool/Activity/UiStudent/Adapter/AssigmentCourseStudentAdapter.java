package com.arifin.daringschool.Activity.UiStudent.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Activity.UiTeacher.Adapter.AssigmentCourseAdapter;
import com.arifin.daringschool.Activity.UiTeacher.Model.Assignment;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AssigmentCourseStudentAdapter  extends RecyclerView.Adapter<AssigmentCourseStudentAdapter.AssigmentHolder>{

    private List<Assignment> list;
    private Context context;


    public AssigmentCourseStudentAdapter(Context context, List<Assignment> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public AssigmentCourseStudentAdapter.AssigmentHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new AssigmentCourseStudentAdapter.AssigmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AssigmentCourseStudentAdapter.AssigmentHolder holder, int position) {
        Assignment course = list.get(position);

        holder.assigmentName.setText(course.getmNameAssignment());
        holder.assigmentDate.setText(course.getmAssignmentDate());
        holder.assigmentFileTask.setText(course.getmNameAssignmentFile());
        holder.assigmentDesc.setText(course.getmDescAssignment());
        holder.btnMenuDetail.setVisibility(View.GONE);
        holder.btnLectureAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(course.getmUrlPdfTeacher()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AssigmentHolder extends RecyclerView.ViewHolder {
        TextView assigmentName;
        TextView assigmentDate;
        TextView assigmentFileTask;
        TextView assigmentDesc;
        RelativeLayout rootView;
        ImageButton btnMenuDetail;
        ButtonBarLayout btnLectureAssignment;

        public AssigmentHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            assigmentName = itemView.findViewById(R.id.tv_card_assignment_name);
            assigmentDate = itemView.findViewById(R.id.tv_card_assignment_date);
            assigmentDesc = itemView.findViewById(R.id.tv_card_assignment_desc);
            assigmentFileTask = itemView.findViewById(R.id.tv_card_lecture_assignment);
            btnMenuDetail = itemView.findViewById(R.id.btn_menu_assignment);
            btnLectureAssignment = itemView.findViewById(R.id.btn_lecture_assignment);
            rootView = itemView.findViewById(R.id.rootView);

        }
    }
}
