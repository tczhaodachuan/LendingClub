package org.p2p.lending.club.api.filter.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tczhaodachuan on 8/12/2015.
 */
public class InventoryFilter implements ValueFilter {
    private static final Logger LOG = LogManager.getLogger();
    private final Set<String> noteSet;

    public InventoryFilter() {
        this.noteSet = new HashSet<>();
    }

    @Override
    public boolean isAllowed(Note note) {
        if (noteSet.contains(note.getLoanId())) {
            LOG.info("LoanId {} is owned by the user. ", note.getLoanId());
        }
        return !noteSet.contains(note.getLoanId());
    }

    public void addNote(final Note note) {
        noteSet.add(note.getLoanId());
    }
}
