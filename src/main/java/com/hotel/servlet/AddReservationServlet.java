/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.hotel.util.DBConnection;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DISHITA
 */
public class AddReservationServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
       String userName=request.getParameter("user_name");
        String guestName = request.getParameter("guest_name");
        int roomNumber = Integer.parseInt(request.getParameter("room_number")); 
        String contactNumber = request.getParameter("contact_number");
        String checkIn = request.getParameter("check_in");
        String checkOut = request.getParameter("check_out");
       

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO reservations (user_name,guest_name,room_number,contact_number,check_in, check_out) VALUES (?, ?, ?, ? ,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, userName);
           ps.setString(2, guestName);
            ps.setInt(3, roomNumber);
            ps.setString(4, contactNumber);
            ps.setString(5, checkIn);
            ps.setString(6, checkOut);
          
            int rowsInserted = ps.executeUpdate();
            out.println("<html><body>");
            if (rowsInserted > 0) {
                 out.println("<h2>Reservation Added Successfully!</h2>");
            } else {
                out.println("<h2>Failed to Add Reservation.</h2>");
            }
              out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
