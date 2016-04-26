package com.spider.poi.metadata;

/**
 * Created by ronnie on 2016/4/26.
 *
 * @author ronnie
 */
public class RangeRowIndex {

    private int startRow;

    private int endRow;

    public RangeRowIndex(int startRow, int endRow) {

        this.startRow = startRow;
        this.endRow = endRow;
    }

    public int getStartRow() {

        return startRow;
    }

    public int getEndRow() {

        return endRow;
    }

}
