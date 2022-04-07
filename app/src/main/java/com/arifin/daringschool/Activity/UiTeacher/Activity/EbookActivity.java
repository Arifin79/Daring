package com.arifin.daringschool.Activity.UiTeacher.Activity;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arifin.daringschool.Activity.UiTeacher.Adapter.EBookAdapter;
import com.arifin.daringschool.Activity.UiTeacher.Model.EBook;
import com.arifin.daringschool.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EbookActivity extends AppCompatActivity implements EBookAdapter.OnItemClickListener {

    @BindView(R.id.action_bar_cp)
    Toolbar actionBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.rv_Ebook)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_circle)
    ProgressBar mProgressCircle;

    private List<EBook> mUploads;
    private EBookAdapter mAdapter;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;

    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("E - Book");

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                Intent i = new Intent(EbookActivity.this, CreateEbookActivity.class);
                startActivity(i);
                finish();
            }
        });

        mUploads = new ArrayList<>();

        mAdapter = new EBookAdapter(EbookActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(EbookActivity.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("login/Rahman").child("ebook");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    EBook upload = postSnapshot.getValue(EBook.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EbookActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

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
    public void onItemClick(int position) {
        Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        EBook selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(EbookActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
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