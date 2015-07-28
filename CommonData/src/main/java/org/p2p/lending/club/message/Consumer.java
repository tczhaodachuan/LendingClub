package org.p2p.lending.club.message;

import org.p2p.lending.club.api.data.impl.Note;
import org.springframework.context.Lifecycle;

/**
 * Created by tczhaodachuan on 7/21/2015.
 */
public interface Consumer extends Lifecycle{
    void start(Listener listener);

    public interface Listener<T>
    {
        void onMessage(T t);
    }
}
