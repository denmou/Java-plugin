package com.biocurd.plugin.difference.node;

import com.biocurd.plugin.difference.constant.StateEnum;

import java.util.LinkedHashMap;

/**
 * @author denmou
 * @date 2020/10/15 17:58
 */
public class RecordNode extends LinkedHashMap<String, IRecord> implements IRecord {
    public void addNode(String key, IRecord record) {
        if (record != null) {
            if (record instanceof RecordValue) {
                StateEnum state = ((RecordValue) record).getState();
                if (state != null && state.getCode() > 0) {
                    put(key, record);
                }
            } else if (record instanceof RecordNode){
                if (!((RecordNode) record).isEmpty()) {
                    put(key, record);
                }
            }
        }
    }
}
