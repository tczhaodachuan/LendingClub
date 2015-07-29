package org.p2p.lending.club.api.filter.impl;

import org.junit.Test;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.Note;

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
        Note note = new Note("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        note = new Note("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        note = new Note("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        note = new Note("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(note));

        singleValueIntegerFilter = new SingleValueIntegerFilter("loanAmount", 1000, "gt");
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        note = new Note("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        note = new Note("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        note = new Note("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(note));

        singleValueIntegerFilter = new SingleValueIntegerFilter("loanAmount", 1000, "lt");
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        note = new Note("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        note = new Note("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        note = new Note("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(note));

        singleValueIntegerFilter = new SingleValueIntegerFilter("loanAmount", 1000, "lte");
        map.put(EnumNote.LOAN_AMOUNT.value(), 1000);
        note = new Note("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 400);
        note = new Note("11", "22", map);
        assertTrue(singleValueIntegerFilter.isAllowed(note));
        map.put(EnumNote.LOAN_AMOUNT.value(), 4000);
        note = new Note("11", "22", map);
        assertFalse(singleValueIntegerFilter.isAllowed(note));
    }
}