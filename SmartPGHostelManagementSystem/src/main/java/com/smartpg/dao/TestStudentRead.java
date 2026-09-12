package com.smartpg.dao;

import java.util.List;
import com.smartpg.model.Student;

public class TestStudentRead {

	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		
		List<Student> students = dao.getAllStudents();
		
		for(Student s : students) {

		    System.out.println(
		        s.getStudentId() + " " +
		        s.getFirstName() + " " +
		        s.getLastName() + " " +
		        s.getPhone()
		    );

		}

	}

}
