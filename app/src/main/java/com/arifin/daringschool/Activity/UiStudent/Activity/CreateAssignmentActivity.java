package com.arifin.daringschool.Activity.UiStudent.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.arifin.daringschool.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAssignmentActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_create_assignment)
    Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assignment);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("Create Assignment");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(CreateAssignmentActivity.this, AssigmentActivity.class);
                startActivity(i);
                finish();
        }
        return true;
    }
}