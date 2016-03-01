package com.spider.global;

/**
 * 向sbc推送的数据类型
 *
 * @author wsy
 */
public enum UpdateSBCType {

    HiloOdds(1),
    HdcOdds(2),
    ScoreAndHalf(3);

    private int type;

    UpdateSBCType(int type) {

        this.type = type;
    }

    public int intValue() {

        return this.type;
    }
}
