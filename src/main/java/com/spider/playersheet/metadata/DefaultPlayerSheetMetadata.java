package com.spider.playersheet.metadata;

/**
 * Created by ronnie on 2016/4/26.
 * <p>
 * 默认的playersheet表格元数据
 *
 * @author ronnie
 */
public class DefaultPlayerSheetMetadata implements PlayerSheetMetadata {

    private CellIndex homeTeam = new CellIndex(20, 7);

    private RangeColumnRowIndex playerRangeColumnRow = new RangeColumnRowIndex(18, 20, 31, 70);

    private RangeRowIndex contentRangeRow = new RangeRowIndex(22, 59);

    @Override
    public CellIndex currentTeam() {

        return homeTeam;
    }

    @Override
    public int matchDate() {

        return 0;
    }

    @Override
    public int opponentTeam() {

        return 7;
    }

    @Override
    public int homeAwayFlag() {

        return 8;
    }

    @Override
    public int homeScore() {

        return 10;
    }

    @Override
    public int awayScore() {

        return 11;
    }

    @Override
    public RangeColumnRowIndex playerIndex() {

        return playerRangeColumnRow;
    }

    @Override
    public RangeRowIndex content() {

        return contentRangeRow;
    }

    @Override
    public int shootingAttempt() {

        return 22;
    }

    @Override
    public int opponentShootingAttempt() {

        return 23;
    }

    @Override
    public int shootingOnTarget() {

        return 24;
    }

    @Override
    public int opponentShootingOnTarget() {

        return 25;
    }

    @Override
    public int possession() {

        return 26;
    }

    @Override
    public int opponentPossession() {

        return 27;
    }

    @Override
    public int league() {

        return 9;
    }

    public void setHomeTeam(CellIndex homeTeam) {

        this.homeTeam = homeTeam;
    }

    public void setPlayerRangeColumnRow(RangeColumnRowIndex playerRangeColumnRow) {

        this.playerRangeColumnRow = playerRangeColumnRow;
    }

    public void setContentRangeRow(RangeRowIndex contentRangeRow) {

        this.contentRangeRow = contentRangeRow;
    }
}
