package com.spider.httputil.request;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;

public interface RequestHandler<T> {

    HttpEntity parse(T body);
    
    HttpEntity parse(T body, ContentType contentType);

}
