package com.arifin.daringschool.Activity.UiTeacher.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arifin.daringschool.Activity.UiTeacher.Activity.AssigmentActivity;
import com.arifin.daringschool.Activity.UiStudent.Activity.HistoryAbsenActivity;
import com.arifin.daringschool.Activity.UiTeacher.Activity.EbookActivity;
import com.arifin.daringschool.Activity.UiTeacher.Activity.ScheduleTeacherActivity;
import com.arifin.daringschool.Activity.UiTeacher.Activity.ScoreTeacherActivity;
import com.arifin.daringschool.Activity.UiTeacher.Adapter.AssigmentCourseAdapter;
import com.arifin.daringschool.Activity.UiTeacher.Adapter.ScheduleHomeTeacherAdapter;
import com.arifin.daringschool.Activity.UiTeacher.Model.Assignment;
import com.arifin.daringschool.R;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    @BindView(R.id.cv_eBook)
    CardView cvEbok;
    @BindView(R.id.cv_grade)
    CardView cvScore;
    @BindView(R.id.cv_schedule)
    CardView cvSchedule;
    @BindView(R.id.rv_schedule)
    RecyclerView rvSchedule;
    @BindView(R.id.compactcalendar_view)
    CompactCalendarView mCompactCalendarView;
    @BindView(R.id.txt_month)
    TextView tvMonth;
    @BindView(R.id.txt_year)
    TextView tvYear;
    @BindView(R.id.tv_date_now)
    TextView tvDateNow;
    @BindView(R.id.tv_viewAll)
    TextView tvViewAll;
    @BindView(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @BindView(R.id.img_arrow_right)
    ImageView imgArrowRight;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy", Locale.getDefault());
    SimpleDateFormat dateFormatDay = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM", Locale.getDefault());

    DatabaseReference studentRef;
    DatabaseReference teacherRefDashboard;

    private ScheduleHomeTeacherAdapter assigmentAdapter;
    private List<Assignment> assignmentList;
    private DatabaseReference mDatabaseRef;

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

        tvMonth.setText(simpleDateFormat.format(mCompactCalendarView.getFirstDayOfCurrentMonth()));
        tvYear.setText(simpleDateFormat2.format(mCompactCalendarView.getFirstDayOfCurrentMonth()));

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

        imgArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompactCalendarView.scrollRight();
            }
        });
        imgArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompactCalendarView.scrollLeft();
            }
        });

        mCompactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                tvDateNow.setText(dateFormatDay.format(dateClicked) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dateFormatForMonth.format(dateClicked) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + simpleDateFormat2.format(dateClicked));
                tvDateNow.setVisibility(View.VISIBLE);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });

        assignmentList = new ArrayList<>();

        rvSchedule.setHasFixedSize(true);
        rvSchedule.setLayoutManager(new LinearLayoutManager(getActivity()));

        assigmentAdapter = new ScheduleHomeTeacherAdapter( getActivity(), assignmentList);
        rvSchedule.setAdapter(assigmentAdapter);



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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        cvDuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragmentTeacher.this.getActivity(), AssigmentActivity.class);
                startActivity(i);
            }
        });

        tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragmentTeacher.this.getActivity(), ScheduleTeacherActivity.class);
                startActivity(i);
            }
        });

        cvSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragmentTeacher.this.getActivity(), ScheduleTeacherActivity.class);
                startActivity(i);
            }
        });

        cvEbok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragmentTeacher.this.getActivity(), EbookActivity.class);
                startActivity(i);
            }
        });

        cvScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragmentTeacher.this.getActivity(), ScoreTeacherActivity.class);
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