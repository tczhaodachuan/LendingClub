package org.p2p.lending.club.api.filter.impl;

import org.junit.Test;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by tczhaodachuan on 7/16/2015.
 */
public class LogicalValueFilterTest {

    @Test
    public void testSetNot() throws Exception {

    }

    @Test
    public void testIsAllowed() throws Exception {
        ValueFilter valueFilter1 = mock(ValueFilter.class);
        ValueFilter valueFilter2 = mock(ValueFilter.class);
        ValueFilter valueFilter3 = mock(ValueFilter.class);
        List<ValueFilter> filterList = new ArrayList<>();
        filterList.add(valueFilter1);
        filterList.add(valueFilter2);
        filterList.add(valueFilter3);

        Note note = new Note("1", "2", new HashMap<>());

        LogicalValueFilter logicalValueFilter = new LogicalValueFilter("and", filterList);

        when(valueFilter1.isAllowed(any(Note.class))).thenReturn(true);
        when(valueFilter2.isAllowed(any(Note.class))).thenReturn(true);
        when(valueFilter3.isAllowed(any(Note.class))).thenReturn(true);

        assertTrue(logicalValueFilter.isAllowed(note));

        when(valueFilter2.isAllowed(any(Note.class))).thenReturn(false);

        assertFalse(logicalValueFilter.isAllowed(note));

        logicalValueFilter = new LogicalValueFilter("or", filterList);
        assertTrue(logicalValueFilter.isAllowed(note));

        when(valueFilter1.isAllowed(any(Note.class))).thenReturn(false);
        when(valueFilter2.isAllowed(any(Note.class))).thenReturn(false);
        when(valueFilter3.isAllowed(any(Note.class))).thenReturn(false);
        assertFalse(logicalValueFilter.isAllowed(note));
    }
}