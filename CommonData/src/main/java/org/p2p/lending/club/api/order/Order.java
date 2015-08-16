package org.p2p.lending.club.api.order;

import org.p2p.lending.club.api.data.impl.Note;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public class Order {
    private final Note note;
    private final String requestedAmount;
    private final long createTime;
    private int portfolioId;

    public Order(Note note, String requestedAmount) {
        this.note = note;
        this.requestedAmount = requestedAmount;
        this.createTime = System.currentTimeMillis();
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Note getNote() {
        return note;
    }

    public String getRequestedAmount() {
        return requestedAmount;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public long getCreateTime() {
        return createTime;
    }
}
