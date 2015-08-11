package org.p2p.lending.club.api.order;

import org.p2p.lending.club.api.data.impl.Note;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public class Order {
    private final Note note;
    private final String requestedAccount;
    private final long createTime;
    private String portfolioId;

    public Order(Note note, String requestedAccount) {
        this.note = note;
        this.requestedAccount = requestedAccount;
        this.createTime = System.currentTimeMillis();
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

    public long getCreateTime() {
        return createTime;
    }
}
