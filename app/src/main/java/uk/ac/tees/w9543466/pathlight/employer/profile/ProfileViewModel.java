package uk.ac.tees.w9543466.pathlight.employer.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;

import java.util.ArrayList;

import uk.ac.tees.w9543466.pathlight.db.PathLightDatabase;
import uk.ac.tees.w9543466.pathlight.db.ProfileDao;
import uk.ac.tees.w9543466.pathlight.db.ProfileEntity;
import uk.ac.tees.w9543466.pathlight.employer.EmployerRepo;
import uk.ac.tees.w9543466.pathlight.KeyValueModel;
import uk.ac.tees.w9543466.pathlight.utils.ThreadWorker;

public class ProfileViewModel extends AndroidViewModel {

    private final EmployerRepo employerRepo;
    public ObservableField<String> firstName = new ObservableField<>("");
    private final ProfileDao profileDao;
    private final Gson gson = new Gson();

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        employerRepo = new EmployerRepo(application);
        profileDao = PathLightDatabase.getDatabase(application).profileDao();
        getProfile();
    }

    public LiveData<ProfileEntity> getProfileDetails() {
        return profileDao.get();
    }

    public void getProfile() {
        employerRepo.getProfile(response -> {
            if (response.isSuccess()) {
                saveProfileToDb(response);
            }
        });
    }

    private void saveProfileToDb(ProfileResponse response) {
        ThreadWorker.execute(() -> {
            profileDao.delete();
            ProfileEntity data = gson.fromJson(gson.toJson(response), ProfileEntity.class);
            profileDao.insert(data);
        });
    }


    public ArrayList<KeyValueModel> getProfileDetailsList(ProfileEntity data) {
        String firstName = data.getFirstName();
        this.firstName.set(firstName == null ? "" : firstName);
        ArrayList<KeyValueModel> list = new ArrayList<>();
        list.add(new KeyValueModel("Name", data.getFirstName() + " " + data.getLastName()));
        list.add(new KeyValueModel("Email", data.getEmail()));
        return list;
    }
}