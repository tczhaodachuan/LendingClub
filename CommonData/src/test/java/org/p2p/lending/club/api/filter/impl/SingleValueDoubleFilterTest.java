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
public class SingleValueDoubleFilterTest {

    @Test
    public void testIsAllowed() throws Exception {
        SingleValueDoubleFilter singleValueDoubleFilter = new SingleValueDoubleFilter("interestRate", 0.1, "gte");
        Map<String, Object> map = new HashMap<>();
        Note note = new Note("11", "22", map);
        assertFalse(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.1);
        note = new Note("11", "22", map);
        assertTrue(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.4);
        note = new Note("11", "22", map);
        assertTrue(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.01);
        note = new Note("11", "22", map);
        assertFalse(singleValueDoubleFilter.isAllowed(note));

        singleValueDoubleFilter = new SingleValueDoubleFilter("interestRate", 0.1, "gt");
        map.put(EnumNote.INTEREST_RATE.value(), 0.1);
        note = new Note("11", "22", map);
        assertFalse(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.04);
        note = new Note("11", "22", map);
        assertFalse(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.4);
        note = new Note("11", "22", map);
        assertTrue(singleValueDoubleFilter.isAllowed(note));

        singleValueDoubleFilter = new SingleValueDoubleFilter("interestRate", 0.1, "lt");
        map.put(EnumNote.INTEREST_RATE.value(), 0.1);
        note = new Note("11", "22", map);
        assertFalse(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.04);
        note = new Note("11", "22", map);
        assertTrue(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.4);
        note = new Note("11", "22", map);
        assertFalse(singleValueDoubleFilter.isAllowed(note));

        singleValueDoubleFilter = new SingleValueDoubleFilter("interestRate", 0.1, "lte");
        map.put(EnumNote.INTEREST_RATE.value(), 0.1);
        note = new Note("11", "22", map);
        assertTrue(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.04);
        note = new Note("11", "22", map);
        assertTrue(singleValueDoubleFilter.isAllowed(note));
        map.put(EnumNote.INTEREST_RATE.value(), 0.4);
        note = new Note("11", "22", map);
        assertFalse(singleValueDoubleFilter.isAllowed(note));


        System.out.println("singleValueDoubleFilter = " + singleValueDoubleFilter.isAllowed(note));
    }
}