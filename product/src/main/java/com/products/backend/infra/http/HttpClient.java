package com.products.backend.infra.http;

import com.products.backend.domain.validators.CodigoErrorEnum;
import com.products.backend.infra.exceptions.InfraException;
import com.products.backend.infra.logs.Logger;
import com.products.backend.infra.util.json.Util_Json;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * @author Reinier Elejalde Chacon
 */
@Component
public class HttpClient {
    @Value("${app.mode}")
    protected String APP_MODE;
    @Value("${tmp.folder.path}")
    protected String TMP_FOLDER;

    public static CloseableHttpClient createHttpClient() throws Exception {
        return HttpClients.custom().setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build()).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
    }

    public <T> T executePost(String txId, String action, String URL, String authorizationHeader, Object payload, Class<T> responseClass) {
        return executePostOrPut(txId, action, new HttpPost(URL), authorizationHeader, ContentType.APPLICATION_JSON, payload, responseClass);
    }

    public <T> T executePost(String txId, String action, String URL, String authorizationHeader, ContentType contentType, Object payload, Class<T> responseClass) {
        return executePostOrPut(txId, action, new HttpPost(URL), authorizationHeader, contentType, payload, responseClass);
    }

    public <T> T executePut(String txId, String action, String URL, String authorizationHeader, Object payload, Class<T> responseClass) {
        return executePostOrPut(txId, action, new HttpPut(URL), authorizationHeader, ContentType.APPLICATION_JSON, payload, responseClass);
    }

    public <T> T executeGet(String txId, String action, String URL, String authorizationHeader, Class<T> responseClass, boolean silent) {
        return executeHeadOrGet(txId, action, new HttpGet(URL), responseClass, silent);
    }

    public File downloadFile(String txId, String action, String documentName, String URL, String authorizationHeader, boolean silent) throws Exception {
        return downloadDocument(txId, action, documentName, new HttpGet(URL), silent);
    }

    public <T> T executeHead(String txId, String action, String URL, String authorizationHeader, Class<T> responseClass, boolean silent) throws Exception {
        return executeHeadOrGet(txId, action, new HttpHead(URL), responseClass, silent);
    }

    @SneakyThrows
    private <T> T executePostOrPut(String txId, String action, HttpEntityEnclosingRequestBase method, String authorizationHeader, ContentType contentType, Object payload, Class<T> responseClass) {
        long t = System.currentTimeMillis();
        String jsonPayload = payload != null ? (payload instanceof String ? (String) payload : Util_Json.writeValueAsString(payload)) : null;
        StatusLine statusLine = null;
        T response = null;

        try (CloseableHttpClient httpclient = HttpClient.createHttpClient()) {
            if (authorizationHeader != null)
                method.setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
            method.setHeader(HttpHeaders.CONTENT_TYPE, contentType.toString());

            if (jsonPayload != null)
                method.setEntity(new StringEntity(jsonPayload, contentType));

            try (CloseableHttpResponse httpResponse = httpclient.execute(method)) {
                if ((statusLine = httpResponse.getStatusLine()).getStatusCode() == 200 || statusLine.getStatusCode() == 201)
                    try (InputStream input = httpResponse.getEntity().getContent()) {
                        response = Util_Json.getObjectMapper().readValue(input, responseClass);
                    }
                else
                    throw new Exception(String.format("An error occurred trying to execute the request: %s",
                            new HttpLoggerMessage(APP_MODE, txId, action, method.getURI().toString(), method.getMethod(), authorizationHeader, contentType, jsonPayload, statusLine, response, System.currentTimeMillis() - t)));
            }
        } catch (Exception e) {
            Logger.getLogger(txId).error(action, new HttpLoggerMessage(APP_MODE, txId, action, method.getURI().toString(), method.getMethod(), authorizationHeader, contentType, jsonPayload, statusLine, response, System.currentTimeMillis() - t).toString(), null);
            throw e;
        }

        return response;
    }

    @SneakyThrows
    private <T> T executeHeadOrGet(String txId, String action, HttpRequestBase method, Class<T> responseClass, boolean silent) {
        long t = System.currentTimeMillis();
        StatusLine statusLine = null;
        T response = null;

        try (CloseableHttpClient httpclient = HttpClient.createHttpClient()) {
            method.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            try (CloseableHttpResponse httpResponse = httpclient.execute(method)) {
                if ((statusLine = httpResponse.getStatusLine()).getStatusCode() == 200 || statusLine.getStatusCode() == 201)
                    try (InputStream input = httpResponse.getEntity().getContent()) {
                        response = Util_Json.getObjectMapper().readValue(input, responseClass);
                    }
                else
                    throw new InfraException(CodigoErrorEnum.INFRA_ERROR_CONSUMIENDO_SERVICIO_HTTP, "Error consumiendo servicio HTTP. Código respuesta: " + statusLine.getStatusCode());
            }
        } catch (Exception e) {
            Logger.getLogger(txId).error(action, new HttpLoggerMessage(APP_MODE, txId, action, method.getURI().toString(), method.getMethod(), null, ContentType.APPLICATION_JSON, null, statusLine, response, System.currentTimeMillis() - t).toString(), null);
            throw e;
        }
        if (!silent)
            Logger.getLogger(txId).info(action, new HttpLoggerMessage(APP_MODE, txId, action, method.getURI().toString(), method.getMethod(), null, ContentType.APPLICATION_JSON, null, statusLine, responseClass == InputStream.class ? "InputStream..." : response, System.currentTimeMillis() - t).toString());

        return response;
    }

    private File downloadDocument(String txId, String action, String documentName, HttpRequestBase method, boolean silent) throws Exception {
        long t = System.currentTimeMillis();
        StatusLine statusLine = null;
        File response = null;

        try (CloseableHttpClient httpclient = HttpClient.createHttpClient()) {
            method.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            try (CloseableHttpResponse httpResponse = httpclient.execute(method)) {
                if ((statusLine = httpResponse.getStatusLine()).getStatusCode() == 200 || statusLine.getStatusCode() == 201)
                    try (InputStream input = httpResponse.getEntity().getContent()) {
                        FileUtils.copyInputStreamToFile(input, response = new File(TMP_FOLDER, documentName+".pdf" /*UUID.randomUUID().toString()*/));
                    } catch (Exception e) {
                        response = null;
                    }
                else
                    throw new Exception(String.format("An error occurred trying to execute the request: %s",
                            new HttpLoggerMessage(APP_MODE, txId, action, method.getURI().toString(), method.getMethod(), null, ContentType.APPLICATION_JSON, null, statusLine, response, System.currentTimeMillis() - t)));
            }
        } catch (Exception e) {
            Logger.getLogger(txId).error(action, new HttpLoggerMessage(APP_MODE, txId, action, method.getURI().toString(), method.getMethod(), null, ContentType.APPLICATION_JSON, null, statusLine, response, System.currentTimeMillis() - t).toString(), null);
            throw e;
        }
        if (!silent)
            Logger.getLogger(txId).info(action, new HttpLoggerMessage(APP_MODE, txId, action, method.getURI().toString(), method.getMethod(), null, ContentType.APPLICATION_JSON, null, statusLine, response, System.currentTimeMillis() - t).toString());

        return response;
    }
}
