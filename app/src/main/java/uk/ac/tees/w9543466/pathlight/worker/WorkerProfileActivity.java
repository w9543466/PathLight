package uk.ac.tees.w9543466.pathlight.worker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.ArrayList;

import uk.ac.tees.w9543466.pathlight.KeyValueModel;
import uk.ac.tees.w9543466.pathlight.RoutingActivity;
import uk.ac.tees.w9543466.pathlight.databinding.ActivityProfileBinding;
import uk.ac.tees.w9543466.pathlight.employer.profile.ProfileDetailAdapter;
import uk.ac.tees.w9543466.pathlight.employer.profile.ProfileViewModel;

public class WorkerProfileActivity extends AppCompatActivity {

    private ProfileViewModel viewModel;
    private ActivityProfileBinding binding;
    private final ProfileDetailAdapter adapter = new ProfileDetailAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setupAdapter();
        observeProfileData();
        setupClickers();
    }

    private void setupClickers() {
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
        binding.topAppBar.setOnMenuItemClickListener(item -> {
//            if (item.getItemId() == R.id.editWork) {
//
//            }
            return false;
        });
        binding.btnLogout.setOnClickListener(v -> {
            viewModel.logout();
            finish();
            Intent targetIntent = new Intent(WorkerProfileActivity.this, RoutingActivity.class);
            targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(targetIntent);
        });
    }

    private void observeProfileData() {
        viewModel.getProfileDetails().observe(this, data -> {
            if (data != null) {
                ArrayList<KeyValueModel> details = viewModel.getWorkerProfileDetailsList(data);
                adapter.submitList(details);
            }
        });
        viewModel.getWorkerProfile();
    }

    private void setupAdapter() {
        binding.rvProfileDetails.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.rvProfileDetails.setAdapter(adapter);
    }
}
