package org.p2p.lending.club.api.impl;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.ListedNotes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by tczhaodachuan on 8/10/2015.
 */
public class HttpQueryAPITest {
    private static String JSON;
    private HttpQueryAPI httpQueryAPI;
    private CloseableHttpClient mockHttpClient;

    @BeforeClass
    public static void init() {
        try (FileReader fileReader = new FileReader("src/test/resources/noteListed.JSON");
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            StringBuffer sb = new StringBuffer();
            bufferedReader.lines().forEach(line -> {
                sb.append(line).append("\n");
            });
            JSON = sb.toString();
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    @Before
    public void setUp() throws Exception {
        //httpQueryAPI = new HttpQueryAPI(10, "M3Pf8h9Dsl+37QwDsNEUYn63aj8=");
        mockHttpClient = mock(CloseableHttpClient.class);
        httpQueryAPI = new HttpQueryAPI(mockHttpClient, "mockToken");
    }

    @Test
    public void testGetOwnedNotes() throws Exception {

    }

    @Test
    public void testGetListedNotes() throws Exception {
        when(mockHttpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn(JSON);
        ListedNotes listedNotes = httpQueryAPI.getListedNotes();
        verify(mockHttpClient).execute(any(HttpGet.class), any(ResponseHandler.class));
        assertTrue(listedNotes.getAsOfDate().equals("2015-08-10T17:23:19.122-07:00"));
        assertTrue(listedNotes.getListedNotes().size() == 155);
    }

    @Test
    public void testGetAllListedNotes() throws Exception {
        when(mockHttpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn(JSON);
        ListedNotes listedNotes = httpQueryAPI.getAllListedNotes();
        verify(mockHttpClient).execute(any(HttpGet.class), any(ResponseHandler.class));
        assertTrue(listedNotes.getAsOfDate().equals("2015-08-10T17:23:19.122-07:00"));
        assertTrue(listedNotes.getListedNotes().size() == 155);
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