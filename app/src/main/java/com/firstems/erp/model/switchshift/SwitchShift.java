package com.firstems.erp.model.switchshift;

import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nguyen Quoc Cuong on 8/5/2020.
 */
public class SwitchShift implements Serializable {
    private Date dateBegin;
    private Date dateEnd;
    private Employee employeeDiLam;
    private Employee employeeChamCong;
    private boolean isMorning;
    private TimekeepingTypeDC contentMornig;
    private boolean isAfternoon;
    private TimekeepingTypeDC contentAfternoon;
    private boolean isEverning;
    private TimekeepingTypeDC contentEverning;

    public SwitchShift() {
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

    public Employee getEmployeeDiLam() {
        return employeeDiLam;
    }

    public void setEmployeeDiLam(Employee employeeDiLam) {
        this.employeeDiLam = employeeDiLam;
    }

    public Employee getEmployeeChamCong() {
        return employeeChamCong;
    }

    public void setEmployeeChamCong(Employee employeeChamCong) {
        this.employeeChamCong = employeeChamCong;
    }

    public boolean isMorning() {
        return isMorning;
    }

    public void setMorning(boolean morning) {
        isMorning = morning;
    }

    public TimekeepingTypeDC getContentMornig() {
        return contentMornig;
    }

    public void setContentMornig(TimekeepingTypeDC contentMornig) {
        this.contentMornig = contentMornig;
    }

    public boolean isAfternoon() {
        return isAfternoon;
    }

    public void setAfternoon(boolean afternoon) {
        isAfternoon = afternoon;
    }

    public TimekeepingTypeDC getContentAfternoon() {
        return contentAfternoon;
    }

    public void setContentAfternoon(TimekeepingTypeDC contentAfternoon) {
        this.contentAfternoon = contentAfternoon;
    }

    public boolean isEverning() {
        return isEverning;
    }

    public void setEverning(boolean everning) {
        isEverning = everning;
    }

    public TimekeepingTypeDC getContentEverning() {
        return contentEverning;
    }

    public void setContentEverning(TimekeepingTypeDC contentEverning) {
        this.contentEverning = contentEverning;
    }
    public String getDateBeginDisplay(){
        return new SimpleDateFormat("dd-MM_yyyy").format(dateBegin);
    }
    public String getDateEndDisplay(){
        return new SimpleDateFormat("dd-MM_yyyy").format(dateEnd);
    }
}
