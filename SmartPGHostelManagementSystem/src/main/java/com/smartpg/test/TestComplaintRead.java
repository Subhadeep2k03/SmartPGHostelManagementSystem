package com.smartpg.test;

import java.util.List;

import com.smartpg.dao.ComplaintDAO;
import com.smartpg.model.Complaint;

public class TestComplaintRead {

	public static void main(String[] args) {

		ComplaintDAO dao = new ComplaintDAO();

		List<Complaint> complaints = dao.getAllComplaints();

		for(Complaint c : complaints) {

			System.out.println("Complaint ID: " + c.getComplaintId());
			System.out.println("Student ID: " + c.getStudentId());
			System.out.println("Room ID: " + c.getRoomId());
			System.out.println("Date: " + c.getComplaintDate());
			System.out.println("Description: " + c.getDescription());
			System.out.println("Status: " + c.getStatus());

			System.out.println("----------------------------");
		}
	}
}