/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.User;
import entities.Pages;
import entities.Posts;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kATHRYN
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get info from form
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        
        /* get session object */
        HttpSession session = request.getSession();
        
        String pw = new String();
        String query;
            
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CSE305Project", "root", "1097123466344aA!");
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stmt = conn.createStatement();
            query = "SELECT EmailAddress, password FROM User WHERE (EmailAddress = '" +user+ 
                    "' AND password = '" +password+ "');";
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    pw = rs.getString("password");
                }    
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }     
            
            if (pw != null && pw.equals(password)) {
            // success, the username and password checks out. Grab 
            // all information, and store it in the session.
            
                //Create and populate User bean
                User returningUser = new User();
                
                String query1 = "SELECT * FROM User WHERE (EmailAddress = '" +user+ 
                        "' AND password = '" +password+ "');";         
                try {
                    ResultSet rs = stmt.executeQuery(query1) ;
                    while (rs.next()) {
                        returningUser.setFirstName(rs.getString("FirstName"));
                        returningUser.setLastName(rs.getString("LastName"));
                        returningUser.setEmailAddress(user);
                        returningUser.setAccountNumber(rs.getInt("AccountNumber"));
                    }
                
                    // add user info to the session
                    session.setAttribute("user", returningUser);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String query2 = "SELECT * FROM Pages WHERE (ownerID = '" +returningUser.getAccountNumber()+
                        "' AND primarypage = 'n');";
                String query3 = "SELECT * FROM Pages WHERE (ownerID = '" +returningUser.getAccountNumber()+
                        "' AND primarypage = 'y');";
                
                ArrayList<Integer> groupPages = new ArrayList<>();
                int mainPage = 0;
                
                try {
                    ResultSet rs = stmt.executeQuery(query2) ;
                    while (rs.next()) {
                        groupPages.add(rs.getInt("pageID"));
                    }
                    ResultSet rs2 = stmt.executeQuery(query3);
                    while (rs2.next()) {
                        mainPage = rs2.getInt("pageID");
                    }
                
                    // add user info to the session
                    session.setAttribute("pageID", mainPage);
                    session.setAttribute("groupIDs", groupPages);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                query = "SELECT * FROM Posts WHERE (Author = " +returningUser.getAccountNumber()+
                        ") ORDER BY postdate DESC;";
                ArrayList<Posts> postList = new ArrayList<Posts>();
                try {
                    ResultSet rs = stmt.executeQuery(query) ;
                    Posts p = new Posts();
                    while (rs.next()) {
                        p.setPostID(rs.getInt("PostID"));
                        p.setPostDate(rs.getDate("PostDate"));
                        p.setContent(rs.getString("content"));
                        postList.add(p);
                    }    
                
                session.setAttribute("postlist", postList);
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        RequestDispatcher jsp = request.getRequestDispatcher("/index.jsp");
        jsp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}