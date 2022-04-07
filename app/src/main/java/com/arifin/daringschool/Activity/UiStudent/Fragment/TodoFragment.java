package com.arifin.daringschool.Activity.UiStudent.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.arifin.daringschool.Activity.UiStudent.Adapter.TaskAdapterStudent;
import com.arifin.daringschool.Activity.UiStudent.Adapter.CreateTaskBottomSheetFragmentStudent;
import com.arifin.daringschool.CalendarBottomSheetFragment.ShowCalenderViewBottomSheet;
import com.arifin.daringschool.CalendarBottomSheetFragment.AlarmBroadcastReceiver;
import com.arifin.daringschool.Database.DatabaseClient;
import com.arifin.daringschool.Model.Task;
import com.arifin.daringschool.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoFragment extends Fragment implements CreateTaskBottomSheetFragmentStudent.setRefreshListener {

    @BindView(R.id.taskRecycler)
    RecyclerView taskRecycler;
    @BindView(R.id.addTask)
    TextView addTask;
    TaskAdapterStudent taskAdapter;
    List<Task> tasks = new ArrayList<>();
    @BindView(R.id.noDataImage)
    ImageView noDataImage;
    @BindView(R.id.calendar)
    ImageView calendar;
    private TodoFragment fragment;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1 = inflater.inflate(R.layout.fragment_todo, container, false);
        ButterKnife.bind(this, view1);
        setUpAdapter();
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ComponentName receiver = new ComponentName(this.getActivity(), AlarmBroadcastReceiver.class);
        PackageManager pm = this.getActivity().getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        Glide.with(getActivity().getApplicationContext()).load(R.drawable.bgnotodo).into(noDataImage);

        addTask.setOnClickListener(view -> {
            CreateTaskBottomSheetFragmentStudent createTaskBottomSheetFragmentStudent = new CreateTaskBottomSheetFragmentStudent();
            createTaskBottomSheetFragmentStudent.setTaskId(0, false, this, TodoFragment.this);
            createTaskBottomSheetFragmentStudent.show(getActivity().getSupportFragmentManager(), createTaskBottomSheetFragmentStudent.getTag());
        });

        getSavedTasks();

        calendar.setOnClickListener(view -> {
            ShowCalenderViewBottomSheet showCalendarViewBottomSheet = new ShowCalenderViewBottomSheet();
            showCalendarViewBottomSheet.show(getActivity().getSupportFragmentManager(), showCalendarViewBottomSheet.getTag());
        });


        return view1;
    }
    public void setUpAdapter() {
        taskAdapter = new TaskAdapterStudent(this, tasks, TodoFragment.this);
        taskRecycler.setLayoutManager(new LinearLayoutManager( this.getActivity()));
        taskRecycler.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();
    }

    private void getSavedTasks() {


        class GetSavedTasks extends AsyncTask<Void, Void, List<Task>> {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                tasks = DatabaseClient
                        .getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .dataBaseAction()
                        .getAllTasksList();
                return tasks;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                noDataImage.setVisibility(tasks.isEmpty() ? View.VISIBLE : View.GONE);
                setUpAdapter();
            }
        }

        GetSavedTasks savedTasks = new GetSavedTasks();
        savedTasks.execute();
    }

    @Override
    public void refresh() {
        getSavedTasks();
    }
}