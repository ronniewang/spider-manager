package com.spider.global;

/**
 * 
 * @author wsy
 *
 */
public enum AbsenceState {

    Yes("1"), No("0");

    AbsenceState(String id) {

        this.id = id;
    }

    private String id;

    public String value() {

        return this.id;
    }
}
