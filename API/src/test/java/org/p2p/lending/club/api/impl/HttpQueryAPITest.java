package org.p2p.lending.club.api.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tczhaodachuan on 8/10/2015.
 */
public class HttpQueryAPITest {
    private HttpQueryAPI httpQueryAPI;
    @Before
    public void setUp() throws Exception {
        httpQueryAPI = new HttpQueryAPI(10, "M3Pf8h9Dsl+37QwDsNEUYn63aj8=");
    }

    @Test
    public void testGetOwnedNotes() throws Exception {

    }

    @Test
    public void testGetListedNotes() throws Exception {
        httpQueryAPI.getListedNotes();
    }

    @Test
    public void testGetAllListedNotes() throws Exception {

    }

    @Test
    public void testGetAvailableCash() throws Exception {

    }

    @Test
    public void testSubmitTransaction() throws Exception {

    }

    @Test
    public void testCreatePortfolio() throws Exception {

    }
}