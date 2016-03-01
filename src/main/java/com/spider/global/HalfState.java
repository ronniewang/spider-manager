package com.spider.global;

/**
 * 半场状态
 * 
 * @author wsy
 *
 */
public enum HalfState {

    Stop(0), FirstHalf(1), MiddleHalf(2), SecondHalf(3);

    private int value;

    public int value() {

        return value;
    }

    private HalfState(int value) {

        this.value = value;
    }
}
