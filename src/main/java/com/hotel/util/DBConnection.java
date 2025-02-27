/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotel.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DISHITA
 */
    public class DBConnection {
   

    private static final String URL = "jdbc:mysql://localhost:3306/hotel_reservation_system";
    private static final String USER = "root";  
    private static final String PASSWORD = "UTsav@9975";     

    public static Connection getConnection()  {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
        
    }
