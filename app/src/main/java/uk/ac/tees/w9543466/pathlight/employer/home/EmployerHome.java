package uk.ac.tees.w9543466.pathlight.employer.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityEmployerHomeBinding;

public class EmployerHome extends AppCompatActivity {

    private ActivityEmployerHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEmployerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_employer_home);
        //NavigationUI.setupWithNavController(binding.navView, navController);
    }

}