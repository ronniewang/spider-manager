package com.spider.httputil.request;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class JsonRequestHandler implements RequestHandler<String> {

    public HttpEntity parse(String body) {

        return parse(body, ContentType.create(MediaType.APPLICATION_JSON_VALUE, Charset.forName("utf-8")));
    }

    public HttpEntity parse(String body, ContentType contentType) {

        return new StringEntity(body, contentType);
    }

}
