/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.servlet;
import com.hotel.util.DBConnection;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *
 * @author DISHITA
 */
public class RegisterServlet extends HttpServlet{
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out= response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
         try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            int result= ps.executeUpdate();
            out.println("<html><body>");
            if (result > 0) {
                out.println("<h2>Registration Successful!</h2>");
                out.println("<a href='login.html'><button>Login Now</button></a>");
            } else {
                out.println("<h2>Registration Failed. Try Again.</h2>");
                out.println("<a href='register.html'><button>Back</button></a>");
            }
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.html?error=Registration Failed");
        }
        
    }
}
   






