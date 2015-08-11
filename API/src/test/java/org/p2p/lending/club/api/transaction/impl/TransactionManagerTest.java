package org.p2p.lending.club.api.transaction.impl;

import org.junit.Before;
import org.junit.Test;
import org.p2p.lending.club.api.QueryAPI;
import org.p2p.lending.club.api.filter.ValueFilter;

import static org.mockito.Mockito.mock;

/**
 * Created by tczhaodachuan on 7/30/2015.
 */
public class TransactionManagerTest {
    private TransactionManager transactionManager;
    private ValueFilter valueFilter;
    private QueryAPI queryAPI;

    @Before
    public void setUp() throws Exception {
        valueFilter = mock(ValueFilter.class);
        queryAPI = mock(QueryAPI.class);
        transactionManager = new TransactionManager("1", valueFilter, queryAPI);
    }

    @Test
    public void testInit() throws Exception {

    }

    @Test
    public void testOnMessage() throws Exception {

    }

    @Test
    public void testSubmitTransaction() throws Exception {

    }
}