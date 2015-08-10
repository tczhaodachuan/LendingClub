package org.p2p.lending.club.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.Note;
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
        Note note1 = new Note("1", "11", map);
        map.put(EnumNote.GRADE.value(), "C");
        Note note2 = new Note("2", "22", map);
        map.put(EnumNote.GRADE.value(), "A");
        Note note3 = new Note("3", "33", map);
        List<Note> noteList = new ArrayList<>();
        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);
        for (Note note : noteList) {
            if (valueFilter.isAllowed(note)) {
                LOG.info("Note is allowed noteId {}, loanId {}", note.getNoteId(), note.getLoanId());
            } else {
                LOG.info("Note is not allowed noteId {}, loanId {}", note.getNoteId(), note.getLoanId());
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
