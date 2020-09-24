package com.firstems.erp.model.business;

import com.firstems.erp.api.model.response.locationtype.LocationType;
import com.firstems.erp.api.model.response.national.National;
import com.firstems.erp.api.model.response.province.Province;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCT;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class Business implements Serializable {
    private String mainCode;
    private Date mainDate;
    private LocationType locationType;
    private National national;
    private Province province;
    private Date dateBegin;
    private Date dateEnd;
    private TimekeepingTypeCT timekeepingTypeCTMorn;
    private TimekeepingTypeCT timekeepingTypeCTAfft;
    private TimekeepingTypeCT timekeepingTypeCTEvrn;
    private boolean isService_0;
    private boolean isService_1;
    private boolean isService_2;
    private boolean isService_3;
    private boolean isService_4;
    private boolean isService_5;
    private boolean isService_6;
    private boolean isService_7;
    private String listService;
    private int workDay;
    private String noteText;

    public TimekeepingTypeCT getTimekeepingTypeCTMorn() {
        return timekeepingTypeCTMorn;
    }

    public void setTimekeepingTypeCTMorn(TimekeepingTypeCT timekeepingTypeCTMorn) {
        this.timekeepingTypeCTMorn = timekeepingTypeCTMorn;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getListService() {
        return listService;
    }

    public int getWorkDay() {
        return workDay;
    }

    public void setWorkDay(int workDay) {
        this.workDay = workDay;
    }

    public Date getMainDate() {
        return mainDate;
    }

    public void setMainDate(Date mainDate) {
        this.mainDate = mainDate;
    }

    public void setListService(String listService) {
        this.listService = listService;
        String []arr = listService.split(",");
        for (String s : arr){
            switch (s){
                case "0" :
                    isService_0 = true;
                    break;
                case "1" :
                    isService_1 = true;
                    break;
                case "2" :
                    isService_2 = true;
                    break;
                case "3" :
                    isService_3 = true;
                    break;
                case "4" :
                    isService_4 = true;
                    break;
                case "5" :
                    isService_5 = true;
                    break;
                case "6" :
                    isService_6 = true;
                    break;
                case "7" :
                    isService_7 = true;
                    break;
            }
        }
    }

    public TimekeepingTypeCT getTimekeepingTypeCTAfft() {
        return timekeepingTypeCTAfft;
    }

    public void setTimekeepingTypeCTAfft(TimekeepingTypeCT timekeepingTypeCTAfft) {
        this.timekeepingTypeCTAfft = timekeepingTypeCTAfft;
    }

    public TimekeepingTypeCT getTimekeepingTypeCTEvrn() {
        return timekeepingTypeCTEvrn;
    }

    public void setTimekeepingTypeCTEvrn(TimekeepingTypeCT timekeepingTypeCTEvrn) {
        this.timekeepingTypeCTEvrn = timekeepingTypeCTEvrn;
    }

    public Business() {
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public National getNational() {
        return national;
    }

    public void setNational(National national) {
        this.national = national;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateBeginDisplay(){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(dateBegin);
    }
    public String getDateEndDisplay(){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(dateEnd);
    }
    public String getMainDateDisplay(){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(mainDate);
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public boolean isService_0() {
        return isService_0;
    }

    public void setService_0(boolean service_0) {
        isService_0 = service_0;
    }

    public boolean isService_1() {
        return isService_1;
    }

    public void setService_1(boolean service_1) {
        isService_1 = service_1;
    }

    public boolean isService_2() {
        return isService_2;
    }

    public void setService_2(boolean service_2) {
        isService_2 = service_2;
    }

    public boolean isService_3() {
        return isService_3;
    }

    public void setService_3(boolean service_3) {
        isService_3 = service_3;
    }

    public boolean isService_4() {
        return isService_4;
    }

    public void setService_4(boolean service_4) {
        isService_4 = service_4;
    }

    public boolean isService_5() {
        return isService_5;
    }

    public void setService_5(boolean service_5) {
        isService_5 = service_5;
    }

    public boolean isService_6() {
        return isService_6;
    }

    public void setService_6(boolean service_6) {
        isService_6 = service_6;
    }

    public boolean isService_7() {
        return isService_7;
    }

    public void setService_7(boolean service_7) {
        isService_7 = service_7;
    }
}
