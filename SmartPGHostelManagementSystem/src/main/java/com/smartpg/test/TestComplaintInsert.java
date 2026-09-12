package com.smartpg.test;

import com.smartpg.dao.ComplaintDAO;
import com.smartpg.model.Complaint;

public class TestComplaintInsert {

	public static void main(String[] args) {

		Complaint complaint = new Complaint();

		complaint.setStudentId(5);
		complaint.setRoomId(101);
		complaint.setComplaintDate("2026-08-03");
		complaint.setDescription("Water leakage problem in room");
		complaint.setStatus("Pending");

		ComplaintDAO dao = new ComplaintDAO();

		dao.insertComplaint(complaint);

		System.out.println("Complaint inserted successfully");
	}
}