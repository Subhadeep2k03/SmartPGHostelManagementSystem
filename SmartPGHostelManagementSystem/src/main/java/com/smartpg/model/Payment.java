package com.smartpg.model;

public class Payment {
	
	private int paymentId;
	private int studentId;
	private double amount;
	private String paymentDate;
	private String status;
	
	public int getPaymentId() {
	    return paymentId;
	}
	    public void setPaymentId(int paymentId) {
	        this.paymentId = paymentId;
	    }
	    public int getStudentId() {
	        return studentId;
	    }
	    public void setStudentId(int studentId) {
	        this.studentId = studentId;
	    }
	    public double getAmount() {
	        return amount;
	    }
	    public void setAmount(double amount) {
	        this.amount = amount;
	    }
	    public String getPaymentDate() {
	        return paymentDate;
	    }
	    public void setPaymentDate(String paymentDate) {
	        this.paymentDate = paymentDate;
	    }
	    public String getStatus() {
	        return status;
	    }
	    public void setStatus(String status) {
	        this.status = status;
	    }
}
