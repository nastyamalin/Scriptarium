//package com.example.sproject.Database;
//
//import static androidx.room.OnConflictStrategy.REPLACE;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import com.example.sproject.Models.Verse;
//
//import java.util.List;
//
//@Dao
//public interface FavoritesDAO {
//    @Insert(onConflict = REPLACE)default
//    void insert() {
//        insert();
//    }
//
//
//    @Query("SELECT*FROM verse ORDER BY id DESC")
//    List<Verse> getAll();
//
//    @Query("UPDATE notes SET title= :title, notes= :verse WHERE ID = :id")
//    void update(int id, String title, String notes);
//
//    @Delete
//    void delete(Verse verse);
//
//    @Query("UPDATE notes SET pinned= :pin WHERE ID = :id")
//    void pin(int id, boolean pin);
//
//
//    @Insert(onConflict = REPLACE)
//    void insert(Verse verse);
//
//}

