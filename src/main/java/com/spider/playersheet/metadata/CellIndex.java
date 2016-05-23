package com.spider.playersheet.metadata;

/**
 * Created by ronnie on 2016/4/26.
 *
 * @author ronnie
 */
public class CellIndex {

    private int row;

    private int column;

    public CellIndex(int row, int column) {

        this.row = row;
        this.column = column;
    }

    /**
     * 单元格所在行，zero based
     *
     * @return 单元格所在行
     */
    public int getRow() {

        return row;
    }

    /**
     * 单元格所在列，zero based
     *
     * @return 单元格所在列
     */
    public int getColumn() {

        return column;
    }
}
