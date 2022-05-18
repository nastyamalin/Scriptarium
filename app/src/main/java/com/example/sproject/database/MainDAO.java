package com.example.sproject.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sproject.models.Notes;

import java.util.List;


@Dao
public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    @Query("SELECT*FROM notes ORDER BY id DESC")
    LiveData<List<Notes>> getAll();

    @Query("UPDATE notes SET title= :title, description= :notes WHERE ID = :id")
    void update(long id, String title, String notes);

    @Delete
    void delete(Notes notes);

    @Query("UPDATE notes SET pinned= :pin WHERE ID = :id")
    void pin(long id, boolean pin);


//    @Insert(onConflict = REPLACE)
//    void insert(Verse verse);

}
