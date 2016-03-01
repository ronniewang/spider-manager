package com.spider.controller;

import com.alibaba.fastjson.JSON;
import com.spider.global.Constants;
import com.spider.model.*;
import com.spider.service.MatchService;
import com.spider.utils.DateUtils;
import com.spider.utils.ExcelUtils;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 导出赛程的Controller
 *
 * @author wsy
 */
@Controller
public class MatchController {

    public static final int MAX_PLAYER_NUMBER = 23;

    @Autowired
    private MatchService matchService;


    private CellView cellView = new CellView();

    {
        cellView.setAutosize(true);
    }

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    @RequestMapping(value = "/listMatch", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<MatchModel> listMatch(@RequestParam Date startDate, @RequestParam Date endDate) {

        return matchService.listMatch(startDate, endDate);
    }

    @RequestMapping(value = "/listMatchPage")
    public String listMatchPage() {

        return "listMatch";
    }

    @RequestMapping(value = "/exportMatchInfoPage")
    public String exportMatchInfoPage() {

        return "exportMatchInfo";
    }

    @RequestMapping(value = "/matchExcel", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DownloadFileResult excel(@RequestParam String matchModels) {

        List<MatchModel> matchList = JSON.parseArray(matchModels, MatchModel.class);
        String fileName = "match" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(Constants.DOWNLOAD_XLS_PATH + fileName));
            WritableSheet sheet = workbook.createSheet(Constants.SHEET_NAME, 0);
            int i = 0;
            sheet.addCell(new Label(0, i, matchList.get(i).getMatchDate()));
            sheet.addCell(new Label(1, i, matchList.get(i).getMatchCode()));
            sheet.addCell(new Label(2, i, matchList.get(i).getMatchLeague()));
            sheet.addCell(new Label(3, i, matchList.get(i).getHomeTeam()));
            sheet.addCell(new Label(4, i, matchList.get(i).getAwayTeam()));
            matchList.remove(0);
            Collections.sort(matchList);
            for (int j = 0; j < matchList.size(); j++) {
                int row = j + 1;
                sheet.addCell(new Label(0, row, matchList.get(j).getMatchDate()));
                sheet.addCell(new Label(1, row, matchList.get(j).getMatchCode()));
                sheet.addCell(new Label(2, row, matchList.get(j).getMatchLeague()));
                sheet.addCell(new Label(3, row, matchList.get(j).getHomeTeam()));
                sheet.addCell(new Label(4, row, matchList.get(j).getAwayTeam()));
            }
            workbook.write();
        } catch (Exception e) {

        } finally {
            ExcelUtils.close(workbook);
        }
        return new DownloadFileResult(fileName);
    }


