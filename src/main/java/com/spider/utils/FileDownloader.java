package com.spider.utils;

import com.spider.exception.DownloadException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件下载工具类
 *
 * @author ronnie
 */
@Component
public class FileDownloader {

    @Value("${download.xls.path}")
    private String downloadXlsPath;

    /**
     * @param response 响应对象，用于获取输出流
     * @param fileName 文件名
     * @throws DownloadException 文件不存在或者io异常时抛出此异常
     */
    public void download(HttpServletResponse response, String fileName) throws DownloadException {

        String downLoadPath = downloadXlsPath + fileName;
        long fileLength = new File(downLoadPath).length();
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
        }
        response.setHeader("Content-Length", String.valueOf(fileLength));

        FileInputStream fis = null;
        ServletOutputStream os = null;
        try {
            fis = new FileInputStream(downLoadPath);
            os = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fis), os);
        } catch (FileNotFoundException e) {
            throw new DownloadException("文件未找到");
        } catch (IOException e) {
            throw new DownloadException("io异常");
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(os);
        }
    }

}
