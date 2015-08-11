package org.p2p.lending.club.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.NoteOwned;
import org.p2p.lending.club.api.filter.ValueFilter;
import org.springframework.context.Lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tczhaodachuan on 7/20/2015.
 */
public class ConsumerLifeCycle implements Lifecycle {
    private static final Logger LOG = LogManager.getLogger();
    private boolean isRunning = false;
    private final ValueFilter valueFilter;

    public ConsumerLifeCycle(ValueFilter valueFilter) {
        this.valueFilter = valueFilter;
    }

    @Override
    public void start() {
        isRunning = true;
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "B");
        NoteOwned note1 = new NoteOwned("1", "11", map);
        map.put(EnumNote.GRADE.value(), "C");
        NoteOwned note2 = new NoteOwned("2", "22", map);
        map.put(EnumNote.GRADE.value(), "A");
        NoteOwned note3 = new NoteOwned("3", "33", map);
        List<NoteOwned> noteOwnedList = new ArrayList<>();
        noteOwnedList.add(note1);
        noteOwnedList.add(note2);
        noteOwnedList.add(note3);
        for (NoteOwned noteOwned : noteOwnedList) {
            if (valueFilter.isAllowed(noteOwned)) {
                LOG.info("NoteOwned is allowed noteId {}, loanId {}", noteOwned.getNoteId(), noteOwned.getLoanId());
            } else {
                LOG.info("NoteOwned is not allowed noteId {}, loanId {}", noteOwned.getNoteId(), noteOwned.getLoanId());
            }
        }
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
