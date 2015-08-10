package org.p2p.lending.club.api;

import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public interface QueryAPI {
    static String scheme = "http";
    static String host = "www.api.lendingclub.com";
    static String listNotesPath = "/api/investor/v1/loans/listing";
    static String showAll = "showAll";
    static String authentication = "Authorization";
    List<Note> getOwnedNotes();
    List<Note> getListedNotes();
    List<Note> getAllListedNotes();
    double getAvailableCash();
    boolean submitTransaction(Transaction transaction);
    boolean createPortfolio(String investorId, String portfolioName, String portfolioDescription);
}
