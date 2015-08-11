package org.p2p.lending.club.api.filter.impl;

import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;

/**
 * Created by tczhaodachuan on 7/15/2015.
 */
public class SingleValueDoubleFilter implements ValueFilter {
    private final String name;
    private final double value;
    // type could be equals, contains
    private final String type;

    public SingleValueDoubleFilter(String name, double value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    @Override
    public boolean isAllowed(Note note) {
        EnumNote enumNote = EnumNote.getEnumTagOf(name);
        Double noteD = note.getDouble(enumNote);
        if (noteD == null) {
            return false;
        }

        if ("gte".equals(type)) {
            return noteD >= value;
        } else if ("gt".equals(type)) {
            return noteD > value;
        } else if ("lt".equals(type)) {
            return noteD < value;
        } else if ("lte".equals(type)) {
            return noteD <= value;
        }
        return false;
    }
}
