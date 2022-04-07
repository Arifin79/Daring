package com.arifin.daringschool.Activity.UiTeacher.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arifin.daringschool.Activity.UiTeacher.Adapter.AssigmentCourseAdapter;
import com.arifin.daringschool.Activity.UiTeacher.Adapter.ScoreTeacherAdapter;
import com.arifin.daringschool.Activity.UiTeacher.Model.Assignment;
import com.arifin.daringschool.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreTeacherActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_score)
    Toolbar actionBar;
    @BindView(R.id.rv_assigment)
    RecyclerView rvAssigment;
    @BindView(R.id.progress_circle)
    ProgressBar mProgressCircle;

    boolean isOpen = false;
    private ScoreTeacherAdapter assigmentAdapter;
    private List<Assignment> assignmentList;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_teacher);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("Nilai");

        assignmentList = new ArrayList<>();

        rvAssigment.setHasFixedSize(true);
        rvAssigment.setLayoutManager(new LinearLayoutManager(this));

        assigmentAdapter = new ScoreTeacherAdapter( ScoreTeacherActivity.this, assignmentList);
        rvAssigment.setAdapter(assigmentAdapter);



        mDatabaseRef = FirebaseDatabase.getInstance().getReference("login/Rahman").child("Assignment");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                assignmentList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Assignment upload = postSnapshot.getValue(Assignment.class);
                    upload.setKey(postSnapshot.getKey());
                    assignmentList.add(upload);
                }

                assigmentAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ScoreTeacherActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

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