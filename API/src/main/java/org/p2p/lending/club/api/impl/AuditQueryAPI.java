package org.p2p.lending.club.api.impl;

import org.p2p.lending.club.api.transaction.impl.Transaction;

/**
 * Created by tczhaodachuan on 8/17/2015.
 * This class is used for UAT, DEV environment for auditing purpose.
 */
public class AuditQueryAPI extends HttpQueryAPI {

    public AuditQueryAPI(int maxTotal, String token, String investorId) {
        super(maxTotal, token, investorId);
    }

    @Override
    public boolean submitTransaction(Transaction transaction) {
        return true;
    }

}
