package com.arifin.daringschool.Activity.UiParent.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.arifin.daringschool.Activity.UiTeacher.Adapter.EBookAdapter;
import com.arifin.daringschool.Activity.UiTeacher.Model.EBook;
import com.arifin.daringschool.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EBookActivityParent extends AppCompatActivity {

    @BindView(R.id.action_br_parent)
    Toolbar actionBar;
    @BindView(R.id.rv_Ebook)
    RecyclerView mRecyclerview;
    @BindView(R.id.progress_circle)
    ProgressBar mProgressCircle;

    private List<EBook> mUpload;
    private EBookAdapter mAdapter;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_parent);
        ButterKnife.bind(this);
//        setSupportActionBar(ActionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("E - Book");

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mUpload = new ArrayList<>();

        mAdapter = new EBookAdapter(EBookActivityParent.this, mUpload);

        mRecyclerview.setAdapter(mAdapter);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("login/Rahman").child("ebook");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot datasnapshot) {

                mUpload.clear();

                for (DataSnapshot postSnapshot : datasnapshot.getChildren()){
                    EBook upload = postSnapshot.getValue(EBook.class);
                    upload.setKey(postSnapshot.getKey());
                    mUpload.add(upload);
                }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(EBookActivityParent.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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