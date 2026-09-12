package com.smartpg.dao;

import com.smartpg.model.Student;

public class TestStudentInsert {

    public static void main(String[] args) {

        Student student = new Student();

        student.setFirstName("Subho");
        student.setLastName("Goswami");
        student.setGender("Male");
        student.setDob("2003-08-15");
        student.setPhone("9123456780");
        student.setEmail("subho.goswami@gmail.com");
        student.setAddress("Kolkata");
        student.setGuardianName("Arindam Goswami");
        student.setGuardianPhone("9123456781");
        student.setJoinDate("2026-08-03");
        student.setStatus("Active");


        StudentDAO dao = new StudentDAO();

        dao.addStudent(student);
        
        Student student2 = new Student();

        student2.setFirstName("Amit");
        student2.setLastName("Roy");
        student2.setGender("Male");
        student2.setDob("2002-08-10");
        student2.setPhone("9999923999");
        student2.setEmail("amit@gmail.com");
        student2.setAddress("Kolkata");
        student2.setGuardianName("Suresh Roy");
        student2.setGuardianPhone("9999900000");
        student2.setJoinDate("2026-08-03");
        student2.setStatus("Active");

        dao.addStudent(student2);

    }

}