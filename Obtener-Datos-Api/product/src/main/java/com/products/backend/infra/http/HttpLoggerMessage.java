package com.products.backend.infra.http;

import com.products.backend.infra.util.json.Util_Json;
import lombok.Data;
import org.apache.http.StatusLine;
import org.apache.http.entity.ContentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class HttpLoggerMessage {
    protected final String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
    protected String transaction;
    protected String appMode;
    protected String action;
    protected String url;
    protected String method;
    protected Map<String, Object> headers;
    protected String payload;
    protected String statusLine;
    protected String response;
    protected long duration;

    public HttpLoggerMessage(String appMode, String transaction, String action, String url, String method, String authorization, ContentType contentType, String payload, StatusLine statusLine, Object response, long duration) {
        super();
        this.appMode = appMode;
        this.transaction = transaction;
        this.action = action;
        this.url = url;
        this.method = method;
        this.headers = new HashMap<>();
        this.headers.put("Authorization", authorization);
        this.headers.put("Content-Type", contentType.getMimeType());
        this.payload = payload;
        this.statusLine = statusLine == null ? null : statusLine.toString();
        this.response = response == null ? null : (response instanceof String ? response.toString() : (response instanceof InputStream ? "Inputstream ..." : Util_Json.writeValueAsString(response)));
        this.duration = duration;
    }

    public String toString() {
        return ("PROD".equals(appMode) || "PRODUCTION".equals(appMode)) ?
                Util_Json.writeValueAsString(this) :
                String.format("Url: %s\n\tHeader: %s\n\tMethod: %s\n\tPayload: %s\n\tStatus line: %s\n\tResponse: %s\n\tDuration: %s\n\t", url, Util_Json.writeValueAsString(headers), method, payload, statusLine, response, duration);
    }
}
