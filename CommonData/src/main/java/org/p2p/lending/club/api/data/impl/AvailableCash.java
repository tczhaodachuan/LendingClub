package org.p2p.lending.club.api.data.impl;

/**
 * Created by tczhaodachuan on 8/12/2015.
 */
public class AvailableCash {
    private final String investorId;
    private final double availableCash;

    public AvailableCash(String investorId, double availableCash) {
        this.investorId = investorId;
        this.availableCash = availableCash;
    }

    public String getInvestorId() {
        return investorId;
    }

    public double getAvailableCash() {
        return availableCash;
    }
}
