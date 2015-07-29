package org.p2p.lending.club.api.transaction.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.transaction.TransactionAuditor;
import org.p2p.lending.club.util.JsonSerializer;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public class LogTransactionAuditor implements TransactionAuditor {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void audit(Transaction transaction, String message) {
        LOG.info("Transaction {} with message: {}", transaction.getTrasactionId(), message);
        if(LOG.isDebugEnabled())
        {
            LOG.debug("Details {} " , JsonSerializer.toGeneralJson(transaction));
        }
    }
}
