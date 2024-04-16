package com.example.a99;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView; // Added RecyclerView import

import com.example.a99.TaskConstructor;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<TaskConstructor> { // Specify the type of items in the adapter
    private static final String TAG = "TaskListAdapter";
    private Context mContext4;
    private int mResource5;
    private database3 db;

    public TaskListAdapter(@NonNull Context context, int resource, List<TaskConstructor> objects) {
        super(context, resource, objects);
        mContext4 = context;
        mResource5 = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext4).inflate(R.layout.task_adapter, parent, false);
        }

        TextView nameview = convertView.findViewById(R.id.Task_name_view);
        TextView taskduedate = convertView.findViewById(R.id.Task_duedate_view);
        CheckBox completed = convertView.findViewById(R.id.Completion_check);
        Button overflowButton = convertView.findViewById(R.id.overflow_Button444);
        RecyclerView SubtaskRecyclerView = convertView.findViewById(R.id.SubTaskRecyclerView);

        TaskConstructor task = getItem(position); // Get the TaskConstructor at the specified position

        if (task != null) {
            nameview.setText(task.getTask_name());
            String good_duedate;
            good_duedate = formatDueDate(task.getDuedate());
            taskduedate.setText(good_duedate);

            if (completed.isChecked()) { // Ensure this is a CheckBox's isChecked() method
                nameview.setPaintFlags(nameview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            if (completed.isChecked()) {
                nameview.setPaintFlags(nameview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                taskduedate.setPaintFlags(taskduedate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                // Clear the strike-through effect if the task is not completed
                nameview.setPaintFlags(nameview.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                taskduedate.setPaintFlags(taskduedate.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

            completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Update the strike-through effect when the checkbox state changes
                    if (isChecked) {
                        nameview.setPaintFlags(nameview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        taskduedate.setPaintFlags(taskduedate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        nameview.setPaintFlags(nameview.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        taskduedate.setPaintFlags(taskduedate.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });

            overflowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu444(view, position);
                }
            });

            db = new database3(mContext4);
            List<SubtaskConstructor> subtasks = db.getSubtasksForMainTask(task.getId());

            SubtaskListAdapter subtaskAdapter = new SubtaskListAdapter(mContext4, subtasks);
            SubtaskRecyclerView.setAdapter(subtaskAdapter);

            db.close();
        }

        return convertView;
    }



    private void showPopupMenu444(View anchorView, int position) {
        PopupMenu popupMenu = new PopupMenu(mContext4, anchorView);
        popupMenu.inflate(R.menu.menu_task_list);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete_task:
                        // Handle Delete option
                        handleDeleteOption(position);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    private String formatDueDate(String inputDueDate) {
        String formattedDueDate = inputDueDate; // Default to the input if parsing fails

        if (inputDueDate != null) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmm");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy - h:mma");

            try {
                Date date = inputFormat.parse(inputDueDate);
                formattedDueDate = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return formattedDueDate;
    }

    private void handleDeleteOption(int position) {
        TaskConstructor task = getItem(position);

        if (task != null) {
            db = new database3(mContext4);
            db.deleteTask(task.getId());
            remove(task);
            notifyDataSetChanged();
            db.close();
        }
    }
}
