package com.spider.global;

/**
 * 全局的常量
 *
 * @author wsy
 */
public final class Constants {

    public static final String DOWNLOAD_XLS_PATH = ConfigProperties.$("download.xls.path");

    public static final String SHEET_NAME = "First Sheet";

    public static final Integer HTTP_UTIL_REQUEST_CONNECTION_TIMEOUT = Integer.valueOf(ConfigProperties.$("com.caiex.httputil.request.connection.timeout"));

    public static final Integer HTTP_UTIL_CONNECTION_MANAGER_MAX_TOTAL = Integer.valueOf(ConfigProperties.$("com.caiex.httputil.connectionmanager.max.total"));

    public static final Integer HTTP_UTIL_CONNECTION_MANAGER_MAX_PER_ROUTE = Integer.valueOf(ConfigProperties
            .$("com.caiex.httputil.connectionmanager.max.perroute"));

    public static final Integer HTTP_UTIL_CONNECTION_TIMEOUT = Integer.valueOf(ConfigProperties.$("com.caiex.httputil.connection.timeout"));

    public static final Integer HTTP_UTIL_SOCKET_TIMEOUT = Integer.valueOf(ConfigProperties.$("com.caiex.httputil.socket.timeout"));

    public static final String UTF_8 = "utf-8";

    public static final String STATUS_SELLING = "SELLING";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ gaming company ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static final String LIJI_NAME = GamingCompany.LiJi.getName();

    public static final String JINBAOBO_NAME = GamingCompany.JinBaoBo.getName();

    private Constants() {

    }
}
