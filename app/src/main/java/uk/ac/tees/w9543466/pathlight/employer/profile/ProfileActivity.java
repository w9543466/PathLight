package uk.ac.tees.w9543466.pathlight.employer.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.ArrayList;

import uk.ac.tees.w9543466.pathlight.KeyValueModel;
import uk.ac.tees.w9543466.pathlight.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

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
    }

    private void observeProfileData() {
        viewModel.getProfileDetails().observe(this, data -> {
            if (data != null) {
                ArrayList<KeyValueModel> details = viewModel.getProfileDetailsList(data);
                adapter.submitList(details);
            }
        });
        viewModel.getEmployerProfile();
    }

    private void setupAdapter() {
        binding.rvProfileDetails.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.rvProfileDetails.setAdapter(adapter);
    }
}
