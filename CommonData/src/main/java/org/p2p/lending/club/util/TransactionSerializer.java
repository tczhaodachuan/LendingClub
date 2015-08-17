package org.p2p.lending.club.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.order.Order;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.lang.reflect.Type;
import java.util.function.Function;

/**
 * Created by tczhaodachuan on 8/16/2015.
 */
public class TransactionSerializer implements com.google.gson.JsonSerializer<Transaction> {

    private static Function<Order, JsonElement> CONVERTER;

    static {
        CONVERTER = order -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(EnumNote.LOAN_ID.value(), Integer.valueOf(order.getNote().getLoanId()));
            jsonObject.addProperty(EnumNote.REQUESTED_AMOUNT.value(), Integer.valueOf(order.getRequestedAmount()));
            if (order.getPortfolioId() != 0) {
                jsonObject.addProperty(EnumNote.PORTFOLIO_ID.value(), order.getPortfolioId());
            }

            return jsonObject;
        };
    }

    @Override
    public JsonElement serialize(Transaction transaction, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("aid", transaction.getAid());
        JsonArray jsonArray = new JsonArray();
        transaction.getOrders().stream().map(CONVERTER).forEach(jsonElement -> {
            jsonArray.add(jsonElement);
        });
        jsonObject.add("orders", jsonArray);
        return jsonObject;
    }
}
