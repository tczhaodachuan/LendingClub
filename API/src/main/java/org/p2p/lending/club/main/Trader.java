package org.p2p.lending.club.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.QueryAPI;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.impl.InventoryFilter;
import org.p2p.lending.club.message.Consumer;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public class Trader implements Consumer {
    private static final Logger LOG = LogManager.getLogger();
    private final Listener listener;
    private final QueryAPI queryAPI;
    private final InventoryFilter inventoryFilter;
    // 1 hour
    private long queryDelayTime = 1 * 60 * 60 * 1000;
    private boolean isRunning = false;

    public Trader(Listener listener, QueryAPI queryAPI, InventoryFilter inventoryFilter) {
        this.listener = listener;
        this.queryAPI = queryAPI;
        this.inventoryFilter = inventoryFilter;
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
            // get all owned notes, to avoid duplications
            LOG.info("[1] Trader starts to analysis owned notes");
            List<Note> ownedNotes = queryAPI.getOwnedNotes();
            ownedNotes.forEach(inventoryFilter::addNote);

            // get all listed notes, add them to listener
            LOG.info("[2] Trader starts to analysis listed notes");
            ListedNotes listedNotes = queryAPI.getAllListedNotes();
            List<Note> noteList = listedNotes.getListedNotes();
            if(noteList == null || noteList.isEmpty())
            {
                // failed to get noteList
                stop();
                break;
            }

            // the listener will filter out notes based on spring configuration,while the inventoryFilter is
            // one of the filters included.
            LOG.info("[3] Trader starts to filter on listed notes");
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
