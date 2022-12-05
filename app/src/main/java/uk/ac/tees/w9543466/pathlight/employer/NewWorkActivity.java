package uk.ac.tees.w9543466.pathlight.employer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import uk.ac.tees.w9543466.pathlight.databinding.ActivityNewWorkBinding;
import uk.ac.tees.w9543466.pathlight.utils.DateTimePickerUtil;
import uk.ac.tees.w9543466.pathlight.utils.LocationUtil;

public class NewWorkActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY_EDIT_MODE = "edit_mode";
    private WorkViewModel viewModel;
    private ActivityNewWorkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WorkViewModel.class);
        binding = ActivityNewWorkBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        setContentView(binding.getRoot());
        setupClickers();
        checkPermission();
    }

    private void setupClickers() {
        binding.etStartTime.setOnClickListener(v -> {
            DateTimePickerUtil.openDatePicker(getSupportFragmentManager(), "Select work start date and time", true, date -> {
                System.out.println("date: startTime = " + date.getTime());
                viewModel.onStartTimeSelected(date.getTime());
            });
        });
        binding.btnContainer.button2.setOnClickListener(v -> viewModel.createWork());
    }

    private static final int RC_LOCATION = 243;

    ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts
                    .RequestMultiplePermissions(), result -> {
                Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
                if (fineLocationGranted != null && fineLocationGranted) {
                    // Precise location access granted.
                    captureLocation();
                } else if (coarseLocationGranted != null && coarseLocationGranted) {
                    // Only approximate location access granted.
                    //TODO
                } else {
                    // No location access granted.
                    //TODO
                }
            }
    );

    private void checkPermission() {
        String accessFineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
        int permissionResult = ContextCompat.checkSelfPermission(this, accessFineLocation);
        if (permissionResult == PackageManager.PERMISSION_GRANTED) {
            captureLocation();
        } else if (shouldShowRequestPermissionRationale(accessFineLocation)) {
            MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this);
            dialog.setTitle("Location permission required.");
            dialog.setMessage("Accurate location is required to create work. Please grant the location permission");
            dialog.setPositiveButton("Okay", (dialog1, which) -> {
                //TODO
            });
            dialog.setNegativeButton("Close", (dialog1, which) -> NewWorkActivity.this.finish());
        } else {
            locationPermissionRequest.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    private void captureLocation() {
        LocationUtil.getCurrentLocation(this, location -> {
            viewModel.onLocationObtained(location);
        });
    }

//    @AfterPermissionGranted(RC_LOCATION)
//    private void checkPermission2() {
//        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            captureLocation();
//        } else {
//            EasyPermissions.requestPermissions(this, "Location is required to create a work",
//                    RC_LOCATION, perms);
//        }
//    }

    //
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
//            checkPermission();
//        }
//    }
//
//
//    new AppSettingsDialog.Builder(this)
//            .build().show();
}
