package org.p2p.lending.club.util;

import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.NoteOwned;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tczhaodachuan on 7/30/2015.
 */
public class TestObjectsFactory {
    private static int noteId = 1;
    private static int loanId = 100;

    public static NoteOwned createNote() {
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "A");
        map.put(EnumNote.INTEREST_RATE.value(), "11.2");
        map.put(EnumNote.LOAN_AMOUNT.value(), "30000");
        map.put(EnumNote.LOAN_LENGTH.value(), "36");
        map.put(EnumNote.LOAN_STATUS.value(), "Pending");
        NoteOwned noteOwned = new NoteOwned(String.valueOf(noteId++), String.valueOf(loanId++), map);
        return noteOwned;
    }
}
