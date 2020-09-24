package com.firstems.erp.system;

import com.firstems.erp.database.model.RunCodeData;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/11/2020.
 */
public class SysConfig {
    private static List<RunCodeData> runCodeDataList;
    public static List<RunCodeData> getRunCodeDataList() {
        return runCodeDataList;
    }

    public static void setRunCodeDataList(List<RunCodeData> runCodeDataList) {
        SysConfig.runCodeDataList = runCodeDataList;
    }

    public static RunCodeData getRunCodeDateUpdate(String runCode){
        if (runCodeDataList!=null){
            for (RunCodeData rc : runCodeDataList){
                if (rc.getRunCode().contains(runCode)){
                    return rc;
                }
            }
        }
        return null;
    }
    public static List<Date> createDateLoadSign(){
        DateTime dateEnd = DateTime.now().withTimeAtStartOfDay();
        DateTime  dateBegin = dateEnd.minusDays(SharedPreferencesManager.getInstance().getNumberDaySignature());
        List<Date> dateArrayList = new ArrayList<>();
        dateArrayList.add(dateBegin.toDate());
        dateArrayList.add(dateEnd.toDate());
        return dateArrayList;
    }
}
