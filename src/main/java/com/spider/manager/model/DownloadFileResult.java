package com.spider.manager.model;

/**
 * Created by wsy on 2015/9/24.
 */
public class DownloadFileResult {

    /**
     * 网页端会根据这个名称请求相应文件，进行下载
     */
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
