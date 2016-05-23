package com.spider.playersheet.metadata;

/**
 * Created by ronnie on 2016/4/26.
 *
 * @author ronnie
 */
public class RangeColumnRowIndex {

    private int startRow;

    private int endRow;

    private int startColumn;

    private int endColumn;

    public RangeColumnRowIndex(int startRow, int endRow, int startColumn, int endColumn) {

        this.startRow = startRow;
        this.endRow = endRow;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }

    public int getStartRow() {

        return startRow;
    }

    public int getEndRow() {

        return endRow;
    }

    public int getStartColumn() {

        return startColumn;
    }

    public int getEndColumn() {

        return endColumn;
    }
}
