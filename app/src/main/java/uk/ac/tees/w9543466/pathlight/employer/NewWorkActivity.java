package uk.ac.tees.w9543466.pathlight.employer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityNewWorkBinding;
import uk.ac.tees.w9543466.pathlight.employer.home.HomeViewModel;

public class NewWorkActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY_EDIT_MODE = "edit_mode";
    private HomeViewModel viewModel;
    private ActivityNewWorkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = ActivityNewWorkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
