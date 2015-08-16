package org.p2p.lending.club.api.data.impl;

/**
 * Created by tczhaodachuan on 8/16/2015.
 */
public class Portfolio {
    public final int portfolioId;
    private final String portfolioName;
    private final String portfolioDescription;

    public Portfolio(int portfolioId, String portfolioName, String portfolioDescription) {
        this.portfolioId = portfolioId;
        this.portfolioName = portfolioName;
        this.portfolioDescription = portfolioDescription;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getPortfolioDescription() {
        return portfolioDescription;
    }
}
