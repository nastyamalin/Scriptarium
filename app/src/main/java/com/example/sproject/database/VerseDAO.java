package com.example.sproject.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sproject.models.Verse;

import java.util.List;


@Dao
public interface VerseDAO {
    @Insert(onConflict = REPLACE)
    void insert(Verse verse);

    @Query("SELECT*FROM verse ORDER BY id DESC")
    LiveData<List<Verse>> getAll();

    @Query("UPDATE verse SET title= :title, verse= :verse WHERE ID = :id")
    void update(int id, String title, String verse);

    @Delete
    void delete(Verse verse);

    @Query("UPDATE verse SET pinned= :pin WHERE ID = :id")
    void pin(int id, boolean pin);



}

