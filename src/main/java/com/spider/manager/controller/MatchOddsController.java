package com.spider.manager.controller;

import com.alibaba.fastjson.JSON;
import com.spider.config.SearchDateAspect;
import com.spider.db.entity.OddsModel;
import com.spider.manager.model.*;
import com.spider.manager.service.MatchOddsServcie;
import com.spider.utils.DateUtils;
import com.spider.utils.ExcelUtils;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 此Controller对应赔率信息页面
 *
 * @author ronnie
 */
@Controller
public class MatchOddsController {

    private static final String SHEET_NAME = "First Sheet";

    @Value("${download.xls.path}")
    private String downloadXlsPath;

    @Autowired
    private MatchOddsServcie matchOddsService;

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    /**
     * startDate和endDate经过aop处理了，{@link SearchDateAspect}
     *
     * @param startDate
     * @param endDate
     * @return
     * @see SearchDateAspect
     */
    @RequestMapping(value = "/listOdds", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<OddsModel> listOdds(@RequestParam Date startDate, @RequestParam Date endDate) {

        return matchOddsService.listOdds(startDate, endDate);
    }

    /**
     * 刷新某个赔率
     *
     * @param matchCode
     * @param absenceState
     * @return
     */
    @RequestMapping(value = "/refreshOdds", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OddsModel refreshOdds(@RequestParam String matchCode, @RequestParam String absenceState) {

        OddsModel refreshOdds = matchOddsService.refreshOdds(matchCode);
        refreshOdds.setAbsenceState(absenceState);
        return refreshOdds;
    }

    /**
     * 计算sup和ttg
     *
     * @param oddsModel
     * @return
     */
    @RequestMapping(value = "/calcSupAndTtg", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Map<String, SupAndTtgModel> calcSupAndTtg(@RequestParam String oddsModel) {

        OddsModel model = JSON.parseObject(oddsModel, OddsModel.class);
        return matchOddsService.calcSupAndTtg(model);
    }

    /**
     * 导出赔率excel
     *
     * @param oddsModels
     * @return
     */
    @RequestMapping(value = "/matchOddsExcel", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    @ResponseBody
    public DownloadFileResult excel(@RequestParam String oddsModels) {

        List<OddsModel> oddsList = JSON.parseArray(oddsModels, OddsModel.class);

        String fileName = "odds" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(downloadXlsPath + fileName));
            WritableSheet sheet = workbook.createSheet(SHEET_NAME, 0);

            for (int i = 0; i < oddsList.size(); i++) {
                int index = 0;
                sheet.addCell(new Label(index++, i, oddsList.get(i).getMatchDate()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getMatchCode()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getMatchLeague()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getHomeTeam()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getAwayTeam()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getHandicapLine()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getSportteryHadH()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getSportteryHadD()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getSportteryHadA()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getSportteryHhadH()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getSportteryHhadD()));
                sheet.addCell(new Label(index++, i, oddsList.get(i).getSportteryHhadA()));
                SportteryAllModel sportteryAllModel = oddsList.get(i).getSportteryAllModel();
                if (sportteryAllModel != null) {
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuHh()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuHd()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuHa()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuDh()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuDd()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuDa()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuAh()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuAd()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getHafuAa()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg0()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg1()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg2()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg3()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg4()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg5()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg6()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getTtg7Up()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore10()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore20()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore21()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore30()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore31()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore32()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore40()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore41()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore42()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore50()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore51()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore52()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScoreHElse()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore00()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore11()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore22()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore33()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScoreDElse()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore01()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore02()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore12()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore03()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore13()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore23()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore04()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore14()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore24()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore05()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore15()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScore25()));
                    sheet.addCell(new Label(index++, i, sportteryAllModel.getScoreAElse()));
                }
            }
            workbook.write();
        } catch (Exception e) {

        } finally {
            ExcelUtils.close(workbook);
        }
        return new DownloadFileResult(fileName);
    }
}
