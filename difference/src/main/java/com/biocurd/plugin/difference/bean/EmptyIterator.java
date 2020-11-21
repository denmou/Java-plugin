package com.biocurd.plugin.difference.bean;

import java.util.Iterator;

/**
 * @author denmou
 * @date 2020/11/2 14:23
 */
public class EmptyIterator implements Iterator<Object> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}
