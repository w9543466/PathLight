package uk.ac.tees.w9543466.pathlight;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.tees.w9543466.pathlight.auth.IntroActivity;

public class RoutingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, IntroActivity.class));
    }
}
