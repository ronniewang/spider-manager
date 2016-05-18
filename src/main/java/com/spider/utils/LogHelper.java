/**
 * @Description
 * @auth guowang
 * @time 2014-7-23
 */
package com.spider.utils;

import org.apache.log4j.Logger;

import java.text.MessageFormat;

/**
 * @author guowang
 */
public class LogHelper {

    public static <T> void infoLog(Logger logger, Exception e, String info, Object... params) {

        if (params != null && params.length > 0) {
            info = MessageFormat.format(info, params);
        }
        logger.info(info);
    }

    public static <T> void errorLog(Logger logger, Exception e, String info, Object... params) {

        if (params != null && params.length > 0) {
            info = MessageFormat.format(info, params);
        }
        if (e != null) {
            logger.error(info, e);
        } else {
            logger.error(info);
        }
    }

    public static <T> void errorLog(Logger logger, Throwable e, String info, Object... params) {

        if (params != null && params.length > 0) {
            info = MessageFormat.format(info, params);
        }
        if (e != null) {
            logger.error(info, e);
        } else {
            logger.error(info);
        }
    }

    public static Logger getErrorLogger() {

        return Logger.getLogger("error_logger");
    }

    public static Logger getInfoLogger() {

        return Logger.getLogger("info_logger");
    }

}
