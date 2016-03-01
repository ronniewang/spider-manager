package com.spider.httputil.response;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ResponseHandlerSupport<T> implements org.apache.http.client.ResponseHandler<T> {

    public static Logger logger = Logger.getLogger(ResponseHandlerSupport.class);

    private int statusCode;

    private StatusLine statusLine;

    protected T response = null;

    private Header[] headers = null;

    @Override
    public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Resposne status line: " + response.getStatusLine());
        }
        this.statusLine = response.getStatusLine();
        this.statusCode = response.getStatusLine().getStatusCode();
        this.headers = response.getAllHeaders();
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            this.response = parseResponse(httpEntity);
        }
        return this.response;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public StatusLine getStatusLine() {
        return this.statusLine;
    }

    public List<Header> getHeaders() {
        if (headers != null) {
            return Arrays.asList(this.headers);
        }
        return new ArrayList<Header>(0);
    }

    abstract T parseResponse(HttpEntity httpEntity) throws ClientProtocolException, IOException;

    public abstract String getRawResponse() ;

}
