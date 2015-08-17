package org.p2p.lending.club.api.order;

import java.util.List;

/**
 * Created by tczhaodachuan on 8/16/2015.
 */
public class OrderResponse {
    private final String orderInstructId;
    private final List<OrderConfirmation> orderConfirmations;

    public OrderResponse(String orderInstructId, List<OrderConfirmation> orderConfirmations) {
        this.orderInstructId = orderInstructId;
        this.orderConfirmations = orderConfirmations;
    }

    public String getOrderInstructId() {
        return orderInstructId;
    }

    public List<OrderConfirmation> getOrderConfirmations() {
        return orderConfirmations;
    }

    public class OrderConfirmation
    {
        private final String loanId;
        private final double requestedAmount;
        private final double investedAmount;
        private final String[] executionStatus;

        OrderConfirmation(String loanId, double requestedAmount, double investedAmount, String[] executionStatus) {
            this.loanId = loanId;
            this.requestedAmount = requestedAmount;
            this.investedAmount = investedAmount;
            this.executionStatus = executionStatus;
        }

        public String getLoanId() {
            return loanId;
        }

        public double getRequestedAmount() {
            return requestedAmount;
        }

        public double getInvestedAmount() {
            return investedAmount;
        }

        public String[] getExecutionStatus() {
            return executionStatus;
        }
    }
}
