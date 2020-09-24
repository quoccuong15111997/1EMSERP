package com.firstems.erp.callback;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nguyen Quoc Cuong on 7/24/2020.
 */
public interface DatePickerCallback {
    void onDatePick(Calendar calendar);
    void typeSelect(TypeSelecDate typeSelecDate);
}
