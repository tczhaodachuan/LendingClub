package org.p2p.lending.club.util;

import com.google.gson.*;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.Note;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by tczhaodachuan on 8/12/2015.
 */
public class ListOfNoteDeserializer implements JsonDeserializer<List<Note>>{
    private final String myNotes = "myNotes";
    private static Function<JsonElement, Note> CONVERTER;
    static {
        CONVERTER = jsonElement -> {
            String loanId = jsonElement.getAsJsonObject().get(EnumNote.LOAN_ID.value()).getAsString();
            String noteId = jsonElement.getAsJsonObject().get(EnumNote.NOTE_ID.value()).getAsString();

            Map<String, Object> fieldsMap = new LinkedHashMap<>();
            // not null attributes will be mapped in fieldsMap.
            jsonElement.getAsJsonObject().entrySet().stream()
                    .filter(entry -> !entry.getValue().isJsonNull())
                    .forEach(entry -> {
                        fieldsMap.put(entry.getKey(), entry.getValue().getAsString());
                    });
            Note note = new Note(noteId, loanId, fieldsMap);
            return note;
        };
    }
    @Override
    public List<Note> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Note> noteList = new ArrayList<>();
        JsonArray jsonArray = jsonElement.getAsJsonObject().get(myNotes).getAsJsonArray();
        jsonArray.forEach(json -> {
            Note note = CONVERTER.apply(json);
            noteList.add(note);
        });
        return noteList;
    }
}
