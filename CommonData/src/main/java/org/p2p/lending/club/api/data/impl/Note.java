package org.p2p.lending.club.api.data.impl;

import org.p2p.lending.club.api.data.VertexData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tczhaodachuan on 7/13/2015.
 * Documents could be found
 * https://www.lendingclub.com/developers/notes-owned.action
 */
public class Note implements VertexData {
    private final String noteId;
    private final String loanId;
    private final Map<String, Object> fieldsMap;

    public Note(String noteId, String loanId, Map<String, Object> fieldsMap) {
        this.noteId = noteId;
        this.loanId = loanId;
        this.fieldsMap = new HashMap<>(fieldsMap);
        // has to register noteId and loanId
        this.fieldsMap.put(EnumNote.NOTE_ID.value(), noteId);
        this.fieldsMap.put(EnumNote.LOAN_ID.value(), loanId);
    }

    public String getNoteId() {
        return noteId;
    }

    public String getLoanId() {
        return loanId;
    }

    public Map<String, Object> getFieldsMap() {
        return fieldsMap;
    }

    public Integer getInteger(EnumNote enumNote) {
        Object val = fieldsMap.get(enumNote.value());
        return val == null ? null : (Integer) val;
    }

    public Double getDouble(EnumNote enumNote) {
        Object val = fieldsMap.get(enumNote.value());
        return val == null ? null : (Double) val;
    }

    public String getString(EnumNote enumNote) {
        Object val = fieldsMap.get(enumNote.value());
        return val == null ? null : (String) val;
    }

    @Override
    public boolean equals(VertexData vertexData) {
        if (vertexData == null) {
            return false;
        }
        if (getClass() != vertexData.getClass()) {
            return false;
        }

        Note note = (Note) vertexData;
        return noteId != null && loanId.equals(note.getNoteId());
    }
}
