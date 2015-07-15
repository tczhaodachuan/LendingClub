package org.p2p.lending.club.api.filter.impl;

import org.junit.Before;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.Note;

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
        Note note = new Note("11", "22", map);
        assertFalse(singleValueStringFilter.isFiltered(note));
        map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "e");
        note = new Note("11", "22", map);
        assertTrue(singleValueStringFilter.isFiltered(note));

    }

    @Test
    public void testSetNot() throws Exception {

    }

    @Test
    public void testIsFiltered() throws Exception {

    }
}