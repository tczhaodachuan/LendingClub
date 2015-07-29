package org.p2p.lending.club.message.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.p2p.lending.club.api.QueryAPI;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;
import org.p2p.lending.club.api.order.Order;
import org.p2p.lending.club.api.transaction.LogTransactionAuditor;
import org.p2p.lending.club.api.transaction.Transaction;
import org.p2p.lending.club.api.transaction.TransactionAuditor;
import org.p2p.lending.club.message.Consumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by tczhaodachuan on 7/21/2015.
 */
public class TransactionManager implements Consumer.Listener<Note>, Runnable {
    private static final Logger LOG = LogManager.getLogger();
    private final String accountId;
    private final ValueFilter valueFilter;
    private final QueryAPI queryAPI;
    private ExceptionHandler exceptionHandler;
    private TransactionAuditor transactionAuditor;
    private LinkedBlockingQueue<Note> blockingQueue;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private int requestedAmount;
    private int transactionBatchAmount;
    private long transactionBatchDelayTime;

    public TransactionManager(String accountId, ValueFilter valueFilter, QueryAPI queryAPI) {
        this.valueFilter = valueFilter;
        this.queryAPI = queryAPI;
        this.accountId = accountId;
        blockingQueue = new LinkedBlockingQueue<Note>();
        exceptionHandler = new LogExceptionHandler();
        transactionAuditor = new LogTransactionAuditor();
    }

    public void init() {
        requestedAmount = 25;
        transactionBatchAmount = 10;
        transactionBatchDelayTime = 3000;
        Thread processNotes = new Thread(this);
        processNotes.setName("ProcessNotes");
        processNotes.start();
        isRunning.set(true);
    }

    public boolean isRunning() {
        return isRunning.get();
    }

    public void stop() {
        isRunning.set(false);
    }

    @Override
    public void onMessage(Note note) {
        blockingQueue.offer(note);
    }

    @Override
    public void run() {
        try {
            processNextNote();
        } catch (Exception e) {
            exceptionHandler.onException(e, this);
        }
    }

    private void processNextNote() {
        Transaction transaction = new Transaction(accountId);
        transactionAuditor.audit(transaction, "Created empty transaction");
        while (isRunning()) {
            Note note = blockingQueue.poll();
            if (valueFilter.isAllowed(note)) {
                LOG.info("Starting to make an order");
                Order order = new Order(note, String.valueOf(requestedAmount));
                transaction.addOrder(order);
                transactionAuditor.audit(transaction, "an Order added into transaction");
                if (transaction.getNumberOfOrders() % transactionBatchAmount == 0) {
                    // submit the batch transaction
                    submitTransaction(transaction);
                    // new transaction has been created
                    transaction = new Transaction(accountId);
                    try {
                        Thread.sleep(transactionBatchDelayTime);
                    } catch (InterruptedException e) {
                        LOG.warn(e, e);
                    }
                }
            }
        }
        LOG.info("Stop to process notes");
    }

    protected void submitTransaction(Transaction transaction) {
        transactionAuditor.audit(transaction, "Starting to submit transaction");
        try {
            queryAPI.submitTransaction(transaction);
            transactionAuditor.audit(transaction, "Successfully submitted transaction");
        } catch (Exception e) {
            transactionAuditor.audit(transaction, "Failed to submit transaction " + e.getMessage());
            exceptionHandler.onException(e, this);
        }
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public void setTransactionBatchAmount(int transactionBatchAmount) {
        this.transactionBatchAmount = transactionBatchAmount;
    }

    public void setTransactionBatchDelayTime(long transactionBatchDelayTime) {
        this.transactionBatchDelayTime = transactionBatchDelayTime;
    }

    public void setTransactionAuditor(TransactionAuditor transactionAuditor) {
        this.transactionAuditor = transactionAuditor;
    }

    class LogExceptionHandler implements ExceptionHandler {

        @Override
        public void onException(Exception e, TransactionManager transactionManager) {
            LOG.error(e, e);
            transactionManager.stop();
        }
    }
}
