package org.p2p.lending.club.api;

import org.p2p.lending.club.api.data.impl.NoteOwned;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public interface QueryAPI {
    static String SCHEME = "https";
    static String HOST = "api.lendingclub.com";
    static String LISTING_NOTES_PATH = "/api/investor/v1/loans/listing";
    static String SHOW_ALL = "showAll";
    static String AUTHENTICATION = "Authorization";
    static String CONTENT_TYPE = "Content-type";
    static String ACCEPT = "Accept";

    List<NoteOwned> getOwnedNotes();

    List<NoteOwned> getListedNotes();

    List<NoteOwned> getAllListedNotes();

    double getAvailableCash();

    boolean submitTransaction(Transaction transaction);

    boolean createPortfolio(String investorId, String portfolioName, String portfolioDescription);
}
