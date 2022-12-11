package uk.ac.tees.w9543466.pathlight.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        observeForgotPwdResponse();
        observeLogin();
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
    }

    private void setUpClicks() {
        binding.loginContainer.btnContainer.button2.setOnClickListener(v -> {
            viewModel.doLogin();
        });
        binding.loginContainer.btnForgotPwd.setOnClickListener(v -> {
            viewModel.onForgotPassword();
        });
    }

    private void observeForgotPwdResponse() {
        String errorMessage = "We are unable to reach our server right now. Please try again later.";
        viewModel.getForgotPwdLiveData().observe(this, response -> {
            if (response != null) {
                String message = response.getMessage();
                message = message == null ? errorMessage : message;
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}