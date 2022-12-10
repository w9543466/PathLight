package uk.ac.tees.w9543466.pathlight.employer.applications;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
        observeApplicationAction();
    }

    private void setupAdapter() {
        binding.rvApplications.setAdapter(adapter);
        adapter.setCallback(viewModel::onApplicationAccepted);
    }

    private void observeApplicationList() {
        viewModel.getApplicationLiveData().observe(this, adapter::submitList);
    }

    private void observeApplicationAction() {
        viewModel.getApplicationActionLiveData().observe(this, data -> {
            if (data.isSuccess()) {
                showSuccessDialog();
            } else {
                Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSuccessDialog() {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
        dialog.setTitle("Success");
        dialog.setMessage("Application accepted");
        dialog.setNeutralButton("Dismiss", (dialog1, which) -> {
            dialog1.dismiss();
            finish();
        });
        dialog.create().show();
    }

    private void setupClickers() {
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
        binding.errorView.btnAction.setOnClickListener(v -> viewModel.getApplication());
        binding.noDataView.btnAction.setOnClickListener(v -> viewModel.getApplication());
    }
}