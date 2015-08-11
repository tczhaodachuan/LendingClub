package org.p2p.lending.club.api.data.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tczhaodachuan on 8/11/2015.
 */
public class NoteTest {
    private Note note;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.ADDR_STATE.value(), "NJ");
        map.put(EnumNote.ADDR_ZIP.value(), "07030");
        map.put(EnumNote.INT_RATE.value(), "11.2");
        map.put(EnumNote.INVESTOR_COUNT.value(), "1132");
        note = new Note("1", "2", map);
    }

    @Test
    public void testGetNoteId() throws Exception {
        assertTrue(note.getNoteId().equals("1"));
    }

    @Test
    public void testGetLoanId() throws Exception {
        assertTrue(note.getLoanId().equals("2"));
    }

    @Test
    public void testGetFieldsMap() throws Exception {
        assertTrue(note.getFieldsMap().size() == 6);
    }

    @Test
    public void testGetInteger() throws Exception {
        assertTrue(note.getInteger(EnumNote.INVESTOR_COUNT) == 1132);
    }

    @Test
    public void testGetDouble() throws Exception {
        assertTrue(note.getDouble(EnumNote.INT_RATE) == 11.2);
    }

    @Test
    public void testGetString() throws Exception {
        assertTrue(note.getString(EnumNote.ADDR_STATE).equals("NJ"));
    }

    @Test
    public void testIsOwned() throws Exception {
        assertFalse(note.isOwned());
        note.setIsOwned(true);
        assertTrue(note.isOwned());
    }
}