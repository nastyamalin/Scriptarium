package com.example.sproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sproject.models.Notes;
import com.example.sproject.repository.RoomRepository;

import java.util.List;

public class IdeaViewModel extends ViewModel {
    //    MutableLiveData<List<Notes>> _notesList = new MutableLiveData<>();
    LiveData<List<Notes>> notesList;
    private final RoomRepository roomRepository = new RoomRepository();

    public IdeaViewModel() {
        getNotesList();
    }

    public void getNotesList() {
        notesList = roomRepository.getAllNotes();
    }

    public void addNote(Notes notes) {
        if (notes.getID() == 0)
            roomRepository.addNote(notes);
        else
            roomRepository.updateNote(notes);
    }

    public void updatePin(Notes notes, boolean pin) {
        roomRepository.updatePin(notes, pin);
    }

    public void delete(Notes notes) {
        roomRepository.deleteNotes(notes);
    }
}