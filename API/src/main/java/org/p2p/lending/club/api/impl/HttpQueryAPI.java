package org.p2p.lending.club.api.impl;

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
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
    private CloseableHttpClient httpClient;
    // applying thread safe ClientConnManager to HttpClient is important. Cause the @TransactionManager and @InventoryFilter may share
    // the HttpClient at the same time.
    public HttpQueryAPI(int maxTotal, String token) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
        httpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();
        tokenString = token;
    }

    @Override
    public List<Note> getOwnedNotes() {
        return null;
    }

    @Override
    public List<Note> getListedNotes() {
        try {
            URI uri = new URIBuilder()
                    .setScheme(scheme)
                    .setHost(host)
                    .setPath(listNotesPath)
                    .setParameter(showAll, "false")
                    .build();
            HttpGet httpGet = new HttpGet(uri);
            LOG.info("URI is {}", httpGet.getURI());

            String response = httpGet(httpGet);
            LOG.info("Get listed notes {}", response);
        } catch (URISyntaxException e) {
            LOG.error(e, e);
        }
        return null;
    }

    @Override
    public List<Note> getAllListedNotes() {
        return null;
    }

    @Override
    public double getAvailableCash() {
        return 0;
    }

    @Override
    public boolean submitTransaction(Transaction transaction) {
        return false;
    }

    @Override
    public boolean createPortfolio(String investorId, String portfolioName, String portfolioDescription) {
        return false;
    }

    protected String httpGet(HttpGet httpGet)
    {
        // add token to each get request, since stateless API.
        httpGet.addHeader(authentication, tokenString);
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            String response = httpClient.execute(httpGet, responseHandler);
            return response;
        } catch (IOException e) {
            LOG.error(e,e);
        }

        return null;
    }


    static class ResponseHandler implements org.apache.http.client.ResponseHandler<String> {
        private final int bufferLength = 1024;
        private final int maxCount = 5;

        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity httpEntity = response.getEntity();
            if (statusLine.getStatusCode() != 200) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }

            if (httpEntity == null) {
                throw new ClientProtocolException("Response contains no content");
            }

            ContentType contentType = ContentType.getOrDefault(httpEntity);
            Charset charset = contentType.getCharset();
            Reader reader = new InputStreamReader(httpEntity.getContent(), charset);
            StringBuilder sb = new StringBuilder();
            char[] charArray = new char[bufferLength];
            int count = 1;
            while (reader.read(charArray) != -1 && count < maxCount) {
                sb.append(charArray);
                count++;
                charArray = new char[bufferLength * count];
            }

            if (count >= maxCount) {
                throw new ClientProtocolException("Response contains too much data");
            } else {
                sb.append(charArray);
            }

            return sb.toString();
        }
    }
}
