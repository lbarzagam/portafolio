package com.categories.backend.infra.util.datetime;

import com.categories.backend.domain.validators.CodigoErrorEnum;
import com.categories.backend.infra.exceptions.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Util_DateTime {

    public static final DateFormatSymbols DATE_FORMAT_SYMBOLS = new DateFormatSymbols(new Locale("es", "ES"));

    public static String getChileanDate() {
        return new SimpleDateFormat("EEEE d MMMM 'de' yyyy", DATE_FORMAT_SYMBOLS).format(Date.from(Instant.now().atZone(ZoneId.of("Chile/Continental")).toInstant()));
    }

    public static String getChileanTime() {
        return Instant.now().atZone(ZoneId.of("Chile/Continental")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static LocalDate getChileanLocalDate() {
        return Instant.now().atZone(ZoneId.of("Chile/Continental")).toLocalDate();
    }

    public static LocalDateTime getChileanLocalDateTime() {
        return Instant.now().atZone(ZoneId.of("Chile/Continental")).toLocalDateTime();
    }

    public static LocalDateTime getServerLocalDateTimeFromChileanLocalDate(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return null;
        ZonedDateTime zonedChilean = localDateTime.atZone(ZoneId.of("Chile/Continental"));
        ZonedDateTime zonedLocalServer = zonedChilean.withZoneSameInstant(ZoneId.of("UTC"));
        return zonedLocalServer.toLocalDateTime();
    }

    public static LocalDateTime getChileanLocalDateTimeFromServerLocalDate(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return null;
        ZonedDateTime zonedLocalServer = localDateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedChilean = zonedLocalServer.withZoneSameInstant(ZoneId.of("Chile/Continental"));
        return zonedChilean.toLocalDateTime();
    }

    public static LocalDateTime getServerLocalDateTimeFromChileanLocalDate(LocalDate localDate) {
        return getServerLocalDateTimeFromChileanLocalDate(LocalDateTime.of(localDate, LocalTime.of(0, 0)));
    }

    public static ZoneId getChileanZoneId() {
        return ZoneId.of("Chile/Continental");
    }

    @Getter
    @AllArgsConstructor
    private static class FormatoFechaHora {
        private final String formato;
        private final boolean incluyeHora;
    }

    private static List<FormatoFechaHora> formatos() {
        return Arrays.asList(
                new FormatoFechaHora("dd/M/yyyy HH:mm:ss", true),
                new FormatoFechaHora("dd/M/yyyy HH:m:ss", true),
                new FormatoFechaHora("dd/MM/yyyy HH:mm:ss", true),
                new FormatoFechaHora("yyyy-MM-dd'T'HH:mm:ss", true),
                new FormatoFechaHora("yyyy-MM-dd'T'HH:mm:ss'Z'", true),
                new FormatoFechaHora("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", true),
                new FormatoFechaHora("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'", true),
                new FormatoFechaHora("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", true),
                new FormatoFechaHora("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", true),
                new FormatoFechaHora("yyyy-MM-dd HH:mm:ss.SSS", true),
                new FormatoFechaHora("yyyy-MM-dd HH:mm:ss.S", true),
                new FormatoFechaHora("yyyy-MM-dd HH:mm:ss", true),
                new FormatoFechaHora("dd/MM/yyyy", false),
                new FormatoFechaHora("yyyy-MM-dd", false)
        );
    }

    public static LocalDateTime deserializarFechaHora(String fechaHora, boolean horaRequerida) {
        if (StringUtils.isBlank(fechaHora))
            return null;
        List<FormatoFechaHora> formatos = formatos();
        Date fecha = null;
        for (FormatoFechaHora formato : formatos) {
            if (horaRequerida && !formato.isIncluyeHora())
                continue;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato.getFormato());
            try {
                fecha = simpleDateFormat.parse(fechaHora);
                if (fechaHora.length() == formato.getFormato().replace("'", "").length())
                    break;
            } catch (Exception e) {
                continue;
            }
        }

        if (fecha == null)
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS, "Error parseando fecha: " + fechaHora);

        LocalDateTime localDateTime = Instant.ofEpochMilli(fecha.getTime())
                .atZone(ZoneId.of("Chile/Continental"))
                .toLocalDateTime();
        return procesarConHorarioDeVeranoSiAplica(localDateTime);
    }

    public static LocalDateTime deserializarFechaHoraSinTimeZone(String fechaHora, boolean horaRequerida) {
        if (StringUtils.isBlank(fechaHora))
            return null;
        List<FormatoFechaHora> formatos = formatos();
        LocalDateTime fecha = null;
        for (FormatoFechaHora formato : formatos) {
            if (horaRequerida && !formato.isIncluyeHora())
                continue;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formato.getFormato());
            try {
                fecha = LocalDateTime.parse(fechaHora, dateTimeFormatter);
            } catch (Exception e) {
                continue;
            }
        }

        if (fecha == null)
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS, "Error parseando fecha: " + fechaHora);
        return fecha;
    }

    public static LocalDate deserializarFechaHoraAsLocalDate(String fechaHora) {
        if (StringUtils.isBlank(fechaHora))
            return null;
        List<FormatoFechaHora> formatos = formatos();
        LocalDate fecha = null;
        for (FormatoFechaHora formato : formatos) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formato.getFormato());
            try {
                fecha = LocalDate.parse(fechaHora, dateTimeFormatter);
            } catch (Exception e) {
                continue;
            }
        }

        if (fecha == null)
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS, "Error parseando fecha: " + fechaHora);
        return fecha;
    }

    public static Date deserializarFechaHoraAsDate(String fechaHora, boolean horaRequerida) {
        return toDate(deserializarFechaHora(fechaHora, horaRequerida));
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null)
            return null;
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.of("Chile/Continental"))
                .toLocalDateTime();
        return procesarConHorarioDeVeranoSiAplica(localDateTime);
    }

    public static String toFormattedLocalDateTime(Date date, String formatPattern) {
        if (date == null)
            return null;
        LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.of("Chile/Continental"))
                .toLocalDateTime();
        return procesarConHorarioDeVeranoSiAplica(localDateTime).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static Date toDate(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.of("Chile/Continental"));
        return Date.from(zdt.toInstant());
    }

    public static Date toDate(LocalDate localDate) {
        if (localDate == null)
            return null;
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
        return toDate(localDateTime);
    }

    public static Date toDateSinZonaHoraria(LocalDateTime localDateTime) throws Exception {
        String formato = "dd/MM/yyyy HH:mm:ss";
        String str = localDateTime.format(DateTimeFormatter.ofPattern(formato));
        return new SimpleDateFormat(formato).parse(str);
    }

    public static LocalDateTime procesarConHorarioDeVeranoSiAplica(LocalDateTime localDateTime) {
        TimeZone timeZone = TimeZone.getTimeZone(ZoneId.of("Chile/Continental"));
        boolean esHorarioVeranoAhora = timeZone.inDaylightTime(toDate(LocalDateTime.now()));
        boolean fueHorarioVeranoEnFecha = timeZone.inDaylightTime(toDate(localDateTime));
        if (esHorarioVeranoAhora && !fueHorarioVeranoEnFecha) {
            return localDateTime.plusHours(1);
        }
        //TODO Ver cuando cambie la hora si es que cuando no es horario de verano hay que restar una hora
        return localDateTime;
    }
}
