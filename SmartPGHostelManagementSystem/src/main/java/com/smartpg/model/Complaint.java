package com.smartpg.model;

public class Complaint {

	private int complaintId;
	private int studentId;
	private int roomId;
	private String complaintDate;
	private String description;
	private String status;


	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}


	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}


	public String getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}