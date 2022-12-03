package uk.ac.tees.w9543466.pathlight.employer.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import uk.ac.tees.w9543466.pathlight.R;
import uk.ac.tees.w9543466.pathlight.databinding.ActivityWorkDetailsBinding;
import uk.ac.tees.w9543466.pathlight.employer.NewWorkActivity;

public class WorkDetailActivity extends AppCompatActivity {

    private HomeViewModel viewModel;
    private ActivityWorkDetailsBinding binding;
    private final WorkDetailAdapter adapter = new WorkDetailAdapter();
    public static final String BUNDLE_KEY_SELECTED_WORK_ID = "work_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = ActivityWorkDetailsBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        long workId = getIntent().getExtras().getLong(BUNDLE_KEY_SELECTED_WORK_ID);
        updateSelectedWork(workId);
        setupClickers();
        setupAdapter();
        setContentView(binding.getRoot());
    }

    private void updateSelectedWork(long workId) {
        viewModel.onItemSelected(workId);
    }

    private void setupAdapter() {
        binding.workDetailsLayout.recyclerView.setAdapter(adapter);
        viewModel.getSelectedWork().observe(this, data -> {
            adapter.submitList(data.displayableList());
        });
    }

    private void setupClickers() {
        binding.workDetailsLayout.button2.setOnClickListener(v -> {
            //navigate to applications
        });
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
        binding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.editWork) {
                startWorkEditActivity();
            } else if (item.getItemId() == R.id.deleteWork) {
                showDeleteConfirmation();
            }
            return false;
        });
    }

    private void showDeleteConfirmation() {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
        dialog.setTitle("Delete work?");
        dialog.setMessage("Please confirm if you really want to delete this work. All application against this work will also be cancelled");
        dialog.setPositiveButton("Delete", (dialog1, which) -> {
            viewModel.deleteSelectedWork();
            dialog1.dismiss();
        });
        dialog.setNegativeButton("Cancel", (dialog1, which) -> {
            dialog1.dismiss();
        });
        dialog.show();
    }

    private void startWorkEditActivity() {
        Intent intent = new Intent(this, NewWorkActivity.class);
        intent.putExtra(NewWorkActivity.BUNDLE_KEY_EDIT_MODE, true);
        startActivity(intent);
    }
}
