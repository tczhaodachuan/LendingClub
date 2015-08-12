package org.p2p.lending.club.api.impl;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.AvailableCash;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by tczhaodachuan on 8/10/2015.
 */
public class HttpQueryAPITest {
    private static String LISTED_NOTE_JSON;
    private static String OWNED_NOTE_JSON;
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
            LISTED_NOTE_JSON = sb.toString();
        } catch (IOException e) {
            fail(e.toString());
        }

        try (FileReader fileReader = new FileReader("src/test/resources/ownedNotes.JSON");
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            StringBuffer sb = new StringBuffer();
            bufferedReader.lines().forEach(line -> {
                sb.append(line).append("\n");
            });
            OWNED_NOTE_JSON = sb.toString();
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    @Before
    public void setUp() throws Exception {
        mockHttpClient = mock(CloseableHttpClient.class);
        // mocking token and investorId
        httpQueryAPI = new HttpQueryAPI(mockHttpClient, "mockToken", "1234");
    }

    @Test
    public void testGetOwnedNotes() throws Exception {
        when(mockHttpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn(OWNED_NOTE_JSON);
        List<Note> noteList = httpQueryAPI.getOwnedNotes();
        verify(mockHttpClient).execute(any(HttpGet.class), any(ResponseHandler.class));
        assertTrue(noteList.size() == 227);
    }

    @Test
    public void testGetListedNotes() throws Exception {
        when(mockHttpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn(LISTED_NOTE_JSON);
        ListedNotes listedNotes = httpQueryAPI.getListedNotes();
        verify(mockHttpClient).execute(any(HttpGet.class), any(ResponseHandler.class));
        assertTrue(listedNotes.getAsOfDate().equals("2015-08-10T17:23:19.122-07:00"));
        assertTrue(listedNotes.getListedNotes().size() == 155);
    }

    @Test
    public void testGetAllListedNotes() throws Exception {
        when(mockHttpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn(LISTED_NOTE_JSON);
        ListedNotes listedNotes = httpQueryAPI.getAllListedNotes();
        verify(mockHttpClient).execute(any(HttpGet.class), any(ResponseHandler.class));
        assertTrue(listedNotes.getAsOfDate().equals("2015-08-10T17:23:19.122-07:00"));
        assertTrue(listedNotes.getListedNotes().size() == 155);
    }

    @Test
    public void testGetAvailableCash() throws Exception {
        String availableCash = "{\"investorId\":51682970,\"availableCash\":4326.19}";
        when(mockHttpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn(availableCash);
        AvailableCash cash = httpQueryAPI.getAvailableCash();
        verify(mockHttpClient).execute(any(HttpGet.class), any(ResponseHandler.class));
        assertTrue(cash.getInvestorId().equals("51682970"));
        assertTrue(cash.getAvailableCash() == 4326.19);
    }

    @Test
    public void testSubmitTransaction() throws Exception {
        //httpQueryAPI = new HttpQueryAPI(10, "M3Pf8h9Dsl+37QwDsNEUYn63aj8=", "51682970");
    }

    @Test
    public void testCreatePortfolio() throws Exception {

    }
}