package org.p2p.lending.club.api.transaction.impl;

import org.junit.Test;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.order.Order;
import org.p2p.lending.club.util.JsonSerializer;
import org.p2p.lending.club.util.TestObjectsFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by tczhaodachuan on 7/29/2015.
 */
public class TransactionTest {
    @Test
    public void testAddOrder() throws Exception {
        Transaction transaction = new Transaction("12345");
        assertTrue(transaction.getNumberOfOrders() == 0);
        Note note = TestObjectsFactory.createNote();
        Order order = new Order(note, "25");
        transaction.addOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 1);

        note = TestObjectsFactory.createNote();
        order = new Order(note, "25");
        transaction.addOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 2);

        transaction.addOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 2);

        transaction.removeOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 1);
    }

    @Test
    public void testGetTransactionId() throws Exception {
        Transaction transaction = new Transaction("12345");
        assertTrue(transaction.getTrasactionId() >= 1);
        transaction = new Transaction("12345");
        assertTrue(transaction.getTrasactionId() >= 2);
        transaction = new Transaction("12345");
        assertTrue(transaction.getTrasactionId() >= 3);
    }

    @Test
    public void testTransactionSerialization()
    {
        Transaction transaction = new Transaction("12345");
        Note note = TestObjectsFactory.createNote();
        Order order = new Order(note, "25");
        order.setPortfolioId(12345);
        transaction.addOrder(order);

        note = TestObjectsFactory.createNote();
        order = new Order(note, "25");
        transaction.addOrder(order);

        note = TestObjectsFactory.createNote();
        order = new Order(note, "25");
        order.setPortfolioId(12345);
        transaction.addOrder(order);

        note = TestObjectsFactory.createNote();
        order = new Order(note, "25");
        transaction.addOrder(order);

        note = TestObjectsFactory.createNote();
        order = new Order(note, "25");
        order.setPortfolioId(12345);
        transaction.addOrder(order);

        note = TestObjectsFactory.createNote();
        order = new Order(note, "25");
        order.setPortfolioId(12345);
        transaction.addOrder(order);

        System.out.println(JsonSerializer.toGeneralJson(transaction));
    }
}