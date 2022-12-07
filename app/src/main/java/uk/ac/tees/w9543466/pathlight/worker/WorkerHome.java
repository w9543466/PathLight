package uk.ac.tees.w9543466.pathlight.worker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityWorkerBinding;
import uk.ac.tees.w9543466.pathlight.employer.profile.ProfileViewModel;

public class WorkerHome extends AppCompatActivity {

    private WorkerViewModel viewModel;
    private ProfileViewModel profileVm;
    private ActivityWorkerBinding binding;
    private final WorkerJobsAdapter adapter = new WorkerJobsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WorkerViewModel.class);
        profileVm = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = ActivityWorkerBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.setProfileVm(profileVm);
        setContentView(binding.getRoot());
        setUpClicks();
        setupAdapter();
        profileVm.getWorkerProfile();
    }

    private void setupAdapter() {
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.rvWorkList.addItemDecoration(decor);
        binding.rvWorkList.setAdapter(adapter);
        viewModel.getWorkLiveData().observe(this, adapter::submitList);
    }

    private void setUpClicks() {
        binding.viewApplications.setOnClickListener(v -> {
            //TODO
            //startActivity(new Intent(WorkerHome.this, WorkerApplications.class));
        });
        adapter.setListener(position -> viewModel.onApplicationSubmitted(position));
    }
}
