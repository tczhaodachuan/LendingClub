package org.p2p.lending.club.util;

import com.google.gson.*;
import org.p2p.lending.club.api.data.impl.Portfolio;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tczhaodachuan on 8/16/2015.
 */
public class ListOfPortfolioDeserializer implements JsonDeserializer<List<Portfolio>> {
    private final String myPortfolios = "myPortfolios";

    @Override
    public List<Portfolio> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Portfolio> portfolios = new LinkedList<>();
        JsonArray jsonArray = json.getAsJsonObject().get(myPortfolios).getAsJsonArray();
        jsonArray.forEach(jsonElement -> {
            Portfolio portfolio = JsonSerializer.fromJson(jsonElement.toString(), Portfolio.class);
            portfolios.add(portfolio);
        });
        return portfolios;
    }
}
