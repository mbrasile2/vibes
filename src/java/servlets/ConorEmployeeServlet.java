/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.advertisementBean;
import beans.advertisementBeanConor;
import beans.mailingListBean;
import beans.message;
import entities.Employee;
import entities.Posts;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Conor
 */
public class ConorEmployeeServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String ssn = request.getParameter("ssn");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String query;
        String pw = new String();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConorEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:33069/cse305", "root", "suckit");
            } catch (SQLException ex) {
                Logger.getLogger(ConorEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement stmt = conn.createStatement();
            query = "SELECT ssn, password FROM employee WHERE (ssn = '" +ssn+ 
                    "' AND password = '" +password+ "');";
            try {
                ResultSet rs = stmt.executeQuery(query) ;
                while (rs.next()) {
                    pw = rs.getString("password");
                }    
            } catch (SQLException ex) {
                Logger.getLogger(ConorEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }     
            
            if (pw != null && pw.equals(password)) {
            // success, the username and password checks out. Grab 
            // all information, and store it in the session.
            
                //Create and populate User bean
                Employee returningEmployee = new Employee();
                
                String query1 = "SELECT * FROM employee WHERE (ssn = '" +ssn+ 
                        "' AND password = '" +password+ "');";         
                try {
                    ResultSet rs = stmt.executeQuery(query1) ;
                    while (rs.next()) {
                        
                        returningEmployee.setSsn(Integer.parseInt(ssn));
                        returningEmployee.setFirstName(rs.getString("FirstName"));
                        returningEmployee.setLastName(rs.getString("LastName"));
                    }
                
                    // add user info to the session
                    session.setAttribute("employee", returningEmployee);
                } catch (SQLException ex) {
                    Logger.getLogger(ConorEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<advertisementBeanConor> ads = new ArrayList<advertisementBeanConor>();
                String adQuery = "SELECT * FROM advertisement;";
                try {
                    ResultSet rs = stmt.executeQuery(adQuery) ;
                    while (rs.next()) {
                        ads.add(new advertisementBeanConor(rs.getInt("advertisementID"), rs.getInt("employeeID"), rs.getString("type"), 
                        rs.getString("itemName"), rs.getDate("dateCreated"), rs.getString("company"), rs.getString("content"), rs.getDouble("price"), rs.getInt("unitsAvailable")));
                        
                    }
                ArrayList<mailingListBean> mail = new ArrayList<mailingListBean>();
                String mailQuery = "SELECT * FROM user;";
                    // add ads and mailing list info to the session
                    session.setAttribute("allAds", ads);
                } catch (SQLException ex) {
                    Logger.getLogger(ConorEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<mailingListBean> mail = new ArrayList<mailingListBean>();
                String mailQuery = "SELECT * FROM user;";
                try{
                ResultSet mQ = stmt.executeQuery(mailQuery);
                    while (mQ.next()) {
                        mail.add(new mailingListBean(mQ.getString("FirstName"), mQ.getString("LastName"), mQ.getString("EmailAddress")));
                        
                    }
                session.setAttribute("mailingList", mail);}
                catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(ConorEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }}
            
        } 
catch (SQLException ex) {
            Logger.getLogger(ConorEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher jsp = request.getRequestDispatcher("./employeeIndex.jsp");
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
