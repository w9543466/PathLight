package uk.ac.tees.w9543466.pathlight.worker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
        setUpClickers();
        setupAdapter();
        profileVm.getWorkerProfile();
    }

    private void setupAdapter() {
        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.rvWorkList.addItemDecoration(decor);
        adapter.setListener(this::promptProposedRate);
        adapter.setItemClickListener(this::navigateToMaps);
        binding.rvWorkList.setAdapter(adapter);
        viewModel.getWorkLiveData().observe(this, adapter::submitList);
    }

    private void setUpClickers() {
        binding.viewApplications.setOnClickListener(v -> startActivity(new Intent(WorkerHome.this, WorkerApplications.class)));
        binding.noDataView.btnAction.setOnClickListener(v -> viewModel.getAllWorks());
        binding.errorView.btnAction.setOnClickListener(v -> viewModel.getAllWorks());
        binding.settings.setOnClickListener(v -> startActivity(new Intent(this, WorkerProfileActivity.class)));
    }

    private void navigateToMaps(int position) {
        WorkDto work = viewModel.getWorkData(position);
        Uri gmmIntentUri = Uri.parse("geo:" + work.getLat() + "," + work.getLng());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Google map is not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void promptProposedRate(int position) {
        WorkDto work = viewModel.getWorkData(position);
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
        dialog.setTitle("Accept the job?");
        dialog.setMessage("Do you want to accept the job with the total rate or do you want to propose a different amount?");
        EditText inputField = new EditText(this);
        inputField.setInputType(EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
        inputField.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        dialog.setView(inputField);
        inputField.setText(String.valueOf(work.getTotalRate().get()));
        dialog.setPositiveButton("Accept / Propose", (dialog1, which) -> applyForWork(position, inputField.getText().toString()));
        dialog.setNegativeButton("Cancel", (dialog1, which) -> dialog1.dismiss());
        dialog.show();
    }

    private void applyForWork(int selectedWorkPos, String proposedRateText) {
        double proposedRate = Double.parseDouble(proposedRateText);
        viewModel.onApplicationSubmitted(selectedWorkPos, proposedRate);
    }
}
