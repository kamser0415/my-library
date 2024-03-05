package com.group.libraryapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class LocalDateTest {

    @Test
    @DisplayName("날짜 테스트")
    void dateTest(){
        LocalDate date = LocalDate.parse("2023-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccc", new Locale("US","KR"));
        String text = date.format(formatter);
        System.out.println("text = " + text);
    }


}
