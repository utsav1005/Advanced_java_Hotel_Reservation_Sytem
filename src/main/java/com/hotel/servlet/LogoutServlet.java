/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get the current session, if exists
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate(); // Destroy session
        }

        // Display logout message and login form
        out.println("<html><head><title>Login</title></head><body>");
        out.println("<h2 style='color: green;'>You have been successfully logged out.</h2>");
        out.println("<h2>Login</h2>");
        out.println("<form action='LoginServlet' method='post'>");
        out.println("<input type='text' name='username' placeholder='Enter Username' required><br>");
        out.println("<input type='password' name='password' placeholder='Enter Password' required><br>");
        out.println("<button type='submit'>Login</button>");
        out.println("</form>");
        out.println("<a href='register.html'>Don't have an account? Register here</a>");
        out.println("</body></html>");
    }
}

