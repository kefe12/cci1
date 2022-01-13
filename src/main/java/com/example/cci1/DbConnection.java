package com.example.cci1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL="jdbc:mysql://localhost/cci";
    private static final String USER="root";
    private static final String PASSWORD="";
    private static final String DRIVER="com.mysql.jdbc.Driver";
    Connection conn=null;
    public Connection connMethod()
    {
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("connection succeded");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
