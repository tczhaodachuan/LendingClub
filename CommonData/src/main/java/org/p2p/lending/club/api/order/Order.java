package org.p2p.lending.club.api.order;

import org.p2p.lending.club.api.data.impl.Note;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public class Order {
    private final Note note;
    private final String requestedAccount;
    private String portfolioId;

    public Order(Note note, String requestedAccount) {
        this.note = note;
        this.requestedAccount = requestedAccount;
    }

    public Note getNote() {
        return note;
    }

    public String getRequestedAccount() {
        return requestedAccount;
    }

    public String getPortfolioId() {
        return portfolioId;
    }
}
