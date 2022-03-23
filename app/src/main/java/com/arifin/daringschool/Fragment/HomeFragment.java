package com.arifin.daringschool.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arifin.daringschool.Activity.AssigmentActivity;
import com.arifin.daringschool.Activity.ExaminationActivity;
import com.arifin.daringschool.Activity.GradeActivity;
import com.arifin.daringschool.Activity.HistoryAbsenActivity;
import com.arifin.daringschool.Activity.ScheduleActivity;
import com.arifin.daringschool.Adapter.ScheduleAdapter;
import com.arifin.daringschool.Adapter.ViewScheduleAdpater;
import com.arifin.daringschool.Model.Course;
import com.arifin.daringschool.R;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.compactcalendar_view)
    CompactCalendarView mCompactCalendarView;
    @BindView(R.id.txt_month)
    TextView tvMonth;
    @BindView(R.id.txt_year)
    TextView tvYear;
    @BindView(R.id.tv_date_now)
    TextView tvDateNow;
    @BindView(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @BindView(R.id.img_arrow_right)
    ImageView imgArrowRight;
    @BindView(R.id.img_fingerprint)
    CardView imgFingerprint;
    @BindView(R.id.img_attendance_history)
    CardView imgAttendanceHistory;
    @BindView(R.id.cv_duty)
    CardView cvDuty;
    @BindView(R.id.cv_schedule)
    CardView cvSchedule;
    @BindView(R.id.cv_examination)
    CardView cvExam;
    @BindView(R.id.cv_grade)
    CardView cvGrade;
    @BindView(R.id.rv_schedule)
    RecyclerView rvSchedule;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        tvMonth.setText(simpleDateFormat.format(mCompactCalendarView.getFirstDayOfCurrentMonth()));
        tvYear.setText(simpleDateFormat2.format(mCompactCalendarView.getFirstDayOfCurrentMonth()));

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

        cvDuty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this.getActivity(), AssigmentActivity.class);
                startActivity(i);
            }
        });

        cvSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this.getActivity(), ScheduleActivity.class);
                startActivity(i);
            }
        });

        cvExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this.getActivity(), ExaminationActivity.class);
                startActivity(i);
            }
        });

        cvGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this.getActivity(), GradeActivity.class);
                startActivity(i);
            }
        });

        imgAttendanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeFragment.this.getActivity(), HistoryAbsenActivity.class);
                startActivity(i);
            }
        });

        imgFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HomeFragment.this.getActivity(), R.style.AppTheme_Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_absen_student);
                dialog.setCanceledOnTouchOutside(true);
                EditText etClockIn =  dialog.findViewById(R.id.clockIn);
                EditText etDateIn =  dialog.findViewById(R.id.dateIn);
                Button btnCancel =  dialog.findViewById(R.id.btnCancel);
                Button btnYes =  dialog.findViewById(R.id.btnYes);

                etClockIn.setOnTouchListener((view, motionEvent) -> {
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        // Get Current Time
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        timePickerDialog = new TimePickerDialog(getActivity(),
                                (view12, hourOfDay, minute) -> {
                                    etClockIn.setText(hourOfDay + ":" + minute);
                                    timePickerDialog.dismiss();
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                    return true;
                });

                etDateIn.setOnTouchListener((view, motionEvent) -> {
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        datePickerDialog = new DatePickerDialog(getActivity(),
                                (view1, year, monthOfYear, dayOfMonth) -> {
                                    etDateIn.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    datePickerDialog.dismiss();
                                }, mYear, mMonth, mDay);
                        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                        datePickerDialog.show();
                    }
                    return true;
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(HomeFragment.this.getActivity(), HistoryAbsenActivity.class);
                        startActivity(i);
                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            }

        });

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