package uk.ac.tees.w9543466.pathlight;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.w9543466.pathlight.auth.IntroActivity;
import uk.ac.tees.w9543466.pathlight.auth.UserRole;
import uk.ac.tees.w9543466.pathlight.employer.home.EmployerHome;
import uk.ac.tees.w9543466.pathlight.utils.PrefUtil;
import uk.ac.tees.w9543466.pathlight.worker.WorkerHome;

public class RoutingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrefUtil prefUtil = new PrefUtil(this);
        if (prefUtil.isLoggedIn()) {
            UserRole userRole = prefUtil.getUserRole();
            if (userRole == UserRole.EMPLOYER) {
                startActivity(new Intent(this, EmployerHome.class));
            } else {
                startActivity(new Intent(this, WorkerHome.class));
            }
        } else {
            startActivity(new Intent(this, IntroActivity.class));
        }
        finish();
    }
}
