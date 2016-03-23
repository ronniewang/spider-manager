package com.spider.manager.controller;

import com.alibaba.fastjson.JSON;
import com.spider.global.Constants;
import com.spider.manager.model.DownloadFileResult;
import com.spider.manager.model.ProductModel;
import com.spider.manager.service.MatchProductService;
import com.spider.utils.DateUtils;
import com.spider.utils.ExcelUtils;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class MatchProductController {

    @Autowired
    private MatchProductService matchProductService;

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    @RequestMapping(value = "/listProduct", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductModel> listProduct(@RequestParam Date startDate, @RequestParam Date endDate) {

        return matchProductService.listMatchProduct(startDate, endDate);
    }

    @RequestMapping(value = "/listProductPage")
    public String listProductPage() {

        return "listProduct";
    }

    @RequestMapping(value = "/matchProductExcel", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public DownloadFileResult excel(@RequestParam String productModels) {

        List<ProductModel> productList = JSON.parseArray(productModels, ProductModel.class);
        String fileName = "product" + DateUtils.getNowStr("yyyyMMdd_HHmmss") + ".xls";
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(Constants.DOWNLOAD_XLS_PATH + fileName));
            WritableSheet sheet = workbook.createSheet(Constants.SHEET_NAME, 0);

            for (int i = 0; i < productList.size(); i++) {
                sheet.addCell(new Label(0, i, productList.get(i).getMatchDate()));
                sheet.addCell(new Label(1, i, productList.get(i).getMatchCode()));
                sheet.addCell(new Label(2, i, productList.get(i).getMatchLeague()));
                sheet.addCell(new Label(3, i, productList.get(i).getHomeTeam()));
                sheet.addCell(new Label(4, i, productList.get(i).getAwayTeam()));
                sheet.addCell(new Label(5, i, productList.get(i).getHandicapLine()));
                sheet.addCell(new Label(6, i, productList.get(i).getHAD()));
                sheet.addCell(new Label(7, i, productList.get(i).getHHAD()));
                sheet.addCell(new Label(8, i, productList.get(i).getHAFU()));
                sheet.addCell(new Label(9, i, productList.get(i).getCRS()));
                sheet.addCell(new Label(10, i, productList.get(i).getTTG()));
            }

            workbook.write();
        } catch (Exception e) {

        } finally {
            ExcelUtils.close(workbook);
        }
        return new DownloadFileResult(fileName);
    }

}
