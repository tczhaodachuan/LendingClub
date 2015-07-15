package org.p2p.lending.club.api.filter.impl;

import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.NoteFilter;

/**
 * Created by tczhaodachuan on 7/14/2015.
 */
public class SingleValueStringFilter implements NoteFilter {
    private final String name;
    private final String value;
    // type could be equals, contains
    private final String type;
    private boolean ignoreCase = true;
    private boolean not = false;

    public SingleValueStringFilter(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    @Override
    public boolean isFiltered(Note note) {
        EnumNote enumNote = EnumNote.valueOf(value);
        String noteV = note.getString(enumNote);
        if (noteV == null) {
            return true;
        }

        if ("equals".equalsIgnoreCase(type)) {
            if (ignoreCase) {
                return noteV.equals(value) ? !not : not;
            } else {
                return noteV.equalsIgnoreCase(value) ? !not : not;
            }
        } else if ("contains".equalsIgnoreCase(type)) {
            return noteV.contains(value) ? !not : not;
        }

        return true;
    }
}
