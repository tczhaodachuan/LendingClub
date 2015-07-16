package org.p2p.lending.club.api.filter.impl;

import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;

/**
 * Created by tczhaodachuan on 7/15/2015.
 */
public class SingleValueIntegerFilter implements ValueFilter {
    private final String name;
    private final int value;
    // type could be equals, contains
    private final String type;

    public SingleValueIntegerFilter(String name, int value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    @Override
    public boolean isAllowed(Note note) {
        EnumNote enumNote = EnumNote.getEnumTagOf(name);
        Integer noteI = note.getInteger(enumNote);
        if (noteI == null) {
            return false;
        }

        if ("gte".equals(type)) {
            return noteI >= value ? true : false;
        } else if ("gt".equals(type)) {
            return noteI > value ? true : false;
        } else if ("lt".equals(type)) {
            return noteI < value ? true : false;
        } else if ("lte".equals(type)) {
            return noteI <= value ? true : false;
        }
        return false;
    }
}
