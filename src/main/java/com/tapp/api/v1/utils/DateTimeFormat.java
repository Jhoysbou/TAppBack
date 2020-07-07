package com.tapp.api.v1.utils;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }
}
