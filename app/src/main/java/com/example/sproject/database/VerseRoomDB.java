package com.example.sproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sproject.models.Verse;
import com.example.sproject.ui.verse.VerseFragment;

@Database(entities = Verse.class, version = 1, exportSchema = false)
public abstract class VerseRoomDB extends RoomDatabase {
   private static VerseRoomDB database;
   private static String DATABASE_NAME = "Verses";

   public static VerseRoomDB getInstance(VerseFragment mainActivity) {
       return null;
    }

   public static synchronized VerseRoomDB gerInstance(Context contex) {
       if (database == null) {
          database = Room.databaseBuilder(contex.getApplicationContext(),
                   VerseRoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                   .fallbackToDestructiveMigration()
                   .build();
        }
        return database;
    }

public abstract VerseDAO verseDAO();
}
