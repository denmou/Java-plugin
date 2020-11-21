package com.biocurd.plugin.difference.resolver;

import com.biocurd.plugin.difference.exception.DifferenceException;
import com.biocurd.plugin.difference.node.IRecord;
import com.biocurd.plugin.difference.node.RecordNode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author denmou
 * @date 2020/10/15 18:06
 */
public class DefaultResolver extends AbstractResolver<Object> {
    private <T> Field[] getField(T source, T target) {
        Class<?> clazz = null;
        if (source != null) {
            clazz = source.getClass();
        } else if (target != null) {
            clazz = target.getClass();
        }
        if (clazz != null) {
            List<Field> list = new ArrayList<>();
            while (clazz != null && clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();
                list.addAll(Arrays.asList(fields));
                clazz = clazz.getSuperclass();
            }
            return list.toArray(new Field[0]);
        }
        return null;
    }

    private Object[] getValue(Field field, Object source, Object target) throws DifferenceException {
        Object[] value = new Object[2];
        try {
            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible(true);
            }
            if (source != null) {
                value[0] = field.get(source);
            }
            if (target != null) {
                value[1] = field.get(target);
            }
            if (!accessible) {
                field.setAccessible(false);
            }
        } catch (IllegalAccessException e) {
            throw new DifferenceException("Failed to get property value", e);
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private IRecord compareValue(Object source, Object target) throws DifferenceException {
        RecordNode record = new RecordNode();
        Field[] fields = getField(source, target);
        if (fields != null) {
            for (Field field : fields) {
                String name = field.getName();
                Object[] value = getValue(field, source, target);
                if (value[0] != null || value[1] != null) {
                    Class<?> returnType = field.getType();
                    AbstractResolver<?> resolver = config.getResolver(returnType);
                    IRecord node;
                    if (resolver != null) {
                        node = ((AbstractResolver<Object>) resolver).compare(value[0], value[1]);
                    } else if (returnType.isPrimitive()) {
                        node = config.getPrimitiveResolver().compare(value[0], value[1]);
                    } else if (isPrimitiveType(returnType)) {
                        node = config.getValueResolver().compare(value[0], value[1]);
                    } else {
                        node = config.getDefaultResolver().compare(value[0], value[1]);
                    }
                    record.addNode(name, node);
                }
            }
        }
        return record;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IRecord compare(Object source, Object target) throws DifferenceException {
        Class<?> clazz;
        if (source != null) {
            clazz = source.getClass();
        } else if (target != null) {
            clazz = target.getClass();
        } else {
            return new RecordNode();
        }
        AbstractResolver<?> resolver = config.getResolver(clazz);
        if (resolver != null) {
            return ((AbstractResolver<Object>) resolver).compare(source, target);
        } else if (clazz.isPrimitive()) {
            return config.getPrimitiveResolver().compare(source, target);
        } else if (isPrimitiveType(clazz)) {
            return config.getValueResolver().compare(source, target);
        } else {
            return compareValue(source, target);
        }
    }
}
