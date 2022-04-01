package com.arifin.daringschool.Activity.UiStudent.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.arifin.daringschool.Activity.UiStudent.Adapter.HistoryAbsenAdapter;
import com.arifin.daringschool.Model.Absen;
import com.arifin.daringschool.Model.Student;
import com.arifin.daringschool.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAbsenActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_history)
    Toolbar actionBar;

    @BindView(R.id.rv_history_absen)
    RecyclerView rvHistoryAbsen;

    HistoryAbsenAdapter historyAdapter;
    ArrayList<Student> absenList;

    DatabaseReference studentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_absen);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("History Absen");


        studentRef = FirebaseDatabase.getInstance().getReference().child("login/Fahmi Wijaya/Absen");

        rvHistoryAbsen.setHasFixedSize(true);
        rvHistoryAbsen.setLayoutManager(new LinearLayoutManager(this));

        absenList = new ArrayList<>();
        historyAdapter = new HistoryAbsenAdapter(this, absenList);
        rvHistoryAbsen.setAdapter(historyAdapter);

        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot studentDatasnap : snapshot.getChildren()){
                    Student student = studentDatasnap.getValue(Student.class);
                    absenList.add(student);
                }
                historyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

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