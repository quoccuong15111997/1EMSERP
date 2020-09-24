package com.firstems.erp.helper.dialog;

import android.content.Context;
import android.widget.DatePicker;

import com.firstems.erp.callback.PickDateCallback;
import com.firstems.erp.helper.datetime.DateTimeHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nguyen Quoc Cuong on 8/5/2020.
 */
public class DatePickerDialog {
    private static DatePickerDialog instance;
    private DatePickerDialog(){

    }
    public static DatePickerDialog getInstance(){
        if (instance==null){
            instance= new DatePickerDialog();
        }
        return instance;
    }
    public void showDialogSelectDate(long minDate, long maxDate, Context context, PickDateCallback pickDateCallback){
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        android.app.DatePickerDialog datePickerDialog = new android.app.DatePickerDialog(context,
                new android.app.DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        //Date date = modifierDate(year,monthOfYear,dayOfMonth);
                        String dateResult = year + "-" + DateTimeHelper.pad(monthOfYear+1) + "-"+DateTimeHelper.pad(dayOfMonth);
                        Date date = com.firstems.erp.helper.date.DateTimeHelper.parseDate(dateResult);
                        pickDateCallback.onDatePicker(date);
                    }
                }, mYear, mMonth, mDay);
        if (minDate!=0){
            datePickerDialog.getDatePicker().setMinDate(minDate);
        }
        if (maxDate!=0){
            datePickerDialog.getDatePicker().setMaxDate(maxDate);
        }
        datePickerDialog.setTitle("Chọn ngày");
        datePickerDialog.show();
    }
}
