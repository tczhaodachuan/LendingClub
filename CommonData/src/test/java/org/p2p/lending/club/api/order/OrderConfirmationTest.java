package org.p2p.lending.club.api.order;

import org.junit.Before;
import org.junit.Test;
import org.p2p.lending.club.util.JsonSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tczhaodachuan on 8/17/2015.
 */
public class OrderConfirmationTest {

    private String submitOrderJson;
    @Before
    public void setUp() throws Exception {
        submitOrderJson = "{\n" +
                "  \"orderInstructId\": 80732498,\n" +
                "  \"orderConfirmations\": [\n" +
                "    {\n" +
                "      \"loanId\": 58271093,\n" +
                "      \"requestedAmount\": 25.0,\n" +
                "      \"investedAmount\": 25.0,\n" +
                "      \"executionStatus\": [\n" +
                "        \"ORDER_FULFILLED\",\n" +
                "        \"NOTE_ADDED_TO_PORTFOLIO\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Test
    public void testSerialization() throws Exception {
        OrderResponse orderResponse = JsonSerializer.fromJson(submitOrderJson, OrderResponse.class);
        assertTrue(orderResponse.getOrderInstructId().equals("80732498"));
        assertTrue(orderResponse.getOrderConfirmations().size() == 1);
        assertTrue(orderResponse.getOrderConfirmations().get(0).getLoanId().equals("58271093"));
        assertTrue(orderResponse.getOrderConfirmations().get(0).getInvestedAmount() == 25.0);
        assertTrue(orderResponse.getOrderConfirmations().get(0).getRequestedAmount() == 25.0);
        List<String> status = Arrays.asList(orderResponse.getOrderConfirmations().get(0).getExecutionStatus());
        status.stream().forEach(System.out::println);
    }}