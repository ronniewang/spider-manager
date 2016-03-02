package com.spider.manager.service;

import com.spider.manager.model.ProductModel;

import java.util.Date;
import java.util.List;


public interface MatchProductService {

    List<ProductModel> listMatchProduct(Date startDate, Date endDate);

}
