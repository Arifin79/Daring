package com.arifin.daringschool.Activity.UiTeacher.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Activity.UiTeacher.Activity.AssigmentActivity;
import com.arifin.daringschool.Activity.UiTeacher.Activity.CreateAssignmentActivity;
import com.arifin.daringschool.Activity.UiTeacher.Model.Assignment;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AssigmentCourseAdapter extends RecyclerView.Adapter<AssigmentCourseAdapter.AssigmentHolder> {

    private List<Assignment> list;
    private Context context;


    public AssigmentCourseAdapter(Context context, List<Assignment> list){
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
    public void onBindViewHolder(@NonNull @NotNull AssigmentCourseAdapter.AssigmentHolder holder,int position) {
        Assignment course = list.get(position);

        holder.assigmentName.setText(course.getmNameAssignment());
        holder.assigmentDate.setText(course.getmAssignmentDate());
        holder.assigmentFileTask.setText(course.getmNameAssignmentFile());
        holder.assigmentDesc.setText(course.getmDescAssignment());

        holder.btnMenuDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, holder.btnMenuDetail);
                popupMenu.inflate(R.menu.menu_assignment);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menuDelete:
                                break;
                            case R.id.menuEdit:
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
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

        public AssigmentHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            assigmentName = itemView.findViewById(R.id.tv_card_assignment_name);
            assigmentDate = itemView.findViewById(R.id.tv_card_assignment_date);
            assigmentDesc = itemView.findViewById(R.id.tv_card_assignment_desc);
            assigmentFileTask = itemView.findViewById(R.id.tv_card_lecture_assignment);
            btnMenuDetail = itemView.findViewById(R.id.btn_menu_assignment);
            rootView = itemView.findViewById(R.id.rootView);


        }
    }

}
