package com.smartpg.test;

import com.smartpg.dao.ComplaintDAO;
import com.smartpg.model.Complaint;

public class TestComplaintUpdate {

	public static void main(String[] args) {

		Complaint complaint = new Complaint();

		complaint.setComplaintId(1);
		complaint.setStatus("Resolved");

		ComplaintDAO dao = new ComplaintDAO();

		dao.updateComplaint(complaint);

	}

}