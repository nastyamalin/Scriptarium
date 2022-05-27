package com.example.sproject.repository;

import androidx.lifecycle.LiveData;

import com.example.sproject.App;
import com.example.sproject.database.MainDAO;
import com.example.sproject.database.RoomDB;
import com.example.sproject.models.Notes;

import java.util.List;

public class RoomRepository {

    private LiveData<List<Notes>> notesArrayList;
    private final MainDAO mainDAO;

    public RoomRepository() {
        RoomDB db = RoomDB.gerInstance(App.instance);
        mainDAO = db.mainDAO();
        notesArrayList = mainDAO.getAll();
    }

    public LiveData<List<Notes>> getAllNotes(){
        new Thread(() -> notesArrayList = mainDAO.getAll()).start();
        return notesArrayList;
    }

    public void addNote(Notes notes){
        new Thread(() -> mainDAO.insert(notes)).start();
    }

    public void updatePin(Notes notes,boolean pin){
        new Thread(() -> mainDAO.pin(notes.getID(),pin)).start();
    }
}
