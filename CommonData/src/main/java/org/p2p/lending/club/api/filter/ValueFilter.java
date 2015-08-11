package org.p2p.lending.club.api.filter;

import org.p2p.lending.club.api.data.impl.NoteOwned;

/**
 * Created by tczhaodachuan on 7/14/2015.
 */
public interface ValueFilter {
    boolean isAllowed(NoteOwned noteOwned);
}
