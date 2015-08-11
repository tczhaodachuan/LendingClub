package org.p2p.lending.club.api.filter.impl;

import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.NoteOwned;
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
    public boolean isAllowed(NoteOwned noteOwned) {
        EnumNote enumNote = EnumNote.getEnumTagOf(name);
        Double noteD = noteOwned.getDouble(enumNote);
        if (noteD == null) {
            return false;
        }

        if ("gte".equals(type)) {
            return noteD >= value ? true : false;
        } else if ("gt".equals(type)) {
            return noteD > value ? true : false;
        } else if ("lt".equals(type)) {
            return noteD < value ? true : false;
        } else if ("lte".equals(type)) {
            return noteD <= value ? true : false;
        }
        return false;
    }
}
