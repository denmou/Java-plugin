package com.biocurd.plugin.difference.resolver;

import com.biocurd.plugin.difference.bean.EmptyIterator;
import com.biocurd.plugin.difference.exception.DifferenceException;
import com.biocurd.plugin.difference.node.IRecord;
import com.biocurd.plugin.difference.node.RecordNode;

import java.util.*;

/**
 * @author denmou
 * @date 2020/10/21 15:05
 */
public class CollectionResolver extends AbstractResolver<Collection<?>> {
    @Override
    public IRecord compare(Collection<?> source, Collection<?> target) throws DifferenceException {
        RecordNode record = new RecordNode();
        int i = 0;
        Iterator<?> sourceIterator = source != null ? source.iterator() : new EmptyIterator();
        Iterator<?> targetIterator = target != null ? target.iterator() : new EmptyIterator();
        while (sourceIterator.hasNext()) {
            Object sourceValue = sourceIterator.next();
            Object targetValue = null;
            if (targetIterator.hasNext()) {
                targetValue = targetIterator.next();
            }
            String key = String.valueOf(i++);
            IRecord node = config.getDefaultResolver().compare(sourceValue, targetValue);
            record.addNode(key, node);
        }
        while (targetIterator.hasNext()) {
            Object targetValue = targetIterator.next();
            String key = String.valueOf(i++);
            record.addNode(key, config.getDefaultResolver().compare(null, targetValue));
        }
        return record;
    }
}
