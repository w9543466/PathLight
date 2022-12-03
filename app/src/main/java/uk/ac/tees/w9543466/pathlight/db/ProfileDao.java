package uk.ac.tees.w9543466.pathlight.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM table_profile LIMIT 1")
    LiveData<ProfileEntity> get();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ProfileEntity data);

    @Query("delete from table_profile")
    void delete();
}
