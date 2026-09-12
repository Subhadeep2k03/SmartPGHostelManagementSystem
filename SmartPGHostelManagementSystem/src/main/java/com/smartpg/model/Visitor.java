package com.smartpg.model;

import java.sql.Date;
import java.sql.Time;

public class Visitor {

    private int visitorId;
    private int studentId;
    private String visitorName;
    private String phone;
    private String relation;
    private Date visitDate;
    private Time inTime;
    private Time outTime;

    public Visitor() {

    }

    public Visitor(int visitorId, int studentId, String visitorName, String phone,
                   String relation, Date visitDate, Time inTime, Time outTime) {

        this.visitorId = visitorId;
        this.studentId = studentId;
        this.visitorName = visitorName;
        this.phone = phone;
        this.relation = relation;
        this.visitDate = visitDate;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Time getInTime() {
        return inTime;
    }

    public void setInTime(Time inTime) {
        this.inTime = inTime;
    }

    public Time getOutTime() {
        return outTime;
    }

    public void setOutTime(Time outTime) {
        this.outTime = outTime;
    }

}