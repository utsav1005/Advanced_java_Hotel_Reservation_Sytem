/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.servlet;
import com.hotel.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DISHITA
 */
 
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            out.println("<html><head><title>Login</title></head><body>");

            if (rs.next()) {
                String role = rs.getString("role");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                out.println("<h2>Welcome, " + username + "!</h2>");

                if ("admin".equals(role)) {
                    out.println("<h3>You are logged in as Admin.</h3>");
                    out.println("<a href='adminDashboard.html'><button>Go to Admin Dashboard</button></a>");
                } else {
                    out.println("<h3>You are logged in as a User.</h3>");
                    out.println("<a href='customerDashboard.html'><button>Go to User Dashboard</button></a>");
                }
            } else {
                out.println("<h2>Invalid username or password!</h2>");
                out.println("<a href='login.html'><button>Try Again</button></a>");
            }
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error occurred: " + e.getMessage() + "</h2>");
        }
    }
}
