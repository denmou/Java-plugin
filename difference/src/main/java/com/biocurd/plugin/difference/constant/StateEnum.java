package com.biocurd.plugin.difference.constant;

/**
 * @author denmou
 * @date 2020/10/15 17:39
 */
public enum StateEnum {
    //新增
    ADDED(1),
    //更新
    CHANGED(2),
    //移除
    REMOVED(3),
    //未变更
    UNTOUCHED(0),
    //循环引用
    CIRCULAR(-1),
    //忽略
    IGNORED(-2);

    private final int code;

    StateEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
