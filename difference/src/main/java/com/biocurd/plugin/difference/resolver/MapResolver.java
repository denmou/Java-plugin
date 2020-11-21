package com.biocurd.plugin.difference.resolver;

import com.biocurd.plugin.difference.exception.DifferenceException;
import com.biocurd.plugin.difference.node.IRecord;
import com.biocurd.plugin.difference.node.RecordNode;

import java.util.Map;

/**
 * @author denmou
 * @date 2020/10/21 15:06
 */
public class MapResolver extends AbstractResolver<Map<?, ?>> {
    @Override
    public IRecord compare(Map<?, ?> source, Map<?, ?> target) throws DifferenceException {
        RecordNode record = new RecordNode();
        if (source != null) {
            for (Map.Entry<?, ?> entry : source.entrySet()) {
                String key = String.valueOf(entry.getKey());
                Object targetValue = target.get(entry.getKey());
                IRecord node = config.getDefaultResolver().compare(entry.getValue(), targetValue);
                record.addNode(key, node);
            }
        }
        if (target != null) {
            for (Map.Entry<?, ?> entry : target.entrySet()) {
                if (source == null || !source.containsKey(entry.getKey())) {
                    String key = String.valueOf(entry.getKey());
                    record.addNode(key, config.getDefaultResolver().compare(null, entry.getValue()));
                }
            }
        }
        return record;
    }
}
