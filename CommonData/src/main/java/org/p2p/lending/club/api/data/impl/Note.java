package org.p2p.lending.club.api.data.impl;

import org.p2p.lending.club.api.data.VertexData;

/**
 * Created by tczhaodachuan on 7/13/2015.
 * Documents could be found
 * https://www.lendingclub.com/developers/notes-owned.action
 * "loanId":11111,
 * "noteId":22222,
 * "orderId":33333,
 * "interestRate":13.57,
 * "loanLength":36,
 * "loanStatus":"Late (31-120 days)",
 * "grade":"C",
 * "loanAmount":10800,
 * "noteAmount":25,
 * "paymentsReceived":5.88,
 * "issueDate":"2009-11-12T06:34:02.000-08:00",
 * "orderDate":"2009-11-05T09:33:50.000-08:00",
 * "loanStatusDate":"2013-05-20T13:13:53.000-07:00"
 */
public class Note implements VertexData {
    private final String noteId;
    private final String loanId;
    private Grades grade;
    private LoanLength loanLength = LoanLength.SHORT;
    private String orderId;
    private double interestRate;
    private String loanStatus;
    private int loanAmount;
    private String noteAmount;
    private double paymentReceived;
    private long issueDate;
    private long orderDate;
    private long loanStatusDate;

    public Note(String noteId, String loanId) {
        this.noteId = noteId;
        this.loanId = loanId;
    }

    public void setGrade(Grades grade) {
        this.grade = grade;
    }

    public void setLoanLength(LoanLength loanLength) {
        this.loanLength = loanLength;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setNoteAmount(String noteAmount) {
        this.noteAmount = noteAmount;
    }

    public void setPaymentReceived(double paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public void setIssueDate(long issueDate) {
        this.issueDate = issueDate;
    }

    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    public void setLoanStatusDate(long loanStatusDate) {
        this.loanStatusDate = loanStatusDate;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getLoanId() {
        return loanId;
    }

    public Grades getGrade() {
        return grade;
    }

    public LoanLength getLoanLength() {
        return loanLength;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public String getNoteAmount() {
        return noteAmount;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public long getIssueDate() {
        return issueDate;
    }

    public long getOrderDate() {
        return orderDate;
    }

    public long getLoanStatusDate() {
        return loanStatusDate;
    }

    @Override
    public boolean equals(VertexData vertexData) {
        if (vertexData == null) {
            return false;
        }
        if (getClass() != vertexData.getClass()) {
            return false;
        }

        Note note = (Note) vertexData;
        return noteId != null && loanId.equals(note.getNoteId());
    }

    public enum LoanLength {
        SHORT(36), LONG(60);
        public int length;

        LoanLength(int i) {
            this.length = i;
        }


        @Override
        public String toString() {
            return String.valueOf(length);
        }
    }

    public enum Grades {
        A, B, C, D, E, F, G;
    }
}
