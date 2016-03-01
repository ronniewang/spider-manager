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

    ;

    public static final Integer HTTP_UTIL_CONNECTION_MANAGER_MAX_TOTAL = Integer.valueOf(ConfigProperties.$("com.caiex.httputil.connectionmanager.max.total"));

    public static final Integer HTTP_UTIL_CONNECTION_MANAGER_MAX_PER_ROUTE = Integer.valueOf(ConfigProperties
            .$("com.caiex.httputil.connectionmanager.max.perroute"));

    public static final Integer HTTP_UTIL_CONNECTION_TIMEOUT = Integer.valueOf(ConfigProperties.$("com.caiex.httputil.connection.timeout"));

    public static final Integer HTTP_UTIL_SOCKET_TIMEOUT = Integer.valueOf(ConfigProperties.$("com.caiex.httputil.socket.timeout"));

    public static final String ENDPOINT_SBC_MATCH_COMPARE = ConfigProperties.$("endpoint.sbc.match_compare");

    public static final String UTF_8 = "utf-8";

    public static final String STATUS_SELLING = "SELLING";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ crawler ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static final String NBSP = " ";

    public static final String NBSP_HTML = "\u00a0";

    public static final String WIN310_ODDS_TYPE_HHAD = "HHAD";

    public static final String WIN310_ODDS_TYPE_HAD = "HAD";

    public static final String WIN310_ODDS_TYPE_HIGH_LOW = "HIGH_LOW";

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ rocket mq ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static final String UPDATE_ODDS_GROUP = ConfigProperties.$("update.odds.group");

    public static final String UPDATE_ODDS_TOPIC = ConfigProperties.$("update.odds.topic");

    public static final String UPDATE_ODDS_HAD_TAG = ConfigProperties.$("update.odds.had.tag");

    public static final String UPDATE_ODDS_HHAD_TAG = ConfigProperties.$("update.odds.hhad.tag");

    public static final String UPDATE_SCORE_TAG = ConfigProperties.$("update.score.tag");

    public static final String ROCKET_MQ_ADDR = ConfigProperties.$("rocket.mq.addr");

    public static final String ROCKET_MQ_ADDR1 = ConfigProperties.$("rocket.mq.addr1");

    public static final String ROCKET_MQ_GROUP = ConfigProperties.$("inplay.odds.group");

    public static final String ROCKET_MQ_GROUP1 = ConfigProperties.$("inplay.odds.group1");

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ gaming company ~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static final String LIJI_NAME = GamingCompany.LiJi.getName();

    public static final String JINBAOBO_NAME = GamingCompany.JinBaoBo.getName();

    public static final String SPORTTERY = GamingCompany.Sporttery.getName();

    private Constants() {

    }
}
