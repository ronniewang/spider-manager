package com.spider.manager.controller;

import com.alibaba.fastjson.JSON;
import com.spider.config.SearchDateAspect;
import com.spider.manager.model.*;
import com.spider.manager.service.MatchService;
import com.spider.utils.DateUtils;
import com.spider.utils.ExcelUtils;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * 此Controller对应赛事列表页面
 *
 * @author ronnie
 */
@Controller
public class MatchController {

    private static final String SHEET_NAME = "First Sheet";

    @Value("${download.xls.path}")
    private String downloadXlsPath;

    @Autowired
    private MatchService matchService;

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    /**
     * 列出日期区间所有比赛
     *
     * @param startDate 11点起
     * @param endDate   11点结束
     * @return
     * @see SearchDateAspect
     */
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

    /**
     * 生成需要导出的比赛excel
     *
     * @param matchModels
     * @return
     */
    @RequestMapping(value = "/matchExcel", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DownloadFileResult excel(@RequestParam String matchModels) {

        List<MatchModel> matchList = JSON.parseArray(matchModels, MatchModel.class);
        String fileName = "match" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(downloadXlsPath + fileName));
            WritableSheet sheet = workbook.createSheet(SHEET_NAME, 0);
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
}
