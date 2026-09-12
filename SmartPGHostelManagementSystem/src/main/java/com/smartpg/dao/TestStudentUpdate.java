package com.smartpg.dao;
import com.smartpg.model.Student;

public class TestStudentUpdate {

	public static void main(String[] args) {
		Student student = new Student();
		student.setStudentId(2);
		
		student.setFirstName("Rahul");
		student.setLastName("Das");
		student.setPhone("9999999999");
		student.setEmail("rahulnew@gmail.com");
		student.setAddress("Kolkata New Address");
		student.setStatus("ACTIVE");
		
		StudentDAO dao = new StudentDAO();
		
		dao.updateStudent(student);
	}

}
