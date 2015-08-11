package org.p2p.lending.club.api.data.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tczhaodachuan on 8/10/2015.
 */
public class ListingNotesResponse {
    private final String asOfDate;
    private List<NoteOwned> noteOwnedList;

    public ListingNotesResponse(String asOfDate) {
        this.asOfDate = asOfDate;
        noteOwnedList = new ArrayList<>();
    }

    public void addNote(NoteOwned noteOwned) {
        noteOwnedList.add(noteOwned);
    }

    public List<NoteOwned> getNoteOwnedList() {
        return noteOwnedList;
    }

    public String getAsOfDate() {
        return asOfDate;
    }
}
