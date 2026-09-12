package com.smartpg.dao;


public class TestStudentDelete {

	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		dao.deleteStudent(7);

	}

}
