package com.arifin.daringschool.Activity.UiTeacher.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arifin.daringschool.Activity.UiTeacher.Activity.AssigmentActivity;
import com.arifin.daringschool.Activity.UiStudent.Activity.HistoryAbsenActivity;
import com.arifin.daringschool.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragmentTeacher extends Fragment {

    @BindView(R.id.img_profile_dashboard_teacher)
    CircleImageView imgProfile;
    @BindView(R.id.tv_code_profile_teacher)
    TextView tvNameDashboard;
    @BindView(R.id.img_attendance_history_teacher)
    CardView imgAttendanceHistory;
    @BindView(R.id.cv_duty)
    CardView cvDuty;

    DatabaseReference studentRef;
    DatabaseReference teacherRefDashboard;

    public HomeFragmentTeacher() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_teacher, container, false);
        ButterKnife.bind(this, view);

        studentRef = FirebaseDatabase.getInstance().getReference().child("login/Fahmi Wijaya/Absen");
        teacherRefDashboard = FirebaseDatabase.getInstance().getReference("login/Rahman").child("Profile");

        teacherRefDashboard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Picasso.get().load(ds.child("imgProfile").getValue().toString()).into(imgProfile);
                    tvNameDashboard.setText(ds.child("nameProfile").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        cvDuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragmentTeacher.this.getActivity(), AssigmentActivity.class);
                startActivity(i);
            }
        });

        imgAttendanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragmentTeacher.this.getActivity(), HistoryAbsenActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}