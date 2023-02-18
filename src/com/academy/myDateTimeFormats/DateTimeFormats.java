package com.academy.myDateTimeFormats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormats {
    public static String lectureDateFormat(LocalDateTime dateTime){
    return DateTimeFormatter.ofPattern("MMM d, EEEE HH:mm:ss", Locale.UK).format(dateTime);
    }

    public static String hwDeadlineFormat(LocalDateTime dateTime){
        return DateTimeFormatter.ofPattern("MMM d, HH:mm", Locale.UK).format(dateTime);
    }

    public static String logDateFormat(LocalDateTime dateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss:SSS").format(dateTime);
    }

    public static String dayMonthYear(LocalDate date){
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date);
    }
}
