package uk.ac.tees.w9543466.pathlight.employer.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;

import java.util.ArrayList;

import uk.ac.tees.w9543466.pathlight.KeyValueModel;
import uk.ac.tees.w9543466.pathlight.db.PathLightDatabase;
import uk.ac.tees.w9543466.pathlight.db.ProfileDao;
import uk.ac.tees.w9543466.pathlight.db.ProfileEntity;
import uk.ac.tees.w9543466.pathlight.employer.EmployerRepo;
import uk.ac.tees.w9543466.pathlight.utils.PrefUtil;
import uk.ac.tees.w9543466.pathlight.utils.ThreadWorker;
import uk.ac.tees.w9543466.pathlight.worker.WorkerRepo;

public class ProfileViewModel extends AndroidViewModel {

    private final EmployerRepo employerRepo;
    private final WorkerRepo workerRepo;
    public ObservableField<String> firstName = new ObservableField<>("");
    private final ProfileDao profileDao;
    private final Gson gson = new Gson();
    private final PrefUtil prefUtil;
    private final PathLightDatabase database;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        employerRepo = new EmployerRepo(application);
        workerRepo = new WorkerRepo(application);
        database = PathLightDatabase.getDatabase(application);
        profileDao = database.profileDao();
        prefUtil = new PrefUtil(application);
    }

    public LiveData<ProfileEntity> getProfileDetails() {
        return profileDao.get();
    }

    public void getEmployerProfile() {
        employerRepo.getProfile(response -> {
            if (response.isSuccess()) {
                saveProfileToDb(response);
            }
        });
    }

    public void getWorkerProfile() {
        workerRepo.getProfile(response -> {
            if (response.isSuccess()) {
                saveProfileToDb(response);
            }
        });
    }

    private void saveProfileToDb(EmployerProfileResponse response) {
        ThreadWorker.execute(() -> {
            profileDao.delete();
            ProfileEntity data = gson.fromJson(gson.toJson(response), ProfileEntity.class);
            String firstName = data.getFirstName();
            this.firstName.set(firstName == null ? "" : firstName);
            profileDao.insert(data);
        });
    }


    public ArrayList<KeyValueModel> getWorkerProfileDetailsList(ProfileEntity data) {
        ArrayList<KeyValueModel> list = new ArrayList<>();
        list.add(new KeyValueModel("Name", data.getFirstName() + " " + data.getLastName()));
        list.add(new KeyValueModel("Email", data.getEmail()));
        String dob = data.getDob();
        String skills = data.getSkills();
        if (dob != null && !dob.isEmpty()) {
            list.add(new KeyValueModel("Date of birth", dob));
        }
        if (skills != null && !skills.isEmpty()) {
            list.add(new KeyValueModel("Designation", skills));
        }
        return list;
    }

    public ArrayList<KeyValueModel> getEmployerProfileDetailsList(ProfileEntity data) {
        ArrayList<KeyValueModel> list = new ArrayList<>();
        list.add(new KeyValueModel("Name", data.getFirstName() + " " + data.getLastName()));
        list.add(new KeyValueModel("Email", data.getEmail()));
        return list;
    }

    public void logout() {
        prefUtil.deleteLoginInfo();
        ThreadWorker.execute(database::clearAllTables);
    }
}