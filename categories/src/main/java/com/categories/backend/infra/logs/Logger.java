package com.categories.backend.infra.logs;

import com.categories.backend.infra.util.json.Util_Json;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Reinier Elejalde Chacon
 */
public class Logger {
    private static final Map<String, Logger> cache = new HashMap<String, Logger>();

    private final String id;

    private Logger(String id) {
        this.id = id;
    }

    public static  Logger getLogger(String id) {
        if (id == null)
            id = "";

         Logger l = cache.get(id);
        if (l == null)
            cache.put(id, l = new  Logger(id));

        return l;
    }

    public void info(String action, Object info, String... actionArgs) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        int l = ste.getLineNumber();
        String a = ste.getClassName(),
                c = ste.getMethodName(),
                d = (info == null) ? "" : String.format("\n\t%s tx:%s", info instanceof String ? info : Util_Json.writeValueAsString(info), id),
                e = String.format("%s - call: %s(%s) line: %s tx: %s %s", action, c, String.join(",", actionArgs), l, id, d);

        LoggerFactory.getLogger(getClass(a)).info(e);
    }

    public void error(String action, String message, Object info, String... actionArgs) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        int l = ste.getLineNumber();
        String a = ste.getClassName(),
                c = ste.getMethodName(),
                d = info == null ?
                        String.format("\n\t%s tx:%s", message, id) :
                        String.format("\n\t%s tx:%s\n\t%s tx:%s", info instanceof String ? info : Util_Json.writeValueAsString(info), id, message, id),
                e = String.format("%s - call: %s(%s) line: %s tx: %s %s", action, c, String.join(",", actionArgs), l, id, d);

        LoggerFactory.getLogger(getClass(a)).error(e);
    }

    @SuppressWarnings("rawtypes")
    public Class getClass(String n) {
        Class c =  Logger.class;
        try {
            c = Class.forName(n);
        } catch (Exception e) {
        }

        return c;
    }
}
