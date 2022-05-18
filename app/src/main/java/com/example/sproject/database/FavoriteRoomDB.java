//package com.example.sproject.Database;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.example.sproject.Models.Notes;
//import com.example.sproject.ui.dashboard.FavoritesFragment;
//
//
//@Database(entities = Notes.class, version = 1, exportSchema = false)
//public abstract class FavoriteRoomDB extends RoomDatabase {
//    private static FavoriteRoomDB database;
//    private static String DATABASE_NAME = "Verses";
//
//    public static FavoriteRoomDB getInstance(FavoritesFragment mainActivity) {
//        return null;
//    }
//
//    public synchronized FavoriteRoomDB gerInstance(Context contex) {
//        if (database == null) {
//            database = Room.databaseBuilder(contex.getApplicationContext(),
//                    FavoriteRoomDB.class, DATABASE_NAME)
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return database;
//    }
//
//    public abstract FavoritesDAO favoritesDAO();
//}
