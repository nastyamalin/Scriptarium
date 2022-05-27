package com.example.sproject.ui.verse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sproject.models.Verse;
import com.example.sproject.repository.RoomRepository;

import java.util.List;

public class VerseViewModel extends ViewModel {
    //    MutableLiveData<List<Notes>> _notesList = new MutableLiveData<>();
    LiveData<List<Verse>> verseList;
    private final RoomRepository roomRepository = new RoomRepository();

    public VerseViewModel() {
        getVerseList();
    }

    public void getVerseList() {
        verseList = roomRepository.getAllVerse();
    }

    public void updateVerse(Verse verse) {
        if (verse.getID() != 0)
            roomRepository.updateVerse(verse);
        else
            roomRepository.addVerse(verse);
    }

}