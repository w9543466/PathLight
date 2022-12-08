package uk.ac.tees.w9543466.pathlight.worker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityWorkerApplicationsBinding;

public class WorkerApplications extends AppCompatActivity {

    private WorkerViewModel viewModel;
    private ActivityWorkerApplicationsBinding binding;
    private final WorkerApplicationAdapter adapter = new WorkerApplicationAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WorkerViewModel.class);
        binding = ActivityWorkerApplicationsBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
        setUpClicks();
        setupAdapter();
    }

    private void setupAdapter() {
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.rvApplicationsList.addItemDecoration(decor);
        binding.rvApplicationsList.setAdapter(adapter);
        viewModel.getApplicationsLiveData().observe(this, adapter::submitList);
        getApplications();
    }

    private void setUpClicks() {
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
        binding.errorView.btnAction.setOnClickListener(v -> getApplications());
        binding.noDataView.btnAction.setOnClickListener(v -> getApplications());
    }

    private void getApplications() {
        viewModel.getWorkApplications();
    }
}
