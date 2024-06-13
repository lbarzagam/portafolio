package com.products.backend.infra.util.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

public class Util_Text {

    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,
            new Locale("es", "ES"));

    /**
     * Obtener un texto aleatorio
     *
     * @param charactersForGenerate Carácteres para generar
     * @param size                  Tamaño de la contraseña
     * @return Retorna la contraseña generada
     */
    public static String randomText(String charactersForGenerate, int size) {
        String pass = "";
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            pass += charactersForGenerate.charAt(ran.nextInt(charactersForGenerate.length()));
        }
        return pass;
    }

    public static String[] split(String text, String separator, boolean stripSpaces) {
        if (stripSpaces)
            return text.trim().split("\\s*" + separator + "\\s*");
        else
            return text.split(separator);
    }

    public static String[] split(String text, String separator) {
        return split(text, separator, true);
    }

    public static String[] split(String text) {
        return text.trim().split("\\s+");
    }

    public static String join(Iterable lst, String sep) {
        String s = "";
        if (lst != null) {
            Iterator iterator = lst.iterator();
            Object e;
            boolean first = true;
            while (iterator.hasNext()) {
                e = iterator.next();
                if (e != null) {
                    if (!first) s += sep;
                    s += e.toString();
                    first = false;
                }
            }
        }
        return s;
    }

    public static String join(Object[] lst, String sep) {
        String s = "";
        boolean first = true;
        for (Object e : lst) {
            if (!first) s += sep;
            s += e.toString();
            first = false;
        }
        return s;
    }

    public static String join(Object[] lst, int from, String sep) {
        return join(lst, from, lst.length - 1, sep);
    }

    public static String join(Object[] lst, int from, int to, String sep) {
        String s = "";
        for (int i = from; i <= to; i++) {
            Object e = lst[i];
            if (i != from) s += sep;
            s += e.toString();
        }
        return s;
    }

    public static Character capital(String s) {
        if (s == null || (s.length() == 0))
            return null;
        Character firstChar = s.charAt(0);
        return Character.toUpperCase(firstChar);
    }

    public static String capitalize(String s, boolean sensitive) {
        return capitalize(sensitive ? s.toLowerCase() : s);
    }

    public static String capitalize(String s) {
        if (s == null || (s.length() == 0))
            return "";
        String firstChar = String.valueOf(s.charAt(0)).toUpperCase();
        return (s.length() > 1) ? firstChar + s.substring(1) : firstChar;
    }

    public static String applyDotThousandsSeparator(Number value) {
        NumberFormat nf = DecimalFormat.getInstance(new Locale("pt", "BR"));
        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
        customSymbol.setGroupingSeparator('.');
        ((DecimalFormat) nf).setDecimalFormatSymbols(customSymbol);
        nf.setGroupingUsed(true);
        return nf.format(value.longValue());
    }

    public static <T> T parseText(Class<T> klass, String value) {
        Object returnValue;
        if (klass == boolean.class || klass == Boolean.class)
            returnValue = (value.isEmpty()) ? null : Boolean.parseBoolean(value);
        else if (klass == char.class || klass == Character.class)
            returnValue = (value.isEmpty()) ? null : value.charAt(0);
        else if (klass == byte.class || klass == Byte.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : Byte.parseByte(value);
        else if (klass == short.class || klass == Short.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : Short.parseShort(value);
        else if (klass == int.class || klass == Integer.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : Integer.parseInt(value);
        else if (klass == BigInteger.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : BigInteger.valueOf(Long.parseLong(value));
        else if (klass == long.class || klass == Long.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : Long.parseLong(value);
        else if (klass == float.class || klass == Float.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : Float.parseFloat(value.replace(",", "."));
        else if (klass == double.class || klass == Double.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : Double.parseDouble(value.replace(",", "."));
        else if (klass == BigDecimal.class)
            returnValue = (value.isEmpty() || value.equals("-")) ? null : BigDecimal.valueOf(Double.parseDouble(value.replace(",", ".")));
        else if (klass == Date.class) {
            try {
                returnValue = Util_Text.dateFormat.parse(value);
            } catch (Exception ex) {
                throw new RuntimeException("Error: convert string to date", ex);
            }
        } else if (klass.isEnum())
            returnValue = Enum.valueOf((Class<? extends Enum>) klass, value);
        else
            returnValue = value;
        return (T) returnValue;
    }

    public static boolean isAlphanumericWithoutSpecialCharacters(String text) {
        String pattern = "^[a-zA-Z0-9]*$";
        return text.matches(pattern);
    }
}
