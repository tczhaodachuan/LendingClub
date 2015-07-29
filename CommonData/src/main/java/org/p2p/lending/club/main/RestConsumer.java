package org.p2p.lending.club.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.QueryAPI;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.message.Consumer;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public class RestConsumer implements Consumer {
    private static final Logger LOG = LogManager.getLogger();
    private final Listener listener;
    private final QueryAPI queryAPI;
    // 1 hour
    private long queryDelayTime = 1 * 60 * 60 * 1000;
    private boolean isRunning = false;

    public RestConsumer(Listener listener, QueryAPI queryAPI) {
        this.listener = listener;
        this.queryAPI = queryAPI;
    }

    public void init() {
        if (listener == null) {
            throw new IllegalArgumentException("Listener class is mandatory!");
        }

        if (queryAPI == null) {
            throw new IllegalArgumentException("QueryAPI class is mandatory!");
        }
    }

    @Override
    public void start(Listener listener) {
        while (isRunning) {
            List<Note> noteList = queryAPI.getListedNotes();
            noteList.forEach(note -> listener.onMessage(note));
            try {
                Thread.sleep(queryDelayTime);
            } catch (InterruptedException e) {
                LOG.error(e, e);
            }
        }
    }

    @Override
    public void start() {
        isRunning = true;
        start(listener);
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    public void setQueryDelayTime(long queryDelayTime) {
        this.queryDelayTime = queryDelayTime;
    }
}
