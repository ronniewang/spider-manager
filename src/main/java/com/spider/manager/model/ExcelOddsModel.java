package com.spider.manager.model;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import java.lang.reflect.Field;

/**
 * Created by wsy on 2016/2/15.
 * 字段顺序很重要，不要轻易更改，导出的excel字段按照此顺序排列
 *
 * @author wsy
 */
public class ExcelOddsModel {

    private String date = "";

    private String homeTeam = "";

    private String score = "";

    private String halfScore = "";

    private String awayTeam = "";

    private String hadBookmaker = "";

    private String hadHStart = "";

    private String hadHClose = "";

    private String hadDStart = "";

    private String hadDClose = "";

    private String hadAStart = "";

    private String hadAClose = "";

    private String hadTimeStart = "";

    private String hadTimeClose = "";

    private String hdcBookmaker = "";

    private String hdcHCStart = "";

    private String hdcHCClose = "";

    private String hdcHomeStart = "";

    private String hdcHomeClose = "";

    private String hdcAwayStart = "";

    private String hdcAwayClose = "";

    private String hdcTimeStart = "";

    private String hdcTimeClose = "";

    private String hiloBookmaker = "";

    private String hiloKeyStart = "";

    private String hiloKeyClose = "";

    private String hiloOverStart = "";

    private String hiloOverClose = "";

    private String hiloUnderStart = "";

    private String hiloUnderClose = "";

    private String hiloTimeStart = "";

    private String hiloTimeClose = "";

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = homeTeam;
    }

    public String getScore() {

        return score;
    }

    public void setScore(String score) {

        this.score = score;
    }

    public String getHalfScore() {

        return halfScore;
    }

    public void setHalfScore(String halfScore) {

        this.halfScore = halfScore;
    }

    public String getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {

        this.awayTeam = awayTeam;
    }

    public String getHadBookmaker() {

        return hadBookmaker;
    }

    public void setHadBookmaker(String hadBookmaker) {

        this.hadBookmaker = hadBookmaker;
    }

    public String getHadHStart() {

        return hadHStart;
    }

    public void setHadHStart(String hadHStart) {

        this.hadHStart = hadHStart;
    }

    public String getHadHClose() {

        return hadHClose;
    }

    public void setHadHClose(String hadHClose) {

        this.hadHClose = hadHClose;
    }

    public String getHadDStart() {

        return hadDStart;
    }

    public void setHadDStart(String hadDStart) {

        this.hadDStart = hadDStart;
    }

    public String getHadDClose() {

        return hadDClose;
    }

    public void setHadDClose(String hadDClose) {

        this.hadDClose = hadDClose;
    }

    public String getHadAStart() {

        return hadAStart;
    }

    public void setHadAStart(String hadAStart) {

        this.hadAStart = hadAStart;
    }

    public String getHadAClose() {

        return hadAClose;
    }

    public void setHadAClose(String hadAClose) {

        this.hadAClose = hadAClose;
    }

    public String getHadTimeStart() {

        return hadTimeStart;
    }

    public void setHadTimeStart(String hadTimeStart) {

        this.hadTimeStart = hadTimeStart;
    }

    public String getHadTimeClose() {

        return hadTimeClose;
    }

    public void setHadTimeClose(String hadTimeClose) {

        this.hadTimeClose = hadTimeClose;
    }

    public String getHdcBookmaker() {

        return hdcBookmaker;
    }

    public void setHdcBookmaker(String hdcBookmaker) {

        this.hdcBookmaker = hdcBookmaker;
    }

    public String getHdcHCStart() {

        return hdcHCStart;
    }

    public void setHdcHCStart(String hdcHCStart) {

        this.hdcHCStart = hdcHCStart;
    }

    public String getHdcHCClose() {

        return hdcHCClose;
    }

    public void setHdcHCClose(String hdcHCClose) {

        this.hdcHCClose = hdcHCClose;
    }

    public String getHdcHomeStart() {

        return hdcHomeStart;
    }

    public void setHdcHomeStart(String hdcHomeStart) {

        this.hdcHomeStart = hdcHomeStart;
    }

    public String getHdcHomeClose() {

        return hdcHomeClose;
    }

    public void setHdcHomeClose(String hdcHomeClose) {

        this.hdcHomeClose = hdcHomeClose;
    }

    public String getHdcAwayStart() {

        return hdcAwayStart;
    }

    public void setHdcAwayStart(String hdcAwayStart) {

        this.hdcAwayStart = hdcAwayStart;
    }

    public String getHdcAwayClose() {

        return hdcAwayClose;
    }

    public void setHdcAwayClose(String hdcAwayClose) {

        this.hdcAwayClose = hdcAwayClose;
    }

    public String getHdcTimeStart() {

        return hdcTimeStart;
    }

    public void setHdcTimeStart(String hdcTimeStart) {

        this.hdcTimeStart = hdcTimeStart;
    }

    public String getHdcTimeClose() {

        return hdcTimeClose;
    }

    public void setHdcTimeClose(String hdcTimeClose) {

        this.hdcTimeClose = hdcTimeClose;
    }

    public String getHiloBookmaker() {

        return hiloBookmaker;
    }

    public void setHiloBookmaker(String hiloBookmaker) {

        this.hiloBookmaker = hiloBookmaker;
    }

    public String getHiloKeyStart() {

        return hiloKeyStart;
    }

    public void setHiloKeyStart(String hiloKeyStart) {

        this.hiloKeyStart = hiloKeyStart;
    }

    public String getHiloKeyClose() {

        return hiloKeyClose;
    }

    public void setHiloKeyClose(String hiloKeyClose) {

        this.hiloKeyClose = hiloKeyClose;
    }

    public String getHiloOverStart() {

        return hiloOverStart;
    }

    public void setHiloOverStart(String hiloOverStart) {

        this.hiloOverStart = hiloOverStart;
    }

    public String getHiloOverClose() {

        return hiloOverClose;
    }

    public void setHiloOverClose(String hiloOverClose) {

        this.hiloOverClose = hiloOverClose;
    }

    public String getHiloUnderStart() {

        return hiloUnderStart;
    }

    public void setHiloUnderStart(String hiloUnderStart) {

        this.hiloUnderStart = hiloUnderStart;
    }

    public String getHiloUnderClose() {

        return hiloUnderClose;
    }

    public void setHiloUnderClose(String hiloUnderClose) {

        this.hiloUnderClose = hiloUnderClose;
    }

    public String getHiloTimeStart() {

        return hiloTimeStart;
    }

    public void setHiloTimeStart(String hiloTimeStart) {

        this.hiloTimeStart = hiloTimeStart;
    }

    public String getHiloTimeClose() {

        return hiloTimeClose;
    }

    public void setHiloTimeClose(String hiloTimeClose) {

        this.hiloTimeClose = hiloTimeClose;
    }

    public void addRowToExcel(WritableSheet writableSheet, int row) {

        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                writableSheet.addCell(new Label(i, row, (String) fields[i].get(this)));
            } catch (WriteException e) {
                throw new IllegalStateException("无法写入excel", e);
            } catch (IllegalAccessException e) {
                //ignore
            }
        }
    }
}
