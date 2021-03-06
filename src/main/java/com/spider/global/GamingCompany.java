package com.spider.global;

/**
 * 关注的博彩公司
 *
 * @author ronnie
 */
public enum GamingCompany {

    JinBaoBo("23", "金宝博"), LiJi("31", "利记");

    GamingCompany(String id, String name) {

        this.id = id;
        this.name = name;
    }

    private String id;

    private String name;

    public String getId() {

        return id;
    }

    public String getName() {

        return this.name;
    }

    public static String abbr(String company) {

        if (company.contains("金")) {
            return "jbb";
        } else {
            return "lj";
        }
    }

}
