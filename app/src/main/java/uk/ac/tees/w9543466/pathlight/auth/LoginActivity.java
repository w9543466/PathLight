package uk.ac.tees.w9543466.pathlight.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityLoginBinding;
import uk.ac.tees.w9543466.pathlight.employer.home.EmployerHome;
import uk.ac.tees.w9543466.pathlight.worker.WorkerHome;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
        setUpClicks();
    }

    private void observeLogin() {
        viewModel.getLoginLiveData().observe(this, response -> {
            if (response.isSuccess()) {
                Intent targetIntent;
                if (response.getRole() == UserRole.EMPLOYER) {
                    targetIntent = new Intent(LoginActivity.this, EmployerHome.class);
                } else {
                    targetIntent = new Intent(LoginActivity.this, WorkerHome.class);
                }
                finish();
                targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(targetIntent);
            }
        });
        viewModel.doLogin();
    }

    private void setUpClicks() {
        binding.loginContainer.btnContainer.button2.setOnClickListener(v -> {
            observeLogin();
        });
        binding.loginContainer.btnForgotPwd.setOnClickListener(v -> {

        });
    }
}