/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.servlet;

import com.hotel.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewReservationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html"); // Redirect if not logged in
            return;
        }

        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role"); // Assuming role is stored in session as "admin" or "customer"

        try (Connection con = DBConnection.getConnection()) {
            String query;
            PreparedStatement ps;

            if ("admin".equals(role)) {
                // Admin: Fetch all reservations
                query = "SELECT * FROM reservations";
                ps = con.prepareStatement(query);
            } else {
                // Customer: Fetch only their reservations
                query = "SELECT * FROM reservations WHERE user_name = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, username);
            }

            ResultSet rs = ps.executeQuery();
            
            out.println("<html><head><title>View Reservations</title></head><body>");
            out.println("<h2>Reservation List</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Username</th><th>Room Number</th><th>Check-In</th><th>Check-Out</th></tr>");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("user_name") + "</td>");
                out.println("<td>" + rs.getInt("room_number") + "</td>");
                out.println("<td>" + rs.getString("check_in") + "</td>");
                out.println("<td>" + rs.getString("check_out") + "</td>");
                out.println("</tr>");
            }

            if (!hasData) {
                out.println("<tr><td colspan='5'>No reservations found.</td></tr>");
            }

            out.println("</table>");
            
            if ("admin".equals(role)) {
                out.println("<br><a href='adminDashboard.html'>Back to Dashboard</a>");
            } else {
                out.println("<br><a href='customerDashboard.html'>Back to Dashboard</a>");
            }

            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
