package com.arifin.daringschool.Activity.UiTeacher.Adapter;

import android.content.Context;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Model.Course;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AssigmentCourseAdapter extends RecyclerView.Adapter<AssigmentCourseAdapter.AssigmentHolder> {

    private List<Course> list;
    private Context context;
    private AutoTransition autoTransition;

    public AssigmentCourseAdapter(List<Course> list, Context context){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public AssigmentCourseAdapter.AssigmentHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new AssigmentCourseAdapter.AssigmentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AssigmentCourseAdapter.AssigmentHolder holder, int position) {
        final AssigmentHolder assigmentHolder = (AssigmentHolder) holder;
        Course course = new Course();
        course = list.get(position);

        holder.assigmentName.setText(course.getAssignmentName());
        holder.assigmentDate.setText(course.getAssignmentDate());
        holder.assigmentFileTask.setText(course.getAssignmentFileTask());

        assigmentHolder.linearSeeMore.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= 19) {
            autoTransition = new AutoTransition();
            autoTransition.setDuration(200);
        }
        assigmentHolder.isExpand = false;
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) assigmentHolder.linearSeeMore.getLayoutParams();
        assigmentHolder.btnDetailAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!assigmentHolder.isExpand) {
                    if (Build.VERSION.SDK_INT >= 19)
                        TransitionManager.beginDelayedTransition(assigmentHolder.rootView,autoTransition);
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    assigmentHolder.linearSeeMore.setLayoutParams(params);
                    assigmentHolder.isExpand = true;
                    assigmentHolder.btnDetailAssignment.setText("Hide Details");
                } else if (assigmentHolder.isExpand) {
                    if (Build.VERSION.SDK_INT >= 19)
                        TransitionManager.beginDelayedTransition(assigmentHolder.rootView, autoTransition);
                    params.height = 0;
                    assigmentHolder.linearSeeMore.setLayoutParams(params);
                    assigmentHolder.isExpand = false;
                    assigmentHolder.btnDetailAssignment.setText("See Details");
                }
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
        LinearLayout linearSeeMore;
        Boolean isExpand;
        RelativeLayout rootView;
        Button btnDetailAssignment;

        public AssigmentHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            assigmentName = itemView.findViewById(R.id.tv_card_assignment_name);
            assigmentDate = itemView.findViewById(R.id.tv_card_assignment_date);
            assigmentFileTask = itemView.findViewById(R.id.tv_card_lecture_assignment);
            btnDetailAssignment = itemView.findViewById(R.id.detailButton);
            linearSeeMore = itemView.findViewById(R.id.seeMoreLayout);
            rootView = itemView.findViewById(R.id.rootView);


        }
    }
}
