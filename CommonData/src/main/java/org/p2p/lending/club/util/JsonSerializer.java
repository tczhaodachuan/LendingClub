package org.p2p.lending.club.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.data.impl.Portfolio;
import org.p2p.lending.club.api.transaction.impl.Transaction;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public class JsonSerializer {
    private static final Gson gson;

    static {
        Type listOfNote = new TypeToken<List<Note>>() {
        }.getType();
        Type listOfPortfolio = new TypeToken<List<Portfolio>>() {
        }.getType();
        gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .serializeNulls()
                        // {@ListedNotes} class deserializer registration
                .registerTypeAdapter(ListedNotes.class, new ListedNotesDeserializer())
                        // {@List<Note>} class deserializer registration
                .registerTypeAdapter(listOfNote, new ListOfNoteDeserializer())
                        // {@Transaction} class serializer registration
                .registerTypeAdapter(Transaction.class, new TransactionSerializer())
                        // {@List<Portfolio>} class deserializer registration
                .registerTypeAdapter(listOfPortfolio, new ListOfPortfolioDeserializer())
                .create();

    }

    public static String toGeneralJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
