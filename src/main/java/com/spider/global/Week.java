package com.spider.global;

/**
 * 星期枚举
 * 
 * @author wsy
 *
 */
public enum Week {
    Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7);

    Week(int value) {

        this.value = value;
    }

    private int value;

    public int intValue() {

        return value;
    }
}
