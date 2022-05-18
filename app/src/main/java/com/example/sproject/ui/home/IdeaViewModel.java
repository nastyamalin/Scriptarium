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

    public void updateNote(Notes notes) {
        roomRepository.updateNote(notes);
    }

}