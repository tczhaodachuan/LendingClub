package org.p2p.lending.club.api.data.impl;

import java.util.HashSet;
import java.util.Set;

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
    // below are for owned notes enums
    LOAN_ID("loanId"),
    NOTE_ID("noteId"),
    ORDER_ID("orderId"),
    INTEREST_RATE("interestRate"),
    LOAN_LENGTH("loanLength"),
    LOAN_STATUS("loanStatus"),
    GRADE("grade"),
    NOTE_AMOUNT("noteAmount"),
    PAYMENTS_RCVD("paymentsReceived"),
    ISSUE_DATE("issueDate"),
    ORDER_DATE("orderDate"),
    STATUS_DATE("loanStatusDate"),
    // common used name tag
    LOAN_AMOUNT("loanAmount"),
    // below are for listed notes enums for addressing purpose
    // it may increase in the future
    ID("id"),
    MEMBER_ID("memberId"),
    TERM("term"),
    INT_RATE("intRate"),
    EXP_DEFAULT_RATE("expDefaultRate"),
    HOME_OWNERSHIP("homeOwnership"),
    REVIEW_STATUS("reviewStatus"),
    PURPOSE("purpose"),
    ADDR_ZIP("addrZip"),
    ADDR_STATE("addrState"),
    INVESTOR_COUNT("investorCount"),
    DTI("dti"),
    DELINQ_2YRS("delinq2Yrs"),
    FICORANGE_LOW("ficoRangeLow"),
    FICORANGE_HIGH("ficoRangeHigh"),
    FUNDED_AMOUNT("fundedAmount");

    private final String val;

    EnumNote(String val) {
        this.val = val;
    }

    public String value() {
        return val;
    }

    public static EnumNote getEnumTagOf(String val) {
        EnumNote[] enumNotes = values();
        for (EnumNote enumNote : enumNotes) {
            if (enumNote.value().equals(val)) {
                return enumNote;
            }
        }
        return null;
    }
}
