package com.smartpg.test;

import com.smartpg.dao.AdminDAO;

public class TestAdminLogin {

    public static void main(String[] args) {

        AdminDAO dao = new AdminDAO();

        boolean result = dao.login("admin", "12345");

        System.out.println(result);

    }

}