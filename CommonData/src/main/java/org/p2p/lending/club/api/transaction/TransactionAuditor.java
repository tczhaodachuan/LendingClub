package org.p2p.lending.club.api.transaction;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public interface TransactionAuditor {
    void audit(Transaction transaction, String message);
}
