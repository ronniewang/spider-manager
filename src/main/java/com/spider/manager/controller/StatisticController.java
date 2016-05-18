package com.spider.manager.controller;

import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.manager.model.DownloadFileResult;
import com.spider.manager.model.ExcelMatchStatisticModel;
import com.spider.manager.model.ExcelOddsModel;
import com.spider.manager.model.MatchPlayerInfoModel;
import com.spider.manager.service.MatchOddsServcie;
import com.spider.manager.service.MatchService;
import com.spider.utils.DateUtils;
import com.spider.utils.ExcelUtils;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.*;
import jxl.write.*;
import jxl.write.Alignment;
import jxl.write.Colour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 此Controller对应导出比赛信息页
 *
 * @author ronnie
 */
@Controller
public class StatisticController {

    private static final String SHEET_NAME = "First Sheet";

    public static final int MAX_PLAYER_NUMBER = 23;

    @Value("${download.xls.path}")
    private String downloadXlsPath;

    @Autowired
    private TCrawlerWin310Repository win310Repository;

    @Autowired
    private MatchOddsServcie matchOddsService;

    @Autowired
    private MatchService matchService;

    private CellView cellView = new CellView();

    {
        cellView.setAutosize(true);
    }

    /**
     * 列出彩客网所有联赛
     *
     * @return
     */
    @RequestMapping(value = "/list310Leagues", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<String> list310Leagues() {

        return win310Repository.findMatchsDistinct();
    }

    @RequestMapping(value = "/matchOddsExcelByLeague", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DownloadFileResult excel(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String league) {

        String fileName = "odds" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(downloadXlsPath + fileName));
            WritableSheet sheet = workbook.createSheet(SHEET_NAME, 0);

            buildHeader(sheet);
            List<ExcelOddsModel> models = matchOddsService.getExcelOddsModels(startDate, endDate, league);
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

    /**
     * genuine联赛和日期区间获取比赛信息
     *
     * @param startDate
     * @param endDate
     * @param league
     * @return
     */
    @RequestMapping(value = "/listMatchByLeague", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DownloadFileResult listMatchByLeague(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String league) {

        List<MatchPlayerInfoModel> matchList = matchService.listMatchByLeague(startDate, endDate, league);
        String fileName = "league" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(downloadXlsPath + fileName));
            WritableSheet playerSheet = workbook.createSheet(SHEET_NAME, 0);
            generatePlayerSheetHeader(playerSheet);
            setPlayerSheetData(matchList, playerSheet);

            WritableSheet sheet2 = workbook.createSheet("sheet2", 1);
            fillMatchInfoToSheet(matchList, sheet2);
            WritableSheet sheet3 = workbook.createSheet("sheet3", 2);
            fillMatchInfoToSheet(matchList, sheet3);
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ExcelUtils.close(workbook);
        }
        return new DownloadFileResult(fileName);
    }

    private void fillMatchInfoToSheet(List<MatchPlayerInfoModel> matchList, WritableSheet sheet) throws WriteException {

        int i = 0;
        sheet.addCell(new Label(0, i, "日期"));
        sheet.addCell(new Label(1, i, "主队"));
        sheet.addCell(new Label(2, i, "客队"));
        sheet.addCell(new Label(3, i, "半场比分"));
        sheet.addCell(new Label(4, i, "比分"));

        for (int j = 0; j < matchList.size(); j++) {
            int row = j + 1;
            sheet.addCell(new Label(0, row, matchList.get(j).getDate()));
            sheet.addCell(new Label(1, row, matchList.get(j).getHomeTeam()));
            sheet.addCell(new Label(2, row, matchList.get(j).getAwayTeam()));
            sheet.addCell(new Label(3, row, matchList.get(j).getHalfScore()));
            sheet.addCell(new Label(4, row, matchList.get(j).getScore()));
        }
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
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.WHITE);
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBackground(jxl.format.Colour.BLUE);
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

    private void buildHeader(WritableSheet sheet) throws WriteException {

        //表头第一行有的单元格需要合并
        sheet.addCell(new Label(0, 0, "League"));
        sheet.mergeCells(0, 0, 4, 0);
        sheet.addCell(new Label(5, 0, "HAD"));
        sheet.addCell(new Label(6, 0, "Home"));
        sheet.mergeCells(6, 0, 7, 0);
        sheet.addCell(new Label(8, 0, "Draw"));
        sheet.mergeCells(8, 0, 9, 0);
        sheet.addCell(new Label(10, 0, "Away"));
        sheet.mergeCells(10, 0, 11, 0);
        sheet.addCell(new Label(12, 0, "Time"));
        sheet.mergeCells(12, 0, 13, 0);
        sheet.addCell(new Label(14, 0, "HDC"));
        sheet.addCell(new Label(15, 0, "HC"));
        sheet.mergeCells(15, 0, 16, 0);
        sheet.addCell(new Label(17, 0, "Home"));
        sheet.mergeCells(17, 0, 18, 0);
        sheet.addCell(new Label(19, 0, "Away"));
        sheet.mergeCells(19, 0, 20, 0);
        sheet.addCell(new Label(21, 0, "Time"));
        sheet.mergeCells(21, 0, 22, 0);
        sheet.addCell(new Label(23, 0, "HILO"));
        sheet.addCell(new Label(24, 0, "Key"));
        sheet.mergeCells(24, 0, 25, 0);
        sheet.addCell(new Label(26, 0, "Home"));
        sheet.mergeCells(26, 0, 27, 0);
        sheet.addCell(new Label(28, 0, "Away"));
        sheet.mergeCells(28, 0, 29, 0);
        sheet.addCell(new Label(30, 0, "Time"));
        sheet.mergeCells(30, 0, 31, 0);

        //表头第二行
        String[] headers = {
                "Date", "Home Team", "Score", "Half Score", "Away Team",
                "Bookmaker", "Start", "Close", "Start", "Close", "Start", "Close", "Start", "Close",
                "Bookmaker", "Start", "Close", "Start", "Close", "Start", "Close", "Start", "Close",
                "Bookmaker", "Start", "Close", "Start", "Close", "Start", "Close", "Start", "Close"
        };
        for (int i = 0; i < headers.length; i++) {
            sheet.addCell(new Label(i, 1, headers[i]));
        }
    }
}
