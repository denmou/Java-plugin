package com.biocurd.plugin.difference.resolver;

import com.biocurd.plugin.difference.config.DifferenceConfig;
import com.biocurd.plugin.difference.exception.DifferenceException;
import com.biocurd.plugin.difference.node.IRecord;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author denmou
 * @date 2020/10/15 17:36
 */
public abstract class AbstractResolver<T> {
    protected DifferenceConfig config;

    protected boolean isPrimitiveType(Class<?> clazz) {
        if (clazz == String.class || Date.class.isAssignableFrom(clazz)) {
            return true;
        } else {
            try {
                Field field = clazz.getField("TYPE");
                Object value = field.get(null);
                if (value instanceof Class) {
                    return ((Class<?>) value).isPrimitive();
                }
            } catch (NoSuchFieldException | IllegalAccessException ignore) {
            }
        }
        return false;
    }

    /**
     * 比较数据差异
     * @param source 源数据
     * @param target 目标数据
     * @return 差异结果
     * @throws DifferenceException 处理异常
     */
    public abstract IRecord compare(T source, T target) throws DifferenceException;

    public DifferenceConfig getConfig() {
        return config;
    }

    public void setConfig(DifferenceConfig config) {
        this.config = config;
    }
}
