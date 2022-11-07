package uk.ac.tees.w9543466.pathlight.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9543466.pathlight.MainActivity;
import uk.ac.tees.w9543466.pathlight.databinding.ActivityLoginBinding;

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
        viewModel.doLogin().observe(this, response -> {
            if (response.isSuccess()) {
                finish();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void setUpClicks() {
        binding.loginContainer.btnContainer.button2.setOnClickListener(v -> {
            observeLogin();
        });
        binding.loginContainer.btnForgotPwd.setOnClickListener(v -> {

        });
    }
}