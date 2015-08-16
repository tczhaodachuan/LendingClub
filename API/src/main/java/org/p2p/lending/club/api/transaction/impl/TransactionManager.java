package org.p2p.lending.club.api.transaction.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.p2p.lending.club.api.QueryAPI;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;
import org.p2p.lending.club.api.order.Order;
import org.p2p.lending.club.api.transaction.ExceptionHandler;
import org.p2p.lending.club.api.transaction.TransactionAuditor;
import org.p2p.lending.club.message.Consumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by tczhaodachuan on 7/21/2015.
 */
public class TransactionManager implements Consumer.Listener<Note> {
    private static final Logger LOG = LogManager.getLogger();
    private final ValueFilter valueFilter;
    private final QueryAPI queryAPI;
    private ExceptionHandler exceptionHandler;
    private TransactionAuditor transactionAuditor;
    private LinkedBlockingQueue<Note> blockingQueue;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private int requestedAmount;
    private int transactionBatchAmount;
    private long transactionBatchDelayTime;

    public TransactionManager(ValueFilter valueFilter, QueryAPI queryAPI) {
        this.valueFilter = valueFilter;
        this.queryAPI = queryAPI;
        blockingQueue = new LinkedBlockingQueue<Note>();
        exceptionHandler = new LogExceptionHandler();
        transactionAuditor = new LogTransactionAuditor();
    }

    public void init() {
        requestedAmount = 25;
        transactionBatchAmount = 100;
        transactionBatchDelayTime = 3000;
        Thread thread = new Thread(this);
        thread.setName("TransactionManager");
        thread.start();
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
        isRunning.set(true);
        try {
            processNextNote();
        } catch (Exception e) {
            exceptionHandler.onException(e, this);
        }
    }

    private void processNextNote() {
        Transaction transaction = new Transaction(queryAPI.getInvestorId());
        transactionAuditor.audit(transaction, "Created empty transaction");
        int count = 0;
        while (isRunning()) {
            try {
                Note note = blockingQueue.take();
                LOG.info("Count {}, loanID {}, noteId {}", ++count, note.getLoanId(), note.getNoteId());
                if (valueFilter.isAllowed(note)) {
                    LOG.info("Starting to make an order");
                    Order order = new Order(note, String.valueOf(requestedAmount));
                    transaction.addOrder(order);
                    transactionAuditor.audit(transaction, "an Order " + order.getNote().getLoanId() + " has been added into transaction " + transaction.getTrasactionId());
                    if (transaction.getNumberOfOrders() % transactionBatchAmount == 0) {
                        // submit the batch transaction
                        submitTransaction(transaction);
                        // new transaction has been created
                        transaction = new Transaction(queryAPI.getInvestorId());

                        Thread.sleep(transactionBatchDelayTime);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("Stop to process notes");
    }

    protected void submitTransaction(Transaction transaction) {
        transactionAuditor.audit(transaction, "Starting to submit transaction");
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH mm");
            DateTime dateTime = new DateTime();
            String dateStr = fmt.print(dateTime);
            if (queryAPI.createPortfolio(dateStr, "This portfolios created by TransactionManager") != null) {
                queryAPI.submitTransaction(transaction);
                transactionAuditor.audit(transaction, "Successfully submitted transaction");
            } else {
                transactionAuditor.audit(transaction, "Failed to create a portfolio");
            }
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
