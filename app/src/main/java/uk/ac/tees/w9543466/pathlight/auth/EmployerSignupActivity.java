package uk.ac.tees.w9543466.pathlight.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9543466.pathlight.RoutingActivity;
import uk.ac.tees.w9543466.pathlight.databinding.ActivityEmployerSignupBinding;

public class EmployerSignupActivity extends AppCompatActivity {
    private ActivityEmployerSignupBinding binding;
    private EmployerSignupViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployerSignupBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(EmployerSignupViewModel.class);
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
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
        binding.btnContainer.button2.setOnClickListener(v -> viewModel.doSignUp());
    }
}