package org.p2p.lending.club.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.QueryAPI;
import org.p2p.lending.club.api.data.impl.AvailableCash;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.transaction.impl.Transaction;
import org.p2p.lending.club.util.JsonSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by tczhaodachuan on 8/10/2015.
 * Stateless Rest API from Lending Club API, so we don't need to provide consisting context for HTTP query.
 */
public class HttpQueryAPI implements QueryAPI {
    private static final Logger LOG = LogManager.getLogger();
    private final String tokenString;
    private final String investorId;
    private CloseableHttpClient httpClient;
    private ResponseHandler responseHandler;

    // applying thread safe ClientConnManager to HttpClient is important. Cause the @TransactionManager and @InventoryFilter may share
    // the HttpClient at the same time.
    public HttpQueryAPI(int maxTotal, String token, String investorId) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
        httpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();
        tokenString = token;
        this.investorId = investorId;
    }

    /**
     * Used for unit testing.
     *
     * @param httpClient the mocking httpClient
     * @param token      random token String
     * @param investorId
     */
    HttpQueryAPI(CloseableHttpClient httpClient, String token, String investorId) {
        this.httpClient = httpClient;
        this.tokenString = token;
        this.investorId = investorId;
    }

    @Override
    public String getInvestorId() {
        return investorId;
    }

    @Override
    public List<Note> getOwnedNotes() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPath(ACCOUNT_PATH + investorId + "/notes")
                    .build();
            HttpGet httpGet = new HttpGet(uri);
            String response = httpGet(httpGet);
            Type listOfNote = new TypeToken<List<Note>>(){}.getType();
            List<Note> noteList = JsonSerializer.fromJson(response, listOfNote);
            LOG.info("Owned number of notes {}", noteList.size());
            return noteList;
        } catch (URISyntaxException e) {
            LOG.error(e, e);
        }
        return null;
    }

    @Override
    public ListedNotes getListedNotes() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPath(LISTING_NOTES_PATH)
                    .setParameter(SHOW_ALL, "false")
                    .build();
            HttpGet httpGet = new HttpGet(uri);

            String response = httpGet(httpGet);
            if (response == null) {
                LOG.fatal("Failed to get listed notes. From API");
            } else {
                ListedNotes listedNotes = JsonSerializer.fromJson(response.trim(), ListedNotes.class);
                LOG.info("Get listed notes at date {}, number of notes {}", listedNotes.getAsOfDate(), listedNotes.getListedNotes().size());
                return listedNotes;
            }
        } catch (URISyntaxException e) {
            LOG.error(e, e);
        }
        return null;
    }

    @Override
    public ListedNotes getAllListedNotes() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPath(LISTING_NOTES_PATH)
                    .setParameter(SHOW_ALL, "true")
                    .build();
            HttpGet httpGet = new HttpGet(uri);

            String response = httpGet(httpGet);
            if (response == null) {
                LOG.fatal("Failed to get all listed notes. From API");
            } else {
                ListedNotes listedNotes = JsonSerializer.fromJson(response.trim(), ListedNotes.class);
                LOG.info("Get all listed notes at date {}, number of notes {}", listedNotes.getAsOfDate(), listedNotes.getListedNotes().size());
                return listedNotes;
            }
        } catch (URISyntaxException e) {
            LOG.error(e, e);
        }
        return null;
    }

    @Override
    public AvailableCash getAvailableCash() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPath(ACCOUNT_PATH + investorId + "/availablecash")
                    .build();
            HttpGet httpGet = new HttpGet(uri);
            String response = httpGet(httpGet);
            AvailableCash availableCash = JsonSerializer.fromJson(response, AvailableCash.class);
            LOG.info("Available cash {}", response);
            return availableCash;
        } catch (URISyntaxException e) {
            LOG.error(e, e);
        }
        return null;
    }

    @Override
    public boolean submitTransaction(Transaction transaction) {
        return false;
    }

    @Override
    public boolean createPortfolio(String investorId, String portfolioName, String portfolioDescription) {
        return false;
    }

    public void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    protected String httpGet(HttpGet httpGet) {
        // add token to each get request, since stateless API.
        httpGet.addHeader("Accept-Encoding", "gzip,deflate");
        httpGet.addHeader(ACCEPT, "application/json");
        httpGet.addHeader(AUTHENTICATION, tokenString);
        LOG.info("URI is {}", httpGet.getURI());
        if (responseHandler == null) {
            responseHandler = new ResponseHandler();
        }
        try {
            String response = httpClient.execute(httpGet, responseHandler);
            return response;
        } catch (IOException e) {
            LOG.error(e, e);
        }
        return null;
    }


    static class ResponseHandler implements org.apache.http.client.ResponseHandler<String> {
        @Override
        public String handleResponse(HttpResponse response) throws IOException {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity httpEntity = response.getEntity();
            if (statusLine.getStatusCode() != 200) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }

            if (httpEntity == null) {
                throw new ClientProtocolException("Response contains no content");
            }

            ContentType contentType = ContentType.getOrDefault(httpEntity);
            LOG.debug("Response ContentType : {}", contentType.toString());
            Charset charset = Charset.defaultCharset();
            StringBuilder sb = new StringBuilder();
            Reader reader = new InputStreamReader(httpEntity.getContent(), charset);
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                bufferedReader.lines().forEach(line -> {
                    sb.append(line);
                });
            } catch (IOException e) {
                throw new IOException(e);
            }

            return sb.toString();
        }
    }
}
