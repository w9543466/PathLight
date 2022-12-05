package uk.ac.tees.w9543466.pathlight.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.Task;

import uk.ac.tees.w9543466.pathlight.employer.home.DataCallback;

public class LocationUtil {

    private static FusedLocationProviderClient fusedLocationClient;

    private static void init(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    public static void getCurrentLocation(Context context, DataCallback<Location> callback) {
        init(context);
        Task<Location> task = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null);
        task.addOnSuccessListener(callback::onData);
    }
}
