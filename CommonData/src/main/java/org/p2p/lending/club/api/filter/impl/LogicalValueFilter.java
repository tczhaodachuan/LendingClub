package org.p2p.lending.club.api.filter.impl;

import org.p2p.lending.club.api.data.impl.Note;
import org.p2p.lending.club.api.filter.ValueFilter;

import java.util.List;

/**
 * Created by tczhaodachuan on 7/15/2015.
 */
public class LogicalValueFilter implements ValueFilter {
    private final String logic;
    private final List<ValueFilter> filters;
    private boolean not = false;

    public LogicalValueFilter(String logic, List<ValueFilter> filters) {
        this.logic = logic;
        this.filters = filters;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    @Override
    public boolean isAllowed(Note note) {
        if ("and".equals(logic)) {
            for (ValueFilter filter : filters) {
                if (!filter.isAllowed(note)) {
                    return not;
                }
            }
            return !not;
        } else if ("or".equals(logic)) {
            for (ValueFilter filter : filters) {
                if (filter.isAllowed(note)) {
                    return !not;
                }
            }
            return not;
        }

        return false;
    }
}
