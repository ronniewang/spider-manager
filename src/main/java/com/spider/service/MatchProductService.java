package com.spider.service;

import com.spider.model.ProductModel;

import java.util.Date;
import java.util.List;


public interface MatchProductService {

    List<ProductModel> listMatchProduct(Date startDate, Date endDate);

}
