package com.firstems.erp.helper.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nguyen Quoc Cuong on 7/24/2020.
 */
public class DateTimeHelper {
    public static String pad(int value) {
        if (value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }
    public static Date getDateWithoutTimeUsingFormat()
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        return formatter.parse(formatter.format(new Date(System.currentTimeMillis())));
    }
}
