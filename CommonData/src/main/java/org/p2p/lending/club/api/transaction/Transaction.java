package org.p2p.lending.club.api.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.order.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by tczhaodachuan on 7/23/2015.
 */
public class Transaction {
    private static AtomicInteger trasactionId = new AtomicInteger();
    private static final Logger LOG = LogManager.getLogger();
    private final String accountId;
    private final Set<String> orderNames;
    private List<Order> orders;

    public Transaction(String accountId) {
        this.accountId = accountId;
        orderNames = new HashSet<>();
        orders = new ArrayList<>();
        LOG.info("Transaction {} has been created " + trasactionId.incrementAndGet());
    }

    public void addOrder(Order order) {
        assertNotNull(order);

        if (orderNames.contains(order.getNote().getNoteId())) {
            LOG.warn("NoteId {} has already been created " + order.getNote().getNoteId());
            return;
        }

        orderNames.add(order.getNote().getNoteId());
        orders.add(order);
        LOG.info("NoteId {} has been added into transaction {} ", order.getNote().getNoteId(), trasactionId.get());
    }

    public void removeOrder(Order order) {
        assertNotNull(order);

        orderNames.remove(order.getNote().getNoteId());
        int index = -1;
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            if (o.getNote().getNoteId().equals(order.getNote().getNoteId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            orders.remove(index);
        }
    }
}
