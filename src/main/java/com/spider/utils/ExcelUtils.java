package com.spider.utils;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.IOException;

public class ExcelUtils {

    public static void close(WritableWorkbook workbook) {

        if (workbook != null) {
            try {
                workbook.close();
            } catch (WriteException e) {
            } catch (IOException e) {
            }
        }
    }
}
