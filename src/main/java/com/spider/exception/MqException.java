package com.spider.exception;

/**
 * @author wsy
 *
 */
public class MqException extends Exception {

    private static final long serialVersionUID = 3306555413225455519L;

    public MqException() {

    }

    public MqException(String arg0) {

        super(arg0);
    }

    public MqException(Throwable arg0) {

        super(arg0);
    }

    public MqException(String arg0, Throwable arg1) {

        super(arg0, arg1);
    }

}
