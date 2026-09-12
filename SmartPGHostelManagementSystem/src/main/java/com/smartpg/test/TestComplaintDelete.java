package com.smartpg.test;

import com.smartpg.dao.ComplaintDAO;

public class TestComplaintDelete {

	public static void main(String[] args) {

		ComplaintDAO dao = new ComplaintDAO();

		dao.deleteComplaint(1);

	}

}