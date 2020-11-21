package com.biocurd.plugin.difference.node;

import com.biocurd.plugin.difference.constant.StateEnum;

import java.util.TreeMap;

/**
 * @author denmou
 * @date 2020/10/15 17:58
 */
public class RecordValue implements IRecord {
    private final StateEnum state;
    private final Object invalid;
    private final Object value;

    private RecordValue(StateEnum state, Object invalid, Object value) {
        this.state = state;
        this.invalid = invalid;
        this.value = value;
    }

    public static RecordValue untouched() {
        return new RecordValue(StateEnum.UNTOUCHED, null, null);
    }

    public static RecordValue add(Object value) {
        return new RecordValue(StateEnum.ADDED, null, value);
    }

    public static RecordValue change(Object invalid, Object value) {
        return new RecordValue(StateEnum.CHANGED, invalid, value);
    }

    public static RecordValue remove(Object invalid) {
        return new RecordValue(StateEnum.REMOVED, invalid, null);
    }

    public StateEnum getState() {
        return state;
    }

    public Object getInvalid() {
        return invalid;
    }

    public Object getValue() {
        return value;
    }
}
