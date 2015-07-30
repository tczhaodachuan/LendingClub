package org.p2p.lending.club.api.transaction.impl;

import org.apache.logging.log4j.core.Logger;
import org.junit.Before;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.order.Order;

import java.util.HashMap;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by tczhaodachuan on 7/29/2015.
 */
public class LogTransactionAuditorTest {
    private LogTransactionAuditor logTransactionAuditor;
    private Logger mockLogger;

    @Before
    public void setUp() throws Exception {
        mockLogger = mock(Logger.class);
        logTransactionAuditor = new LogTransactionAuditor();
        logTransactionAuditor.setLOG(mockLogger);
    }

    @Test
    public void testAudit() throws Exception {
        Transaction transaction = new Transaction("12345");
        Order order1 = new Order(new Note("1", "1.1", new HashMap<>()), "123456");
        Order order2 = new Order(new Note("2", "1.2", new HashMap<>()), "123456");
        transaction.addOrder(order1);
        transaction.addOrder(order2);
        when(mockLogger.isDebugEnabled()).thenReturn(false);

        logTransactionAuditor.audit(transaction, "create transaction");

        verify(mockLogger).isDebugEnabled();
        verify(mockLogger).info(eq("Transaction {} with message: {}"), eq(1), eq("create transaction"));

        when(mockLogger.isDebugEnabled()).thenReturn(true);
        logTransactionAuditor.audit(transaction, "starting to submit transaction");
        verify(mockLogger).info(eq("Transaction {} with message: {}"), eq(1), eq("starting to submit transaction"));
        verify(mockLogger).debug(anyString(), anyString());
    }
}