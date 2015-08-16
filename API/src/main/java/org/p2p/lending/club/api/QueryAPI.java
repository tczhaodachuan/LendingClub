package org.p2p.lending.club.api;

import org.p2p.lending.club.api.data.impl.AvailableCash;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.data.impl.Portfolio;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public interface QueryAPI {
    String SCHEME = "https";
    String HOST = "api.lendingclub.com";
    String LISTING_NOTES_PATH = "/api/investor/v1/loans/listing";
    String ACCOUNT_PATH = "/api/investor/v1/accounts/";
    String SHOW_ALL = "showAll";
    String AUTHENTICATION = "Authorization";
    String CONTENT_TYPE = "Content-type";
    String ACCEPT = "Accept";

    String AID = "aid";
    String PORTFOLIO_NAME = "portfolioName";
    String PORTFOLIOD_ESCRIPTION = "portfolioDescription";


    String getInvestorId();

    List<Note> getOwnedNotes();

    ListedNotes getListedNotes();

    ListedNotes getAllListedNotes();

    AvailableCash getAvailableCash();

    boolean submitTransaction(Transaction transaction);

    Portfolio createPortfolio(String portfolioName, String portfolioDescription);
}
