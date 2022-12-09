package uk.ac.tees.w9543466.pathlight.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        ActivityIntroBinding binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.introContainer.button.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this, LoginActivity.class)));
        binding.introContainer.btnNewAcc.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this, UserRoleActivity.class)));
    }
}