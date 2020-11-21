package com.biocurd.plugin.difference.bean;

/**
 * @author denmou
 * @date 2020/10/21 15:09
 */
public class CompareField {
    private String key;
    private Object source;
    private Object target;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
