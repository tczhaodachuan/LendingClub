package org.p2p.lending.club.message.impl;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public interface ExceptionHandler {
    void onException(Exception e, TransactionManager transactionManager);
}
