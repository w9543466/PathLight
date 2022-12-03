package uk.ac.tees.w9543466.pathlight.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import kotlin.jvm.Volatile;

@Database(
        entities = {WorkEntity.class, ProfileEntity.class},
        version = 2
)
public abstract class PathLightDatabase extends RoomDatabase {
    public abstract WorkDao workDao();

    public abstract ProfileDao profileDao();

    @Volatile
    private static PathLightDatabase instance = null;

    public static PathLightDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PathLightDatabase.class, "pathlight_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
