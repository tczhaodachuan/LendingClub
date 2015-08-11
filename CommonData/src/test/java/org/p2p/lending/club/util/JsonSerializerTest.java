package org.p2p.lending.club.util;

import org.junit.Test;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.ListedNotes;
import org.p2p.lending.club.api.data.impl.Note;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by tczhaodachuan on 7/28/2015.
 */
public class JsonSerializerTest {

    @Test
    public void testToGeneralJson() throws Exception {
        Person person = new Person("dachuan", "zhao", 27);
        String json = JsonSerializer.toGeneralJson(person);
        assertTrue(json.contains("zhao"));
        assertTrue(json.contains("27"));
        assertTrue(json.contains("address"));
        assertTrue(json.contains("citizenship"));

        Map<String, Object> map = new HashMap<>();
        Note note = new Note("1", "2", map);
        json = JsonSerializer.toGeneralJson(note);
        assertTrue(json.contains("noteId"));
        assertTrue(json.contains("loanId"));

        map.put(EnumNote.GRADE.value(), "A");
        map.put(EnumNote.LOAN_AMOUNT.value(), 30000);
        note = new Note("1", "2", map);
        json = JsonSerializer.toGeneralJson(note);
        assertTrue(json.contains("loanAmount"));
        assertTrue(json.contains("30000"));
        assertTrue(json.contains("grade"));
        assertTrue(json.contains("A"));
    }

    @Test
    public void testFromJson() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "A");
        map.put(EnumNote.LOAN_AMOUNT.value(), "30000");
        Note note = new Note("1", "2", map);
        String json = JsonSerializer.toGeneralJson(note);
        System.out.println("json = " + json);

        Note fromJson = JsonSerializer.fromJson(json, Note.class);
        assertEquals("1", fromJson.getNoteId());
        assertEquals("2", fromJson.getLoanId());
        assertEquals("A", fromJson.getString(EnumNote.GRADE));
        // the gson assumes all numbers to be converted to double.
        assertTrue(fromJson.getDouble(EnumNote.LOAN_AMOUNT) == 30000.0);
    }

    @Test
    public void testListedNotesDeserializer() {
        try (FileReader fileReader = new FileReader("src/test/resources/noteListed.json");
             BufferedReader bufferedReader = new BufferedReader(fileReader);) {
            StringBuffer sb = new StringBuffer();
            bufferedReader.lines().forEach(line -> {
                sb.append(line).append("\n");
            });
            ListedNotes listedNotes = JsonSerializer.fromJson(sb.toString(), ListedNotes.class);
            System.out.println("listedNotes.getAsOfDate() = " + listedNotes.getAsOfDate());
            System.out.println("listedNotes.getListedNotes().size() = " + listedNotes.getListedNotes().size());
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    class Person {
        private final String firstName;
        private final String lastName;
        private final int age;
        private String address;
        private String citizenship;

        Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }
    }
}