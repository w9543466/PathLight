package uk.ac.tees.w9543466.pathlight.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityUserRoleBinding;

public class UserRoleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserRoleBinding binding = ActivityUserRoleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonWorker.setOnClickListener(v -> startActivity(new Intent(UserRoleActivity.this, WorkerSignupActivity.class)));
        binding.btnEmployer.setOnClickListener(v -> startActivity(new Intent(UserRoleActivity.this, EmployerSignupActivity.class)));
    }
}
