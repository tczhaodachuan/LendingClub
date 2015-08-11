package org.p2p.lending.club.message;

import org.springframework.context.Lifecycle;

/**
 * Created by tczhaodachuan on 7/21/2015.
 */
public interface Consumer extends Lifecycle{
    void start(Listener listener);

    interface Listener<T>
    {
        void onMessage(T t);
    }
}
