package com.example.myapplication.Workouts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutLogFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private WorkoutsViewModel viewModel;

    public WorkoutLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutLogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutLogFragment newInstance(String param1, String param2) {
        WorkoutLogFragment fragment = new WorkoutLogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_log, container, false);

        // Initialise ViewModel
        viewModel = new ViewModelProvider(this).get(WorkoutsViewModel.class);

        // TextFields
        EditText muscleGroupField = view.findViewById(R.id.editTextMuscleGroup);
        EditText descriptionField = view.findViewById(R.id.editTextDescription);

        // CalendarView
        CalendarView calendar = view.findViewById(R.id.calendarView);

        // Log button
        Button logButton = view.findViewById(R.id.button_log);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!TextUtils.isEmpty(muscleGroupField.getText().toString())) && (!TextUtils.isEmpty(descriptionField.getText().toString()))) {
                    viewModel.addWorkout(descriptionField.getText().toString(), muscleGroupField.getText().toString(), new Date(calendar.getDate()));
                    Toast.makeText(getContext(), "Workout logged!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(), "All fields must be specified to log new workout", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Back button
        Button backButton = view.findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_workoutLogFragment_to_workoutsFragment);
            }
        });

        return view;
    }
}