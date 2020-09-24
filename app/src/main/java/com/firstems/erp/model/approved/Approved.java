package com.firstems.erp.model.approved;

import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nguyen Quoc Cuong on 8/14/2020.
 */
public class Approved implements Serializable {
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
    private long numberDay;

    public Approved() {
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public long getNumberDay() {
        return  (((dateEnd.getTime() - dateBegin.getTime())/ (1000 * 60 * 60 * 24) % 365)+1);
    }

    public void setNumberDay(long numberDay) {
        this.numberDay = numberDay;
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


    public boolean isEverning() {
        return isEverning;
    }

    public void setEverning(boolean everning) {
        isEverning = everning;
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

    public TimekeepingTypeDC getContentEverning() {
        return contentEverning;
    }

    public void setContentEverning(TimekeepingTypeDC contentEverning) {
        this.contentEverning = contentEverning;
    }
}
