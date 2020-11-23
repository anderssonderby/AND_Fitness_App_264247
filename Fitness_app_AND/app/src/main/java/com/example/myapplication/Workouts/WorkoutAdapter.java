package com.example.myapplication.Workouts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Shared.WorkoutModel;
import com.example.myapplication.R;

import java.text.DateFormat;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private List<WorkoutModel> workouts;

    public WorkoutAdapter(List<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.ViewHolder holder, int position) {
        holder.workoutDesc.setText(workouts.get(position).get_description());
        holder.workoutMuscleGroup.setText("Muscle group: "+ workouts.get(position).getMuscleGroup());
        DateFormat dateFormat = DateFormat.getDateInstance();
        holder.workoutDate.setText(dateFormat.format(workouts.get(position).get_workoutDate()));
    }

    @Override
    public int getItemCount() {
        if (workouts == null) return 0;
        return workouts.size();
    }

    public void setWorkouts(List<WorkoutModel> workouts)  {
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView workoutDesc;
        TextView workoutMuscleGroup;
        TextView workoutDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutDesc = itemView.findViewById(R.id.workout_desc);
            workoutMuscleGroup = itemView.findViewById(R.id.workout_muscle);
            workoutDate = itemView.findViewById(R.id.workout_date);
        }
    }
}
