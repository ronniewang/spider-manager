package com.spider.exception;

/**
 * @author wsy
 *
 */
public class DownloadException extends Exception {

    private static final long serialVersionUID = 3306555413225455519L;

    public DownloadException() {

    }

    public DownloadException(String arg0) {

        super(arg0);
    }

    public DownloadException(Throwable arg0) {

        super(arg0);
    }

    public DownloadException(String arg0, Throwable arg1) {

        super(arg0, arg1);
    }

}
