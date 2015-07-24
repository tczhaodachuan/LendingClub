package org.p2p.lending.club.api;

import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.transaction.Transaction;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public interface QueryAPI {
    List<Note> getOwnedNotes();
    List<Note> getListedNotes();
    double getAvailableCash();
    boolean submitOrder(Transaction transaction);
    boolean createPortfolio(String investorId, String portfolioName, String portfolioDescription);
}
