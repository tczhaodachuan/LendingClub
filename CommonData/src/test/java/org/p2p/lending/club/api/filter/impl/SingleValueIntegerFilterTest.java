package org.p2p.lending.club.api.filter.impl;

import org.junit.Test;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.NoteOwned;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tczhaodachuan on 7/15/2015.
 */
public class SingleValueIntegerFilterTest {

    @Test
    public void testIsAllowed() {
        SingleValueIntegerFilter singleValueIntegerFilter = new SingleValueIntegerFilter("loanAmount", 1000, "gte");
        Map<String, Object> map = new HashMap<>();
        NoteOwned noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(noteOwned));

        singleValueIntegerFilter = new SingleValueIntegerFilter("loanAmount", 1000, "gt");
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(noteOwned));

        singleValueIntegerFilter = new SingleValueIntegerFilter("loanAmount", 1000, "lt");
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(noteOwned));

        singleValueIntegerFilter = new SingleValueIntegerFilter("loanAmount", 1000, "lte");
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(noteOwned));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(noteOwned));
    }
}