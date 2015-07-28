package org.p2p.lending.club.message.impl;

import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.message.Consumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by tczhaodachuan on 7/21/2015.
 */
public class NotesListener implements Consumer.Listener<Note> {
    private final int capacity;
    private ArrayBlockingQueue<Note> blockingQueue;

    public NotesListener(int capacity) {
        this.capacity = capacity;
        blockingQueue = new ArrayBlockingQueue<Note>(capacity);
    }

    @Override
    public void onMessage(Note note) {
        blockingQueue.offer(note);
    }
}
