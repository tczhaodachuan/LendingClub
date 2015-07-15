package org.p2p.lending.club.api.filter;

import org.p2p.lending.club.api.data.impl.Note;

/**
 * Created by tczhaodachuan on 7/14/2015.
 */
public interface NoteFilter {
    boolean isFiltered(Note note);
}
