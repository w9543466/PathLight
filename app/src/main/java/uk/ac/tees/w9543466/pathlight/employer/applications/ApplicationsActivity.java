package uk.ac.tees.w9543466.pathlight.employer.applications;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityApplicationsBinding;

public class ApplicationsActivity extends AppCompatActivity {
    public static final String BUNDLE_KEY_WORK_ID = "selectedWorkId";
    private ApplicationsViewModel viewModel;
    private ActivityApplicationsBinding binding;
    private final ApplicationsAdapter adapter = new ApplicationsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ApplicationsViewModel.class);
        binding = ActivityApplicationsBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
        setupClickers();
        observeApplicationList();
        setupAdapter();
        viewModel.setSelectedWorkId(getIntent().getLongExtra(BUNDLE_KEY_WORK_ID, -1));
    }

    private void setupAdapter() {
        binding.rvApplications.setAdapter(adapter);
    }

    private void observeApplicationList() {
        viewModel.getApplicationLiveData().observe(this, adapter::submitList);
    }

    private void setupClickers() {
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
        binding.errorView.btnAction.setOnClickListener(v -> viewModel.getApplication());
    }
}