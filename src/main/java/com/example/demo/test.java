package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            final String url="jdbc:mysql://localhost:3306/fruit?useUnicode=true&useSSL=false&characterEncoding=utf-8";
            final String user="root";
            final String password="root";
            Connection conn= DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
