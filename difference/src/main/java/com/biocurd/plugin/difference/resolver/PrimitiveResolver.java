package com.biocurd.plugin.difference.resolver;

import com.biocurd.plugin.difference.node.IRecord;
import com.biocurd.plugin.difference.node.RecordValue;

/**
 * @author denmou
 * @date 2020/10/15 18:06
 */
public class PrimitiveResolver extends AbstractResolver<Object> {
    @Override
    public IRecord compare(Object source, Object target) {
        if (source == null) {
            return RecordValue.add(target);
        } else if (target == null) {
            return RecordValue.remove(source);
        } else if (source != target) {
            return RecordValue.change(source, target);
        }
        return RecordValue.untouched();
    }
}
