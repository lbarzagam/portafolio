package com.categories.backend.infra.http;


import com.categories.backend.infra.util.json.Util_Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.boon.json.JsonParserFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
@Service
public class RequestHandler {

    public RequestHandler() {
    }

    private CloseableHttpClient httpClient() throws Exception {
        return HttpClients.custom().setSSLContext((new SSLContextBuilder()).loadTrustMaterial(null, (certificate, authType) -> {
            return true;
        }).build()).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
    }

    /**
     * Handles HTTP Requests
     *
     * @param url_,     object-type: String
     * @param request_, object-type: {@link HttpUriRequest}
     * @return response data
     * @throws Exception
     **/
    private <T> T handleRequest(String url_, HttpUriRequest request_, boolean isXmlRequest) throws Exception {
        T response;
        Map<String, Object> responseMap = new HashMap<>();

        Throwable var4 = null;
        try {

            try {
                Throwable var9 = null;
                CloseableHttpResponse httpResponse = httpClient().execute(request_);

                try {
                    int statusCode = httpResponse.getStatusLine().getStatusCode();
                    Range<Integer> range = Range.between(200, 299);
                    if (!range.contains(statusCode)) {
                        String message = "Estado '" + statusCode + "' al consultar el servicio '" + url_ + "'";

                        // Verificando Header especificos para Defontana...
                        if (httpResponse.containsHeader("x-amzn-Remapped-WWW-Authenticate")) {
                            Header header_ = httpResponse.getHeaders("x-amzn-Remapped-WWW-Authenticate")[0];
                            responseMap.put("defontana_header", header_.getValue());
                        }

                        responseMap.put("message", message);
                        responseMap.put("status", statusCode);

                        try {
                            InputStream binary = httpResponse.getEntity().getContent();
                            response = (T) new JsonParserFactory().create().parse(binary);

                            responseMap.put("error", (response != null && !response.equals("") ? response : "REQUEST ERROR"));
                        } catch (Exception exc) {
                            try {
                                String entityError_ = EntityUtils.toString(httpResponse.getEntity());
                                responseMap.put("error", entityError_.isEmpty() ? "REQUEST ERROR" : entityError_);
                            } catch (Exception ex) {
                                responseMap.put("error", "No ha sido posible obtener info del error");
                            }
                        }
                        return (T) responseMap;
                    }

                    try {
                        InputStream stream_ = httpResponse.getEntity().getContent();
                        Throwable var11 = null;

                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            Map<String, Object> jsonMap;
                            if(isXmlRequest)
                                jsonMap = (new XmlMapper()).readValue(stream_, Map.class);
                            else
                               jsonMap = mapper.readValue(stream_, Map.class);

                            response = (T) jsonMap;
                            /*response = (T) (new JsonParserFactory()).create().parse(stream_);*/
                        } catch (Throwable var64) {
                            var11 = var64;
                            throw var64;
                        } finally {
                            if (stream_ != null) {
                                if (var11 != null) {
                                    try {
                                        stream_.close();
                                    } catch (Throwable var63) {
                                        var11.addSuppressed(var63);
                                    }
                                } else {
                                    stream_.close();
                                }
                            }
                        }
                    } catch (IOException var66) {
                        responseMap.put("error", "Error creando InputStream al consultar el servicio '" + url_ + "'");
                        responseMap.put("message", var66.getMessage());
                        return (T) responseMap;
                    }
                } catch (Throwable var67) {
                    var9 = var67;
                    throw var67;
                } finally {
                    if (httpResponse != null) {
                        if (var9 != null) {
                            try {
                                httpResponse.close();
                            } catch (Throwable var62) {
                                var9.addSuppressed(var62);
                            }
                        } else {
                            httpResponse.close();
                        }
                    }
                }
            } catch (ClientProtocolException var69) {
                responseMap.put("error", "Error de protocolo HTT consultando el servicio: '" + url_ + "'");
                responseMap.put("message", var69.getMessage());
                return (T) responseMap;
            } catch (IOException var70) {
                responseMap.put("error", "Error de conexión consultando el servicio: '" + url_ + "'");
                responseMap.put("message", var70.getMessage());
                return (T) responseMap;
            }

        } catch (Throwable var71) {
            var4 = var71;
            throw var71;
        } finally {
            if (httpClient() != null) {
                if (var4 != null) {
                    try {
                        httpClient().close();
                    } catch (Throwable var61) {
                        var4.addSuppressed(var61);
                    }
                } else {
                    httpClient().close();
                }
            }
        }
        return response;
    }

    /**
     * HTTP Request using Method GET
     *
     * @param data, object-type: Map<String, Object>
     *              required-param: url (String)
     *              optional-param: authorization (String value for header Authorization)
     * @return response data
     * @throws Exception
     **/
    public <T> T get(Map<String, Object> data) throws Exception {
        String url_ = data.get("url").toString();
        String authorization_ = data.containsKey("authorization") ? data.get("authorization").toString() : null;

        HttpGet httpGet_ = new HttpGet(url_);
        if (authorization_ != null) {
            httpGet_.addHeader("Authorization", authorization_);
        }

        return handleRequest(url_, httpGet_, false);
    }

    /**
     * HTTP Request using Method DELETE
     *
     * @param data, object-type: Map<String, Object>
     *              required-param: url (String)
     *              optional-param: authorization (String value for header Authorization)
     * @return response data
     * @throws Exception
     **/
    public <T> T delete(Map<String, Object> data) throws Exception {
        String url_ = data.get("url").toString();
        String authorization_ = data.containsKey("authorization") ? data.get("authorization").toString() : null;

        HttpDelete httpDelete_ = new HttpDelete(url_);
        if (authorization_ != null) {
            httpDelete_.addHeader("Authorization", authorization_);
        }

        return handleRequest(url_, httpDelete_, false);
    }

    /**
     * HTTP Request using Method POST
     *
     * @param data, object-type: Map<String, Object>
     *              required-param: url (String)
     *              required-param: payload (Map<String, Object>)
     *              optional-param: authorization (String value for header Authorization)
     *              optional-param: binary_file (File value for uploads)
     *              optional-param: content_type_json (Boolean value for setting up header Content-Type: application/json)
     *              optional-param: content_type_formdata (Boolean value for setting up header Content-Type: multipart/form-data)
     * @return response data
     * @throws Exception
     **/
    public <T> T post(Map<String, Object> data) throws Exception {
        String url_ = CommonHelpers.getFromMap("url", data, "").toString();
        String authorization_ = CommonHelpers.getFromMap("authorization", data, "").toString();
        String payloadStr = CommonHelpers.getFromMap("payload", data, "").toString();
        Map<String, Object> payload_ = new HashMap<>();
        if(StringUtils.isBlank(payloadStr))
            payload_ = CommonHelpers.hashMapFromMap("payload", data);

        HttpPost httpPost_ = new HttpPost(url_);
        if (!authorization_.isEmpty()) {
            httpPost_.addHeader("Authorization", authorization_);
        }
        if (!data.containsKey("content_type_json") || (boolean) data.get("content_type_json")) {
            httpPost_.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        }

        if (data.get("binary_file") != null) {
            File binary_ = (File) CommonHelpers.getFromMap("binary_file", data);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addBinaryBody("file", binary_, ContentType.DEFAULT_BINARY, binary_.getName());
            for (Map.Entry<String, Object> meta_ : payload_.entrySet())
                builder.addTextBody(meta_.getKey(), meta_.getValue().toString(), ContentType.DEFAULT_BINARY);

            httpPost_.setEntity(builder.build());
        } else
            httpPost_.setEntity(new StringEntity(Util_Json.writeValueAsString(payload_), ContentType.APPLICATION_XML));

        return handleRequest(url_, httpPost_, false);
    }

    public <T> T postXml(Map<String, Object> data) throws Exception {
        String url_ = data.getOrDefault("url", "").toString();
        String authorization_ = data.getOrDefault("authorization", "").toString();
        String payload_ = data.getOrDefault("payload", "").toString();
        String contentType = data.getOrDefault("content_type", ContentType.APPLICATION_XML).toString();

        HttpPost hPost = new HttpPost(url_);
        hPost.setHeader("Content-Type", contentType);
        if (!authorization_.isEmpty()) {
            hPost.addHeader("Authorization", authorization_);
        }

        if (data.get("binary_file") != null) {
            File binary_ = (File) data.getOrDefault("binary_file", new HashMap<String, Object>());
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addBinaryBody("file", binary_, ContentType.DEFAULT_BINARY, binary_.getName());
            hPost.setEntity(builder.build());
        } else {
            hPost.setEntity(new StringEntity(payload_, ContentType.parse(contentType)));
        }

        return handleRequest(url_, hPost, true);
    }

    /**
     * HTTP Request using Method POST
     *
     * @param data, object-type: Map<String, Object>
     *              required-param: url (String)
     *              required-param: payload (Map<String, Object>)
     *              optional-param: authorization (String value for header Authorization)
     *              optional-param: content_type_json (Boolean value for setting up header Content-Type: application/json)
     *              optional-param: content_type_formdata (Boolean value for setting up header Content-Type: multipart/form-data)
     * @return response data
     * @throws Exception
     **/
    public <T> T put(Map<String, Object> data) throws Exception {
        String url_ = CommonHelpers.getFromMap("url", data, "").toString();
        String authorization_ = CommonHelpers.getFromMap("authorization", data, "").toString();
        Map<String, Object> payload_ = CommonHelpers.hashMapFromMap("payload", data);

        HttpPut httpPut_ = new HttpPut(url_);
        if (!authorization_.isEmpty()) {
            httpPut_.addHeader("Authorization", authorization_);
        }
        if (!data.containsKey("content_type_json") || (boolean) data.get("content_type_json")) {
            httpPut_.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        }

        httpPut_.setEntity(new StringEntity(Util_Json.writeValueAsString(payload_), ContentType.APPLICATION_JSON));

        return handleRequest(url_, httpPut_, false);
    }
}

