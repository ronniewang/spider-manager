package com.spider.httputil.response;

import com.spider.global.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class JsonResponseHandler extends ResponseHandlerSupport<String> {

    private String charset = "utf-8";

    @Override
    public String getRawResponse() {

        return this.response;
    }

    @Override
    public String parseResponse(HttpEntity httpEntity) throws ClientProtocolException, IOException {

        this.response = EntityUtils.toString(httpEntity, Charset.forName(charset));
        return this.response;
    }

    public String getCharset() {

        return charset;
    }

    public void setCharset(String charset) {

        this.charset = charset;
    }

}
