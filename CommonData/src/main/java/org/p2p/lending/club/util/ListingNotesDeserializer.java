package org.p2p.lending.club.util;

import com.google.gson.*;
import org.p2p.lending.club.api.data.impl.ListingNotesResponse;

import java.lang.reflect.Type;

/**
 * Created by tczhaodachuan on 8/10/2015.
 */
public class ListingNotesDeserializer implements JsonDeserializer<ListingNotesResponse> {
    private final String asOfDate = "asOfDate";
    private final String loans = "loans";
    @Override
    public ListingNotesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String date = jsonObject.get(asOfDate).getAsString();
        ListingNotesResponse listingNotesResponse = new ListingNotesResponse(date);
        JsonArray jsonArray = jsonObject.get(loans).getAsJsonArray();
        jsonArray.forEach(jsonElement -> { });
        return null;
    }
}
