package com.arifin.daringschool.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.arifin.daringschool.Adapter.AssigmentCourseAdapter;
import com.arifin.daringschool.Model.Course;
import com.arifin.daringschool.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssigmentActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_duty)
    Toolbar actionBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.rv_assigment)
    RecyclerView rvAssigment;

    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;
    AssigmentCourseAdapter assigmentAdapter;
    LinearLayoutManager layoutManagerAssigment;
    ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duty);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("Tugas");

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                Intent i = new Intent(AssigmentActivity.this, CreateAssignmentActivity.class);
                startActivity(i);
                finish();
            }
        });

        listAssigment();

        assigmentAdapter = new AssigmentCourseAdapter(courseList, AssigmentActivity.this);
        layoutManagerAssigment = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAssigment.setLayoutManager(layoutManagerAssigment);
        rvAssigment.setAdapter(assigmentAdapter);

    }

    private void listAssigment() {
        courseList = new ArrayList<>();
        for (int i = 0; i<5 ; i++) {
            Course course = new Course();
            course.setAssignmentName("Indonesia");
            course.setAssignmentDate("5 April 2022");
            course.setAssignmentFileTask("File Tugas Indonesia");
            courseList.add(course);
        }

    }

    private void animateFab() {
        if (isOpen) {
            fab.startAnimation(rotateForward);
            fab1.startAnimation(fabClose);
            fab1.setClickable(false);
            isOpen=false;
        } else {
            fab.startAnimation(rotateForward);
            fab1.startAnimation(fabOpen);
            fab1.setClickable(true);
            isOpen=true;
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