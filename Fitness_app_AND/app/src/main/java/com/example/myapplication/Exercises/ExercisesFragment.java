package com.example.myapplication.Exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.RetroFit.ExerciseResponse;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment implements OnListItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ExercisesViewModel viewModel;
    private ExerciseAdapter adapter;
    private List<ExerciseResponse.Result> exercisesList = new ArrayList<>();

    public ExercisesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExercisesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExercisesFragment newInstance(String param1, String param2) {
        ExercisesFragment fragment = new ExercisesFragment();
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
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        // Recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.exercises_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        adapter = new ExerciseAdapter(exercisesList, this);
        recyclerView.setAdapter(adapter);

        // Initialise ViewModel and update adapter with LiveData
        viewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        viewModel.getExercises().observe(getViewLifecycleOwner(), new Observer<List<ExerciseResponse.Result>>() {
            @Override
            public void onChanged(List<ExerciseResponse.Result> exercises) {
                adapter.setExercises(exercises);
                exercisesList = exercises;
            }
        });

        return view;
    }

    @Override
    public void onListItemClick(int itemIndex) {

        Bundle bundle = new Bundle();
        bundle.putString("name", exercisesList.get(itemIndex).name);
        bundle.putString("description", exercisesList.get(itemIndex).description);

        Navigation.findNavController(getView()).navigate(R.id.action_exercisesFragment_to_exerciseDetailFragment, bundle);
    }
}