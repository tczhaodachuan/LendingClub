package org.p2p.lending.club.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.p2p.lending.club.api.data.impl.Note;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by tczhaodachuan on 8/12/2015.
 */
public class NoteDeserializer implements JsonDeserializer<List<Note>> {
    @Override
    public List<Note> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
