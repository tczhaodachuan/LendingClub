package org.p2p.lending.club.util;

import com.google.gson.*;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by tczhaodachuan on 8/10/2015.
 */
public class ListedNotesDeserializer implements JsonDeserializer<ListedNotes> {
    private final String asOfDate = "asOfDate";
    private final String loans = "loans";
    private ListedNotes listedNotes;
    private static Function<JsonElement, Note> CONVERTER;

    static {
        CONVERTER = jsonElement -> {
            String id = jsonElement.getAsJsonObject().get(EnumNote.ID.value()).getAsString();
            Map<String, Object> fieldsMap = new LinkedHashMap<>();
            // not null attributes will be mapped in fieldsMap.
            jsonElement.getAsJsonObject().entrySet().stream()
                    .filter(entry -> !entry.getValue().isJsonNull())
                    .forEach(entry -> {
                        fieldsMap.put(entry.getKey(), entry.getValue().getAsString());
                    });
            Note note = new Note(id, id, fieldsMap);
            return note;
        };
    }

    @Override
    public ListedNotes deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String date = jsonObject.get(asOfDate).getAsString();
        listedNotes = new ListedNotes(date);
        JsonArray jsonArray = jsonObject.get(loans).getAsJsonArray();
        jsonArray.forEach(jsonElement -> {
            Note note = CONVERTER.apply(jsonElement);
            listedNotes.addNote(note);
        });
        return listedNotes;
    }
}
