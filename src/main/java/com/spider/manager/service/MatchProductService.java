package com.spider.manager.service;

import com.spider.manager.model.ProductModel;

import java.util.Date;
import java.util.List;


public interface MatchProductService {

    /**
     * 列出所有奖池
     *
     * @param startDate 11点起，如果为空，查询今天之后所有的
     * @param endDate   11点结束
     * @return
     */
    List<ProductModel> listMatchProduct(Date startDate, Date endDate);
}
