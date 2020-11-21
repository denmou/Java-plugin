package com.biocurd.plugin.difference.config;

import com.biocurd.plugin.difference.resolver.*;

import java.util.*;

/**
 * @author denmou
 * @date 2020/10/16 10:52
 */
public class DifferenceConfig {
    private AbstractResolver<Object> primitiveResolver;
    private AbstractResolver<Object> valueResolver;
    private AbstractResolver<Object> defaultResolver;
    private final Map<Class<?>, AbstractResolver<?>> resolverMap;

    public DifferenceConfig() {
        primitiveResolver = new PrimitiveResolver();
        valueResolver = new ValueResolver();
        defaultResolver = new DefaultResolver();
        primitiveResolver.setConfig(this);
        valueResolver.setConfig(this);
        defaultResolver.setConfig(this);
        resolverMap = new HashMap<>();
        MapResolver mapResolver = new MapResolver();
        CollectionResolver collectionResolver = new CollectionResolver();
        addResolver(Map.class, mapResolver);
        addResolver(Collection.class, collectionResolver);
    }

    public AbstractResolver<Object> getPrimitiveResolver() {
        return primitiveResolver;
    }

    public void setPrimitiveResolver(AbstractResolver<Object> primitiveResolver) {
        primitiveResolver.setConfig(this);
        this.primitiveResolver = primitiveResolver;
    }

    public AbstractResolver<Object> getValueResolver() {
        return valueResolver;
    }

    public void setValueResolver(AbstractResolver<Object> valueResolver) {
        valueResolver.setConfig(this);
        this.valueResolver = valueResolver;
    }

    public AbstractResolver<Object> getDefaultResolver() {
        return defaultResolver;
    }

    public void setDefaultResolver(AbstractResolver<Object> defaultResolver) {
        this.defaultResolver = defaultResolver;
    }

    public AbstractResolver<?> getResolver(Class<?> clazz) {
        if (resolverMap.containsKey(clazz)) {
            return resolverMap.get(clazz);
        }
        for (Map.Entry<Class<?>, AbstractResolver<?>> entry : resolverMap.entrySet()) {
            if (entry.getKey().isAssignableFrom(clazz)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void addResolver(Class<?> clazz, AbstractResolver<?> resolver) {
        if (resolver != null) {
            resolver.setConfig(this);
            resolverMap.put(clazz, resolver);
        }
    }

    public void removeResolver(Class<?> clazz) {
        resolverMap.remove(clazz);
    }
}