    @RequestMapping(value = "/listMatchByLeague", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DownloadFileResult listMatchByLeague(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String league) {


        List<MatchPlayerInfoModel> matchList = matchService.listMatchByLeague(startDate, endDate, league);
        String fileName = "league" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(Constants.DOWNLOAD_XLS_PATH + fileName));
            WritableSheet playerSheet = workbook.createSheet(Constants.SHEET_NAME, 0);
            generatePlayerSheetHeader(playerSheet);
            setPlayerSheetData(matchList, playerSheet);

            WritableSheet sheet2 = workbook.createSheet("sheet2", 1);
            int i2 = 0;
            sheet2.addCell(new Label(0, i2, "日期"));
            sheet2.addCell(new Label(1, i2, "主队"));
            sheet2.addCell(new Label(2, i2, "客队"));
            sheet2.addCell(new Label(3, i2, "半场比分"));
            sheet2.addCell(new Label(4, i2, "比分"));
            for (int j = 0; j < matchList.size(); j++) {
                int row = j + 1;
                sheet2.addCell(new Label(0, row, matchList.get(j).getDate()));
                sheet2.addCell(new Label(1, row, matchList.get(j).getHomeTeam()));
                sheet2.addCell(new Label(2, row, matchList.get(j).getAwayTeam()));
                sheet2.addCell(new Label(3, row, matchList.get(j).getHalfScore()));
                sheet2.addCell(new Label(4, row, matchList.get(j).getScore()));
            }
            WritableSheet sheet3 = workbook.createSheet("sheet3", 2);
            int i3 = 0;
            sheet3.addCell(new Label(0, i3, "日期"));
            sheet3.addCell(new Label(1, i3, "主队"));
            sheet3.addCell(new Label(2, i3, "客队"));
            sheet3.addCell(new Label(3, i3, "半场比分"));
            sheet3.addCell(new Label(4, i3, "比分"));
            for (int j = 0; j < matchList.size(); j++) {
                int row = j + 1;
                sheet3.addCell(new Label(0, row, matchList.get(j).getDate()));
                sheet3.addCell(new Label(1, row, matchList.get(j).getHomeTeam()));
                sheet3.addCell(new Label(2, row, matchList.get(j).getAwayTeam()));
                sheet3.addCell(new Label(3, row, matchList.get(j).getHalfScore()));
                sheet3.addCell(new Label(4, row, matchList.get(j).getScore()));
            }
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ExcelUtils.close(workbook);
        }
        return new DownloadFileResult(fileName);
    }

    private void setPlayerSheetData(List<MatchPlayerInfoModel> matchList, WritableSheet playerSheet) throws WriteException {

        for (int i = 0; i < matchList.size(); i++) {
            int row = i + 1;
            playerSheet.addCell(new Label(0, row, matchList.get(i).getDate()));
            playerSheet.addCell(new Label(1, row, matchList.get(i).getHomeTeam()));
            playerSheet.addCell(new Label(2, row, matchList.get(i).getAwayTeam()));
            playerSheet.addCell(new Label(3, row, matchList.get(i).getHalfScore()));
            playerSheet.addCell(new Label(4, row, matchList.get(i).getScore()));
            MatchPlayerInfoModel model = matchList.get(i);
            int j = 5;
            for (String s : model.getHomeFirstPlayers()) {
                playerSheet.addCell(new Label(j, row, s));
                j++;
            }
            for (String s : model.getHomeSecondPlayers()) {
                playerSheet.addCell(new Label(j, row, s));
                j++;
            }

            j = 5 + MAX_PLAYER_NUMBER;
            for (String s : model.getAwayFirstPlayers()) {
                playerSheet.addCell(new Label(j, row, s));
                j++;
            }
            for (String s : model.getAwaySecondPlayers()) {
                playerSheet.addCell(new Label(j, row, s));
                j++;
            }
        }
    }

    private void generatePlayerSheetHeader(WritableSheet sheet0) throws WriteException {

        for (int i = 0; i < 50; i++) {
            sheet0.setColumnView(i, cellView);
        }
        int firstRow = 0;
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBackground(Colour.BLUE);
        cellFormat.setAlignment(Alignment.CENTRE);
        cellFormat.setFont(font);
        sheet0.addCell(new Label(0, firstRow, "Date", cellFormat));
        sheet0.addCell(new Label(1, firstRow, "Home Team", cellFormat));
        sheet0.addCell(new Label(2, firstRow, "Away Team", cellFormat));
        sheet0.addCell(new Label(3, firstRow, "Half Score", cellFormat));
        sheet0.addCell(new Label(4, firstRow, "Score", cellFormat));
        for (int i = 0; i < MAX_PLAYER_NUMBER; i++) {
            sheet0.addCell(new Label(i + 5, firstRow, "H Player" + (i + 1), cellFormat));
        }
        for (int i = 0; i < MAX_PLAYER_NUMBER; i++) {
            sheet0.addCell(new Label(i + 5 + MAX_PLAYER_NUMBER, firstRow, "A Player" + (i + 1), cellFormat));
        }
    }


    @RequestMapping(value = "/matchStatisticExcelByLeague", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DownloadFileResult excel(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String league) {

        String fileName = "statistic_" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(Constants.DOWNLOAD_XLS_PATH + fileName));
            WritableSheet sheet = workbook.createSheet(Constants.SHEET_NAME, 0);

            buildHeader(sheet);
            List<ExcelMatchStatisticModel> models = matchService.getExcelStatisticModels(startDate, endDate, league);
            for (int i = 0; i < models.size(); i++) {
                models.get(i).addRowToExcel(sheet, i + 2);
            }
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ExcelUtils.close(workbook);
        }
        return new DownloadFileResult(fileName);
    }

    private void buildHeader(WritableSheet sheet) throws WriteException {

        sheet.addCell(new Label(0, 0, "League"));
        sheet.mergeCells(0, 0, 4, 0);
        sheet.addCell(new Label(5, 0, "Corner"));
        sheet.mergeCells(5, 0, 6, 0);
        sheet.addCell(new Label(7, 0, "Yellow Cards"));
        sheet.mergeCells(7, 0, 8, 0);
        sheet.addCell(new Label(9, 0, "Shots"));
        sheet.mergeCells(9, 0, 10, 0);
        sheet.addCell(new Label(11, 0, "Shots On Goal"));
        sheet.mergeCells(11, 0, 12, 0);
        sheet.addCell(new Label(13, 0, "Possession"));
        sheet.mergeCells(13, 0, 14, 0);
        sheet.addCell(new Label(15, 0, "Pass"));
        sheet.mergeCells(15, 0, 16, 0);
        sheet.addCell(new Label(17, 0, "Pass Success"));
        sheet.mergeCells(17, 0, 18, 0);
        sheet.addCell(new Label(19, 0, "Saves"));
        sheet.mergeCells(19, 0, 20, 0);
        sheet.addCell(new Label(21, 0, "Tackles"));
        sheet.mergeCells(21, 0, 22, 0);
        sheet.addCell(new Label(23, 0, "Dribbles"));
        sheet.mergeCells(23, 0, 24, 0);

        String[] headers = {
                "Date", "Home Team", "Score", "Half Score", "Away Team",
                "Home", "Away", "Home", "Away", "Home", "Away", "Home", "Away", "Home", "Away",
                "Home", "Away", "Home", "Away", "Home", "Away", "Home", "Away", "Home", "Away"
        };
        for (int i = 0; i < headers.length; i++) {

            sheet.setColumnView(i, cellView);
            sheet.addCell(new Label(i, 1, headers[i]));
        }
    }
}
