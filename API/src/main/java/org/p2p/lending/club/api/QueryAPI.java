package org.p2p.lending.club.api;

import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public interface QueryAPI {
    String SCHEME = "https";
    String HOST = "api.lendingclub.com";
    String LISTING_NOTES_PATH = "/api/investor/v1/loans/listing";
    String SHOW_ALL = "showAll";
    String AUTHENTICATION = "Authorization";
    String CONTENT_TYPE = "Content-type";
    String ACCEPT = "Accept";

    List<Note> getOwnedNotes();

    List<Note> getListedNotes();

    List<Note> getAllListedNotes();

    double getAvailableCash();

    boolean submitTransaction(Transaction transaction);

    boolean createPortfolio(String investorId, String portfolioName, String portfolioDescription);
}
