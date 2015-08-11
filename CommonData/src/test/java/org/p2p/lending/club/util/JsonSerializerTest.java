package org.p2p.lending.club.util;

import org.junit.Test;
import org.p2p.lending.club.api.data.impl.EnumNote;
import org.p2p.lending.club.api.data.impl.NoteOwned;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        NoteOwned noteOwned = new NoteOwned("1", "2", map);
        json = JsonSerializer.toGeneralJson(noteOwned);
        assertTrue(json.contains("noteId"));
        assertTrue(json.contains("loanId"));

        map.put(EnumNote.GRADE.value(), "A");
        map.put(EnumNote.LOAN_AMOUNT.value(), 30000);
        noteOwned = new NoteOwned("1", "2", map);
        json = JsonSerializer.toGeneralJson(noteOwned);
        assertTrue(json.contains("loanAmount"));
        assertTrue(json.contains("30000"));
        assertTrue(json.contains("grade"));
        assertTrue(json.contains("A"));
    }

    @Test
    public void testFromJson() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put(EnumNote.GRADE.value(), "A");
        map.put(EnumNote.LOAN_AMOUNT.value(), 30000);
        NoteOwned noteOwned = new NoteOwned("1", "2", map);
        String json = JsonSerializer.toGeneralJson(noteOwned);
        System.out.println("json = " + json);

        NoteOwned fromJson = JsonSerializer.fromJson(json, NoteOwned.class);
        assertEquals("1", fromJson.getNoteId());
        assertEquals("2", fromJson.getLoanId());
        assertEquals("A", fromJson.getString(EnumNote.GRADE));
        // the gson assumes all numbers to be converted to double.
        assertTrue(fromJson.getDouble(EnumNote.LOAN_AMOUNT) == 30000.0);
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