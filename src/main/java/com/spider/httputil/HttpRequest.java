package com.spider.httputil;

import com.spider.httputil.request.JsonRequestHandler;
import com.spider.httputil.request.RequestHandler;
import com.spider.httputil.response.JsonResponseHandler;
import com.spider.httputil.response.ResponseHandlerSupport;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {

    private static final Integer HTTP_UTIL_REQUEST_CONNECTION_TIMEOUT = 1000;

    private static final Integer HTTP_UTIL_CONNECTION_TIMEOUT = 1000;

    private static final Integer HTTP_UTIL_SOCKET_TIMEOUT = 1000;

    private static final Integer HTTP_UTIL_CONNECTION_MANAGER_MAX_TOTAL = 200;

    private static final Integer HTTP_UTIL_CONNECTION_MANAGER_MAX_PER_ROUTE = 1000;

    private static Logger logger = Logger.getLogger(HttpRequest.class);

    private static PoolingHttpClientConnectionManager connectionManager;

    {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(HTTP_UTIL_CONNECTION_MANAGER_MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(HTTP_UTIL_CONNECTION_MANAGER_MAX_PER_ROUTE);
    }

    public static HttpRequest createRequest(final String endpoint) {

        return new HttpRequest(endpoint);
    }

    private final String endpoint;

    private final List<NameValuePair> parameters;

    private final List<Header> headers;

    private final RequestConfig requestConfig;

    private long executeStart;

    private long executeEnd;

    private HttpRequest(final String endpoint) {

        this.endpoint = endpoint;
        this.parameters = new ArrayList<>();
        this.headers = new ArrayList<>();
        Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder.setConnectionRequestTimeout(HTTP_UTIL_REQUEST_CONNECTION_TIMEOUT);
        requestConfigBuilder.setConnectTimeout(HTTP_UTIL_CONNECTION_TIMEOUT);
        requestConfigBuilder.setSocketTimeout(HTTP_UTIL_SOCKET_TIMEOUT);
        this.requestConfig = requestConfigBuilder.build();
    }

    public <RES> RES GET(Class<RES> cls, ResponseHandlerSupport<RES> responseHandler) throws HttpException {

        URI uri = buildURI();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(this.requestConfig);
        return buildResponse(httpGet, cls, responseHandler);
    }

    public String GET() throws HttpException {

        return GET(String.class, new JsonResponseHandler());
    }

    public String POST(String postBody) throws HttpException {

        return POST(String.class, postBody, new JsonRequestHandler(), new JsonResponseHandler());
    }

    public <REQ, RES> RES POST(final Class<RES> cls, final REQ postBody, final RequestHandler<REQ> requestHandler, final ResponseHandlerSupport<RES> responseHandler) throws HttpException {

        URI uri = buildURI();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(requestHandler.parse(postBody));
        httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        return buildResponse(httpPost, cls, responseHandler);
    }

    public void addParameter(final String name, final String value) {

        BasicNameValuePair nameValuePair = new BasicNameValuePair(name, value);
        this.parameters.add(nameValuePair);
    }

    public void addHeader(final String name, final String value) {

        this.headers.add(new BasicHeader(name, value));
    }

    private URI buildURI() throws HttpException {

        try {
            URIBuilder uriBuilder = null;
            uriBuilder = new URIBuilder(this.endpoint);
            uriBuilder.setParameters(this.parameters);
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            final String msg = MessageFormat.format("Invalid request URL={0}", this.endpoint);
            logger.error(msg, e);
            throw new HttpException(msg);
        }
    }

    private HttpClient buildClient() {

        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    private <T> T buildResponse(HttpUriRequest request, Class<T> cls, ResponseHandlerSupport<T> responseHandler) throws HttpException {

        executeStart = System.currentTimeMillis();
        HttpClient httpClient = buildClient();
        try {
            return httpClient.execute(request, responseHandler);
        } catch (ClientProtocolException e) {
            final String msg = "Http request execute error.";
            logger.error(msg, e);
            throw new HttpException(msg);
        } catch (IOException e) {
            final String msg = "Http request io error.";
            logger.error(msg, e);
            throw new HttpException(msg);
        } finally {
            executeEnd = System.currentTimeMillis();
            logResponse(request, responseHandler);
        }
    }

    public <T> void logResponse(HttpUriRequest request, ResponseHandlerSupport<T> responseHandler) {

        String method = null;
        String body = "";
        if (request instanceof HttpGet) {
            HttpGet httpGet = (HttpGet) request;
            method = httpGet.getMethod();
        } else if (request instanceof HttpPost) {
            HttpPost httpPost = (HttpPost) request;
            HttpEntity requestEntity = httpPost.getEntity();
            try {
                if (requestEntity != null) {
                    body = EntityUtils.toString(requestEntity);
                }
            } catch (ParseException e) {
                logger.error("Parse request entity failed when logging.", e);
            } catch (IOException e) {
                logger.error("Parse request entity failed because of IO when logging.", e);
            }
            method = httpPost.getMethod();
        }

        String msg = MessageFormat.format("{0} \n\tMETHOD={1} \n\tParameters = {2}\n\tRequest Headers = {3}\n\t Body={4}\n\t Duration={5}\n\t Response Status={6}\n\t"
                        + "Response Status Text={7}\n\tResponse Status Line={8}\n\tResponse Headers={9}\n\tResponse={10}", this.endpoint, method, this.parameters, this.headers, body,
                (executeEnd - executeStart) + "ms", responseHandler.getStatusCode(), responseHandler.getStatusLine() != null ? responseHandler.getStatusLine().getReasonPhrase() : null,
                responseHandler.getStatusLine(), responseHandler.getHeaders(), responseHandler.getRawResponse());
        logger.trace(msg);
    }
}
