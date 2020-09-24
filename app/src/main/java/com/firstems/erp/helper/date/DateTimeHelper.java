package com.firstems.erp.helper.date;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.joda.time.DateTime;

import static android.content.ContentValues.TAG;

/**
 * Created by Nguyen Quoc Cuong on 7/31/2020.
 */
public class DateTimeHelper {
    private static  DateTimeHelper instance;
    public SimpleDateFormat apiDateFormat;
    public SimpleDateFormat displayDateFormat;
    private static final SimpleDateFormat DATE_FORMATTER =
            new SimpleDateFormat("yyyy-MM-dd");
    private DateTimeHelper(){
        apiDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        displayDateFormat = new SimpleDateFormat("dd_MM-yyyy");
    }
    public static  DateTimeHelper getInstance(){
        if (instance==null)
            instance =  new DateTimeHelper();
        return instance;
    }
    public String minusDate(int numberDay){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); // Or where ever you get it from
        Date daysAgo = new DateTime(date).minusDays(numberDay).toDate();
        return  simpleDateFormat.format(daysAgo);
    }
    public String plusDate(int numberDay){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); // Or where ever you get it from
        Date daysAgo = new DateTime(date).plusDays(numberDay).toDate();
        return  simpleDateFormat.format(daysAgo);
    }
    public static Date parseDate(String date) {
        try {
            return DATE_FORMATTER.parse(date);
        } catch (ParseException e) {
            Log.d(TAG, "parseDate() caught ParseException", e);
            e.printStackTrace();
            return null;
        }
    }
}
