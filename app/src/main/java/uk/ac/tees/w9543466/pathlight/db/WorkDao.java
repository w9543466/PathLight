package uk.ac.tees.w9543466.pathlight.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface WorkDao {

    @Query("SELECT * FROM table_work")
    List<WorkEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorkEntity work);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<WorkEntity> works);

    @Query("select * from table_work where id=:id")
    WorkEntity get(long id);

    @Query("delete from table_work where id=:id")
    void delete(long id);

    @Query("delete from table_work")
    void deleteAll();
}
