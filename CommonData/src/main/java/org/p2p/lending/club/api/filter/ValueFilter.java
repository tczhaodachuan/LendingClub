package org.p2p.lending.club.api.filter;

import org.p2p.lending.club.api.data.impl.Note;

/**
 * Created by tczhaodachuan on 7/14/2015.
 */
public interface ValueFilter {
    boolean isAllowed(Note note);
}
