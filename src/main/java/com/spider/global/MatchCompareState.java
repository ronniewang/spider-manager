package com.spider.global;

/**
 * 赛事在两边状态，可能只有彩客有，只有官网有，或者都有
 *
 * @author wsy
 */
public enum MatchCompareState {

    SportteryOnly("1", "仅官网"), Win310Only("2", "仅彩客"), Both("3", "都有");

    private final String desc;

    MatchCompareState(String id, String desc) {

        this.id = id;
        this.desc = desc;
    }

    private String id;

    public String value() {

        return id;
    }

    public String desc() {

        return desc;
    }

}
