package org.p2p.lending.club.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.p2p.lending.club.api.data.impl.NoteListed;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public class JsonSerializer {
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(NoteListed.class, new NoteListedDeserializer())
                .create();

    }

    public static String toGeneralJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }
}
