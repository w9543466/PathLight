package uk.ac.tees.w9543466.pathlight.utils;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

import uk.ac.tees.w9543466.pathlight.employer.home.DataCallback;

public class DateTimePickerUtil {

    public static void openDatePicker(FragmentManager fragmentManager, String msg, boolean pickTime, DataCallback<Calendar> callback) {
        MaterialDatePicker<Long> picker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(msg)
                .build();
        Calendar instance = Calendar.getInstance();
        picker.addOnPositiveButtonClickListener(selection -> {
            instance.setTimeInMillis(selection);
            if (pickTime) {
                openTimePicker(fragmentManager, msg, callback, instance);
            } else {
                callback.onData(instance);
            }
        });
        picker.show(fragmentManager, "datePicker");
    }

    public static void openTimePicker(FragmentManager fragmentManager, String msg, DataCallback<Calendar> callback, Calendar cal) {
        MaterialTimePicker picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setTitleText(msg)
                .setHour(cal.get(Calendar.HOUR_OF_DAY))
                .setMinute(cal.get(Calendar.MINUTE))
                .build();
        picker.addOnDismissListener(dialog -> {
            cal.set(Calendar.HOUR_OF_DAY, picker.getHour());
            cal.set(Calendar.MINUTE, picker.getMinute());
            callback.onData(cal);
        });
        picker.show(fragmentManager, "timePicker");
    }
}
