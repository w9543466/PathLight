package uk.ac.tees.w9543466.pathlight.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9543466.pathlight.RoutingActivity;
import uk.ac.tees.w9543466.pathlight.databinding.ActivityWorkerSignupBinding;
import uk.ac.tees.w9543466.pathlight.utils.DateTimePickerUtil;

public class WorkerSignupActivity extends AppCompatActivity {

    private ActivityWorkerSignupBinding binding;
    private WorkerSignupViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkerSignupBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(WorkerSignupViewModel.class);
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
        setupClickers();
        observeSignupResponse();
    }

    private void observeSignupResponse() {
        viewModel.getSignupResponse().observe(this, response -> {
            if (response != null) {
                if (response.isSuccess()) {
                    finish();
                    startActivity(new Intent(this, RoutingActivity.class));
                } else {
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupClickers() {
        binding.etDob.setOnClickListener(v -> DateTimePickerUtil.openDatePicker(getSupportFragmentManager(), "Select your date of birth", false, date -> {
            viewModel.onDobSelected(date.getTime());
        }));
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
        binding.btnContainer.button2.setOnClickListener(v -> viewModel.doSignUp());
    }
}
