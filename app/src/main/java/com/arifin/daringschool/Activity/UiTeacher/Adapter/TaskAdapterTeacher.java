package com.arifin.daringschool.Activity.UiTeacher.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Activity.UiTeacher.Fragment.TodoFragmentTeacher;
import com.arifin.daringschool.Activity.UiStudent.Adapter.CreateTaskBottomSheetFragmentStudent;
import com.arifin.daringschool.Database.DatabaseClient;
import com.arifin.daringschool.Model.Task;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapterTeacher extends RecyclerView.Adapter<TaskAdapterTeacher.TaskViewHolder> {

    private TodoFragmentTeacher fragment;
    private LayoutInflater inflater;
    private List<Task> taskList;
    Context mContext;
    public SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy", Locale.US);
    public SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-M-yyyy", Locale.US);
    Date date = null;
    String outputDateString = null;
    CreateTaskBottomSheetFragmentTeacher.setRefreshListener setRefreshListener;

    public TaskAdapterTeacher(TodoFragmentTeacher context, List<Task> taskList, CreateTaskBottomSheetFragmentTeacher.setRefreshListener setRefreshListener) {
        this.fragment = context;
        this.taskList = taskList;
        this.setRefreshListener = setRefreshListener;
//        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @NotNull
    @Override
    public TaskAdapterTeacher.TaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent,false);
        return new TaskAdapterTeacher.TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskAdapterTeacher.TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.title.setText(task.getTaskTitle());
        holder.description.setText(task.getTaskDescrption());
        holder.time.setText(task.getLastAlarm());
        holder.status.setText(task.isComplete() ? "COMPLETED" : "UPCOMING");
        holder.options.setOnClickListener(view -> showPopUpMenu(view, position));

        try {
            date = inputDateFormat.parse(task.getDate());
            outputDateString = dateFormat.format(date);

            String[] items1 = outputDateString.split(" ");
            String day = items1[0];
            String dd = items1[1];
            String month = items1[2];

            holder.day.setText(day);
            holder.date.setText(dd);
            holder.month.setText(month);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPopUpMenu(View view, int position) {
        final Task task = taskList.get(position);
        PopupMenu popupMenu = new PopupMenu(fragment.getActivity(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuDelete:
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(fragment.getActivity(), R.style.AppTheme_Dialog);
                    alertDialogBuilder.setTitle("Confirmation").setMessage("Are you sure you want to delete the task")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                deleteTaskFromId(task.getTaskId(), position);
                            })
                            .setNegativeButton("No", (dialog, which) -> dialog.cancel()).show();
                    break;
                case R.id.menuUpdate:
                    CreateTaskBottomSheetFragmentTeacher createTaskBottomSheetFragmentTeacher = new CreateTaskBottomSheetFragmentTeacher();
                    createTaskBottomSheetFragmentTeacher.setTaskId(task.getTaskId(), true,  fragment, fragment);
                    createTaskBottomSheetFragmentTeacher.show(fragment.getActivity().getSupportFragmentManager(), createTaskBottomSheetFragmentTeacher.getTag());
                    break;
                case R.id.menuComplete:
                    AlertDialog.Builder completeAlertDialog = new AlertDialog.Builder(fragment.getActivity(), R.style.AppTheme_Dialog);
                    completeAlertDialog.setTitle("Completed the task!..").setMessage("Are you sure you want to mark this task as completed").
                            setPositiveButton("Yes", (dialog, which) -> showCompleteDialog(task.getTaskId(), position))
                            .setNegativeButton("No", (dialog, which) -> dialog.cancel()).show();
                    break;
            }
            return false;
        });
        popupMenu.show();
    }

    public void showCompleteDialog(int taskId, int position) {
        Dialog dialog = new Dialog(fragment.getActivity(),R.style.AppTheme);
        dialog.setContentView(R.layout.dialog_complete_theme);
        Button close = dialog.findViewById(R.id.closeButton);
        close.setOnClickListener(view -> {
            deleteTaskFromId(taskId, position);
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void deleteTaskFromId(int taskId, int position) {
        class GetSavedTasks extends AsyncTask<Void, Void, List<Task>> {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                DatabaseClient.getInstance(fragment.getActivity())
                        .getAppDatabase()
                        .dataBaseAction()
                        .deleteTaskFromId(taskId);
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                removeAtPosition(position);
                setRefreshListener.refresh();
            }
        }
        GetSavedTasks savedTasks = new GetSavedTasks();
        savedTasks.execute();
    }

    private void removeAtPosition(int position) {
        taskList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, taskList.size());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.day)
        TextView day;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.month)
        TextView month;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.options)
        ImageView options;
        @BindView(R.id.time)
        TextView time;

        TaskViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
