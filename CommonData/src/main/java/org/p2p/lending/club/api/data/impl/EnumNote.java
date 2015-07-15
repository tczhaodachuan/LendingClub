package org.p2p.lending.club.api.data.impl;

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
public enum EnumNote {
    LOAN_ID("loanId"),
    NOTE_ID("noteId"),
    ORDER_ID("orderId"),
    INTEREST_RATE("interestRate"),
    LOAN_LENGTH("loanLength"),
    LOAN_STATUS("loanStatus"),
    GRADE("grade"),
    LOAN_AMOUNT("loanAmount"),
    NOTE_AMOUNT("noteAmount"),
    PAYMENTS_RCVD("paymentsReceived"),
    ISSUE_DATE("issueDate"),
    ORDER_DATE("orderDate"),
    STATUS_DATE("loanStatusDate");

    private final String val;

    EnumNote(String val) {
        this.val = val;
    }

    public String value() {
        return val;
    }

    public static EnumNote getEnumTagOf(String val)
    {
        EnumNote[] enumNotes = values();
        for(EnumNote enumNote : enumNotes)
        {
            if(enumNote.value().equals(val))
            {
                return enumNote;
            }
        }
        return null;
    }
}
