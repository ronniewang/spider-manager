package com.spider.poi.metadata;

/**
 * Created by ronnie on 2016/4/26.
 * <p>
 * playersheet表的元数据，主要根据这些数据确定相关数据的开始和结束范围，便于遍历操作
 * 而且与操作表格的代码进行解耦，如果数据表结构有所改变，修改元数据即可
 *
 * @author ronnie
 */
public interface PlayerSheetMetadata {

    /**
     * 当前表格的队伍信息信息所在单元格
     *
     * @return
     */
    CellIndex currentTeam();

    /**
     * 比赛时间列
     * @return
     */
    int matchDate();

    /**
     * 客队列
     * @return
     */
    int opponentTeam();

    /**
     * 主客队标志
     * @return
     */
    int homeAwayFlag();

    /**
     * 主队得分
     *
     * @return
     */
    int homeScore();

    int awayScore();

    /**
     * 球员信息所在行列范围
     *
     * @return
     */
    RangeColumnRowIndex playerIndex();

    /**
     * 详细数据所在的行列的范围
     *
     * @return
     */
    RangeRowIndex content();

    int shootingAttempt();

    int opponentShootingAttempt();

    int shootingOnTarget();

    int opponentShootingOnTarget();

    int possession();

    int opponentPossession();

    int league();
}
