package com.spider.model;

/**
 * Created by wsy on 2015/9/24.
 */
public class DownloadFileResult {

    private String fileName;

    public DownloadFileResult(String fileName) {

        this.fileName = fileName;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {

        this.fileName = fileName;
    }
}
