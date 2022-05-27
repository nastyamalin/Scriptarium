package com.example.sproject.repository;

import androidx.lifecycle.LiveData;

import com.example.sproject.App;
import com.example.sproject.database.MainDAO;
import com.example.sproject.database.RoomDB;
import com.example.sproject.database.VerseDAO;
import com.example.sproject.database.VerseRoomDB;
import com.example.sproject.models.Notes;
import com.example.sproject.models.Verse;

import java.util.List;

public class RoomRepository {
    private LiveData<List<Notes>> notesArrayList;
    private final MainDAO mainDAO;
    private LiveData<List<Verse>> verseArrayList;
    private final VerseDAO verseDAO;
    public RoomRepository() {
        RoomDB db = RoomDB.gerInstance(App.instance);
        mainDAO = db.mainDAO();
        notesArrayList = mainDAO.getAll();
        VerseRoomDB vdb = VerseRoomDB.gerInstance(App.instance);
        verseDAO= vdb.verseDAO();
        verseArrayList= verseDAO.getAll();

    }

    public LiveData<List<Notes>> getAllNotes(){
        new Thread(() -> notesArrayList = mainDAO.getAll()).start();
        return notesArrayList;
    }
    public LiveData<List<Verse>> getAllVerse(){
        new Thread(() -> verseArrayList = verseDAO.getAll()).start();
        return  verseArrayList;
    }
    public void updateNote(Notes notes){

    public void addNote(Notes notes){
        new Thread(() -> mainDAO.insert(notes)).start();
    }
    public  void updateVerse(Verse verse){
        new Thread(() -> verseDAO.insert(verse)).start();
    }

    public void updatePin(Notes notes,boolean pin){
        new Thread(() -> mainDAO.pin(notes.getID(),pin)).start();
    }
}
