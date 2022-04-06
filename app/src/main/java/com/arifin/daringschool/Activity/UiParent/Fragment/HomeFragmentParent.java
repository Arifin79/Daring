package com.arifin.daringschool.Activity.UiParent.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Activity.UiStudent.Activity.EBookActivityStudent;
import com.arifin.daringschool.Activity.UiStudent.Activity.GradeActivity;
import com.arifin.daringschool.Activity.UiStudent.Activity.HistoryAbsenActivity;
import com.arifin.daringschool.Activity.UiStudent.Activity.ScheduleActivity;
import com.arifin.daringschool.Activity.UiStudent.Adapter.ViewScheduleAdpater;
import com.arifin.daringschool.Activity.UiTeacher.Activity.ExaminationActivity;
import com.arifin.daringschool.Model.Course;
import com.arifin.daringschool.R;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.firebase.auth.FirebaseAuth;
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
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragmentParent extends Fragment {

   @BindView(R.id.compactcalendar_view) CompactCalendarView mCompactCalendarView;
   @BindView(R.id.txt_month) TextView tvMonth;
   @BindView(R.id.txt_year) TextView tvYear;
   @BindView(R.id.tv_date_now) TextView tvDateNow;
   @BindView(R.id.img_arrow_left) ImageView imgArrowLeft;
   @BindView(R.id.img_arrow_right) ImageView imgArrowRight;
   @BindView(R.id.img_attendance_history) CardView imgAttendanceHistory;
   @BindView(R.id.cv_eBook) CardView cvEBook;
   @BindView(R.id.cv_schedule) CardView cvSchedule;
   @BindView(R.id.cv_grade) CardView cvGrade;
   @BindView(R.id.cv_examination) CardView cvExam;
   @BindView(R.id.img_profile_dashboard) CircleImageView imgProfile;
   @BindView(R.id.rv_schedule) RecyclerView rvSchedule;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy", Locale.getDefault());
    SimpleDateFormat dateFormatDay = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM", Locale.getDefault());
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    int mYear, mMonth, mDay;
    int mHour, mMinute;
    ViewScheduleAdpater viewScheduleAdpater;
    LinearLayoutManager layoutManagerAssigment;
    ArrayList<Course> courseList;
    TextView urlProfile;

    DatabaseReference studentRef;
    DatabaseReference studentRefDashboard;
    private FirebaseAuth mAuth;

    public HomeFragmentParent() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_parent, container, false);
        ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();

        tvMonth.setText(simpleDateFormat.format(mCompactCalendarView.getFirstDayOfCurrentMonth()));
        tvYear.setText(simpleDateFormat2.format(mCompactCalendarView.getFirstDayOfCurrentMonth()));

        studentRef = FirebaseDatabase.getInstance().getReference("login/Fahmi Wijaya").child("Absen");

        studentRefDashboard = FirebaseDatabase.getInstance().getReference("login/Ahmad Satria Wijaya").child("Profile");

        studentRefDashboard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Picasso.get().load(ds.child("imgProfile").getValue().toString()).into(imgProfile);


                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });
        listSchedule();

        viewScheduleAdpater = new ViewScheduleAdpater(courseList, getContext());
        layoutManagerAssigment = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvSchedule.setLayoutManager(layoutManagerAssigment);
        rvSchedule.setAdapter(viewScheduleAdpater);

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

        cvEBook.setOnClickListener(v -> startActivity(new Intent(getActivity(), EBookActivityStudent.class)));
        cvSchedule.setOnClickListener(v -> startActivity(new Intent(getActivity(), ScheduleActivity.class)));
        cvExam.setOnClickListener(v -> startActivity(new Intent(getActivity(), ExaminationActivity.class)));
        cvGrade.setOnClickListener(v -> startActivity(new Intent(getActivity(), GradeActivity.class)));
        imgAttendanceHistory.setOnClickListener(v -> startActivity(new Intent(getActivity(), HistoryAbsenActivity.class)));

        return view;
    }

    private void listSchedule() {
        courseList = new ArrayList<>();
        for (int i = 0; i<3 ; i++) {
            Course course = new Course();
            course.setAssignmentName("Indonesia");
            courseList.add(course);
        }

    }
}