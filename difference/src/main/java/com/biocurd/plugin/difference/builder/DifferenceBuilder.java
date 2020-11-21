package com.biocurd.plugin.difference.builder;

import com.biocurd.plugin.difference.config.DifferenceConfig;
import com.biocurd.plugin.difference.resolver.AbstractResolver;

/**
 * @author denmou
 * @date 2020/10/16 10:52
 */
public class DifferenceBuilder {
    private final DifferenceConfig config;

    public DifferenceBuilder() {
        config = new DifferenceConfig();
    }

    public DifferenceConfig configBuild() {
        return config;
    }

    public DifferenceBuilder setPrimitiveResolver(AbstractResolver<Object> resolver) {
        config.setPrimitiveResolver(resolver);
        return this;
    }

    public DifferenceBuilder setObjectResolver(AbstractResolver<Object> resolver) {
        config.setValueResolver(resolver);
        return this;
    }

    public DifferenceBuilder addResolver(Class<?> clazz, AbstractResolver<Object> resolver) {
        config.addResolver(clazz, resolver);
        return this;
    }

    public AbstractResolver<Object> getDefaultResolver() {
        return config.getDefaultResolver();
    }
}
