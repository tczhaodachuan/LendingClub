package org.p2p.lending.club.api.data.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tczhaodachuan on 8/11/2015.
 */
public class ListedNotes {
    private final String asOfDate;
    private final List<Note> listedNotes;

    public ListedNotes(String asOfDate) {
        this.asOfDate = asOfDate;
        listedNotes = new LinkedList<>();
    }

    public String getAsOfDate() {
        return asOfDate;
    }

    public List<Note> getListedNotes() {
        return listedNotes;
    }

    public void addNote(Note note)
    {
        listedNotes.add(note);
    }
}
