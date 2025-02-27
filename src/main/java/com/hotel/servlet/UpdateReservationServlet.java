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

/**
 *
 * @author DISHITA
 */
public class UpdateReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
       
            
            int reservationId = Integer.parseInt(request.getParameter("id"));
            
            int roomNumber= Integer.parseInt(request.getParameter("room_number"));
            String checkIn = request.getParameter("check_in");
            String checkOut = request.getParameter("check_out");
             
             try {
                 
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE reservations SET room_number=?, check_in=?, check_out=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1,roomNumber);
            ps.setString(2, checkIn);
            ps.setString(3, checkOut);
            ps.setInt(4, reservationId);
          
            int rowsInserted= ps.executeUpdate();
           out.println("<html><body>");
            if (rowsInserted > 0) {
                out.println("<h2>Reservation Updated Successfully!</h2>");
            } else {
                out.println("<h2>Failed to Update Reservation.</h2>");
            }
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
             out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
