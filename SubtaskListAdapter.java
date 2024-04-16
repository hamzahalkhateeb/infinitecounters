package com.example.a99;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubtaskListAdapter extends RecyclerView.Adapter<SubtaskListAdapter.SubtaskViewHolder> {
    private List<SubtaskConstructor> subtasks;
    private Context mContext45;

    public SubtaskListAdapter(Context context, List<SubtaskConstructor> subtasks) {
        this.mContext45 = context;
        this.subtasks = subtasks;
    }

    @NonNull
    @Override
    public SubtaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subtask_adapter, parent, false);
        return new SubtaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubtaskViewHolder holder, int position) {
        SubtaskConstructor subtask = subtasks.get(position);
        holder.nameview.setText(subtask.getSubtaskName());
        boolean isCompleted = subtask.isCompleted() == 1; // Check if the subtask is completed

        // Apply the strike-through effect based on subtask completion
        if (isCompleted) {
            holder.nameview.setPaintFlags(holder.nameview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.nameview.setPaintFlags(holder.nameview.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // Set the completion checkbox state
        holder.completionBox.setChecked(isCompleted);

        holder.completionBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the strike-through effect and subtask completion status when the checkbox state changes
                if (isChecked) {
                    holder.nameview.setPaintFlags(holder.nameview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    subtask.setCompleted(1); // Mark the subtask as completed
                } else {
                    holder.nameview.setPaintFlags(holder.nameview.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    subtask.setCompleted(0); // Mark the subtask as not completed
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return subtasks.size();
    }

    public class SubtaskViewHolder extends RecyclerView.ViewHolder {
        TextView nameview;
        CheckBox completionBox;

        public SubtaskViewHolder(View itemView) {
            super(itemView);
            nameview = itemView.findViewById(R.id.subtask_name_view);

            completionBox = itemView.findViewById(R.id.subtask_Completion_check);
        }
    }
}
