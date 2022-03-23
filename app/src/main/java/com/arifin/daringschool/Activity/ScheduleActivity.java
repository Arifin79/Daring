package com.arifin.daringschool.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.arifin.daringschool.Adapter.AssigmentCourseAdapter;
import com.arifin.daringschool.Adapter.ScheduleAdapter;
import com.arifin.daringschool.Model.Course;
import com.arifin.daringschool.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_schedule)
    Toolbar actionBar;
    @BindView(R.id.rv_schedule_assignment)
    RecyclerView rvSchedule;

    ScheduleAdapter scheduleAdapter;
    LinearLayoutManager layoutManagerAssigment;
    ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("Jadwal");

        listSchedule();

        scheduleAdapter = new ScheduleAdapter(courseList, ScheduleActivity.this);
        layoutManagerAssigment = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSchedule.setLayoutManager(layoutManagerAssigment);
        rvSchedule.setAdapter(scheduleAdapter);

    }

    private void listSchedule() {
        courseList = new ArrayList<>();
        for (int i = 0; i<5 ; i++) {
            Course course = new Course();
            course.setNameStudent("Irfan Wijaya");
            course.setClasses("12");
            course.setAssignmentName("Indonesia");
            course.setAssignmentDate("5 April 2022");
            courseList.add(course);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}