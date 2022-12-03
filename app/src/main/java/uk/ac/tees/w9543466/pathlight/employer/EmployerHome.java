package uk.ac.tees.w9543466.pathlight.employer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import uk.ac.tees.w9543466.pathlight.R;
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