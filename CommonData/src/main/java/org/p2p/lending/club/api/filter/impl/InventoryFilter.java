package org.p2p.lending.club.api.filter.impl;

import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;

import java.util.Set;

/**
 * Created by tczhaodachuan on 8/12/2015.
 */
public class InventoryFilter implements ValueFilter{
    private final Set<String> noteSet;

    public InventoryFilter(Set<String> noteSet) {
        this.noteSet = noteSet;
    }

    @Override
    public boolean isAllowed(Note note) {
        return !noteSet.contains(note.getLoanId());
    }

    public void addNote(final Note note)
    {
        noteSet.add(note.getLoanId());
    }
}
