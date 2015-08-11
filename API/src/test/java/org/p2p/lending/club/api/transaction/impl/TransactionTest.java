package org.p2p.lending.club.api.transaction.impl;

import org.junit.Test;
import org.p2p.lending.club.api.data.impl.NoteOwned;
import org.p2p.lending.club.api.order.Order;
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
        NoteOwned noteOwned = TestObjectsFactory.createNote();
        Order order = new Order(noteOwned, "25");
        transaction.addOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 1);

        noteOwned = TestObjectsFactory.createNote();
        order = new Order(noteOwned, "25");
        transaction.addOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 2);

        transaction.addOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 2);

        transaction.removeOrder(order);
        assertTrue(transaction.getNumberOfOrders() == 1);
    }

    @Test
    public void testGetTrasactionId() throws Exception {
        Transaction transaction = new Transaction("12345");
        assertTrue(transaction.getTrasactionId() >= 1);
        transaction = new Transaction("12345");
        assertTrue(transaction.getTrasactionId() >= 2);
        transaction = new Transaction("12345");
        assertTrue(transaction.getTrasactionId() >= 3);
    }
}