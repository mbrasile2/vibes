/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.groupBean;
import beans.message;
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
import java.util.ArrayList;
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
                conn = DriverManager.getConnection("jdbc:mysql://localhost:33069/cse305", "root", "suckit");
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

                
            // get messages
             query = "SELECT * FROM Message WHERE (Receiver = " +returningUser.getAccountNumber()+
                        ") ORDER BY messagedate DESC;";
                
            ArrayList<message> messageList = new ArrayList<>();
               
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    message m = new message();
                    int i = rs.getObject("Sender", Integer.class);
                    m.setAcct(i);
                    m.setDate(rs.getDate("MessageDate"));
                    m.setContent(rs.getString("content"));
                    m.setMid(rs.getInt("MessageID"));
                    messageList.add(m);
                }    
                
                for (message m2 : messageList) {
                    query2 = "SELECT firstname, lastname, emailaddress FROM User WHERE (accountNumber = " +m2.getAcct()+ ");";
                    try {
                        ResultSet rs2 = stmt.executeQuery(query2) ;
                        while (rs2.next()) {
                            m2.setEmail(rs2.getString("emailAddress"));
                            m2.setSender(rs2.getString("firstName") + " " + rs2.getString("lastName"));
                        }
                        }catch (SQLException ex) {
                        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
                session.setAttribute("messages", messageList);
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
            // get users groups (owner)
            query = "SELECT * FROM fbGroup WHERE (owner = " +returningUser.getAccountNumber()+
                        ");";
                
            ArrayList<groupBean> groupList = new ArrayList<>();
               
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    groupBean g = new groupBean();
                    g.setGroupName(rs.getString("groupname"));
                    g.setPageID(rs.getInt("pageID"));
                    g.setGroupID(rs.getInt("GroupID"));
                    g.setGroupOwner(returningUser.getAccountNumber());
                    groupList.add(g);
                }   
                
                session.setAttribute("groups", groupList);
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             // get users groups (member)
            query = "SELECT * FROM GroupMembership WHERE (UserID = " +returningUser.getAccountNumber()+
                        ");";
                
            ArrayList<groupBean> groupMembership = new ArrayList<>();
            ArrayList<groupBean> groupMembership2 = new ArrayList<>();
            
            // get the group IDs from membership list 
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    groupBean g = new groupBean();
                    g.setGroupID(rs.getInt("groupID"));
                    groupMembership.add(g);
                }   
               
            } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // now get the info
            for (groupBean g : groupMembership) {
                
                query = "SELECT * FROM fbGroup WHERE (GroupID = " +g.getGroupID()+
                        ");";
                try {
                    ResultSet rs = stmt.executeQuery(query) ;
                    while (rs.next()) {
                        groupBean gb = new groupBean();
                        gb.setGroupID(g.getGroupID());
                        gb.setPageID(rs.getInt("pageID"));
                        gb.setGroupName(rs.getString("GroupName"));
                        gb.setGroupOwner(rs.getInt("owner"));
                        groupMembership2.add(gb);
                    }   
               
                } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // add to session data
                session.setAttribute("groupMembership", groupMembership2);
            }
            
        }
        }catch (SQLException ex) {
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