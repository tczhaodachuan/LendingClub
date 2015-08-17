package org.p2p.lending.club.api.impl;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.p2p.lending.club.api.data.impl.AvailableCash;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.data.impl.Portfolio;
import org.p2p.lending.club.api.order.Order;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
    private static String CREATED_PORTFOLIO_JSON;
    private static String OWNED_PORTFOLIO_JSON;
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

        try (FileReader fileReader = new FileReader("src/test/resources/portfolioCreated.JSON");
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            StringBuffer sb = new StringBuffer();
            bufferedReader.lines().forEach(line -> {
                sb.append(line).append("\n");
            });
            CREATED_PORTFOLIO_JSON = sb.toString();
        } catch (IOException e) {
            fail(e.toString());
        }

        try (FileReader fileReader = new FileReader("src/test/resources/myPortfolios.JSON");
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            StringBuffer sb = new StringBuffer();
            bufferedReader.lines().forEach(line -> {
                sb.append(line).append("\n");
            });
            OWNED_PORTFOLIO_JSON = sb.toString();
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
        httpQueryAPI = new HttpQueryAPI(10, "acYnrgakzqRMvDkmolYiy2euT+Y=", "51682970");
        Note note = new Note("58271093", "58271093", new HashMap<>());
        Order order = new Order(note, "25");
        order.setPortfolioId(58062043);
        Transaction transaction = new Transaction("51682970");
        transaction.addOrder(order);
        //boolean result = httpQueryAPI.submitTransaction(transaction);
    }

    @Test
    public void testGetPortfolios() throws IOException {
        when(mockHttpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn(OWNED_PORTFOLIO_JSON);
        List<Portfolio> portfolios = httpQueryAPI.getPortfolios();
        verify(mockHttpClient).execute(any(HttpGet.class), any(ResponseHandler.class));
        assertTrue(portfolios.size() == 3);
        portfolios.stream().forEach(portfolio -> {
            System.out.println("portfolio.getPortfolioId(), portfolio.getPortfolioName() = " + portfolio.getPortfolioId() + portfolio.getPortfolioName());
        });
    }

    @Test
    public void testCreatePortfolio() throws Exception {
        when(mockHttpClient.execute(any(HttpPost.class), any(ResponseHandler.class))).thenReturn(CREATED_PORTFOLIO_JSON);
        Portfolio portfolio = httpQueryAPI.createPortfolio("whatever", "testing");
        verify(mockHttpClient).execute(any(HttpPost.class), any(ResponseHandler.class));
        assertTrue(portfolio.getPortfolioId() == 58062042);
        assertTrue(portfolio.getPortfolioName().equals("2015-08-16 19 02"));
        assertTrue(portfolio.getPortfolioDescription().equals("Auto created portfolio, this portfolios is created by API."));
    }
}