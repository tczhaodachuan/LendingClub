package org.p2p.lending.club.api.filter.impl;

import org.junit.Before;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.NoteOwned;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tczhaodachuan on 7/14/2015.
 */
public class SingleValueStringFilterTest {
    private SingleValueStringFilter singleValueStringFilter;

    @Before
    public void setUp() throws Exception {
        singleValueStringFilter = new SingleValueStringFilter("grade", "E", "equals");
    }

    @Test
    public void testSetIgnoreCase() throws Exception {
        singleValueStringFilter = new SingleValueStringFilter("grade", "e", "equals");
        singleValueStringFilter.setIgnoreCase(false);
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "E");
        NoteOwned noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueStringFilter.isAllowed(noteOwned));
        map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "e");
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueStringFilter.isAllowed(noteOwned));

    }

    @Test
    public void testSetNot() throws Exception {
        singleValueStringFilter = new SingleValueStringFilter("grade", "e", "equals");
        singleValueStringFilter.setNot(true);
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "e");
        NoteOwned noteOwned = new NoteOwned("11", "22", map);
        // should be filtered but not
        assertFalse(singleValueStringFilter.isAllowed(noteOwned));

        map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "A");
        noteOwned = new NoteOwned("11", "22", map);
        // should not be filtered but yes
        assertTrue(singleValueStringFilter.isAllowed(noteOwned));
    }

    @Test
    public void testIsAllowed() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "A");
        NoteOwned noteOwned = new NoteOwned("11", "22", map);

        assertFalse(singleValueStringFilter.isAllowed(noteOwned));

        singleValueStringFilter = new SingleValueStringFilter("issueDate", "07-15-2015", "equals");
        map = new HashMap<>();
        map.put(EnumNote.ISSUE_DATE.value(), "07-15-2015");
        noteOwned = new NoteOwned("11", "22", map);

        assertTrue(singleValueStringFilter.isAllowed(noteOwned));

        singleValueStringFilter = new SingleValueStringFilter("issueDate", "07-15-2015", "contains");
        map = new HashMap<>();
        map.put(EnumNote.ISSUE_DATE.value(), "07-15-2015 00:00:00");
        noteOwned = new NoteOwned("11", "22", map);
        assertTrue(singleValueStringFilter.isAllowed(noteOwned));

        map = new HashMap<>();
        map.put(EnumNote.ISSUE_DATE.value(), "07-15-2016 00:00:00");
        noteOwned = new NoteOwned("11", "22", map);
        assertFalse(singleValueStringFilter.isAllowed(noteOwned));
    }
}