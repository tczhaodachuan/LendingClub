package org.p2p.lending.club.api.order;

import org.p2p.lending.club.api.data.impl.NoteOwned;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public class Order {
    private final NoteOwned noteOwned;
    private final String requestedAccount;
    private final long createTime;
    private String portfolioId;

    public Order(NoteOwned noteOwned, String requestedAccount) {
        this.noteOwned = noteOwned;
        this.requestedAccount = requestedAccount;
        this.createTime = System.currentTimeMillis();
    }

    public NoteOwned getNoteOwned() {
        return noteOwned;
    }

    public String getRequestedAccount() {
        return requestedAccount;
    }

    public String getPortfolioId() {
        return portfolioId;
    }
}
